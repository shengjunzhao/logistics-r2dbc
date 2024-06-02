package com.haole.logistics.r2dbc.service.logistics.impl;

import com.haole.logistics.r2dbc.common.ActionResult;
import com.haole.logistics.r2dbc.common.BusinessException;
import com.haole.logistics.r2dbc.dal.bo.logistics.LogisticsOrderCreateBO;
import com.haole.logistics.r2dbc.dal.bo.logistics.LogisticsWayInfoCreateBO;
import com.haole.logistics.r2dbc.dal.dao.*;
import com.haole.logistics.r2dbc.dal.model.*;
import com.haole.logistics.r2dbc.enums.*;
import com.haole.logistics.r2dbc.service.BeanMapper;
import com.haole.logistics.r2dbc.service.amap.AmapService;
import com.haole.logistics.r2dbc.service.bo.amap.AmapReverseAddressBO;
import com.haole.logistics.r2dbc.service.dto.amap.AmapReverseAddressDTO;
import com.haole.logistics.r2dbc.service.log.OperLogService;
import com.haole.logistics.r2dbc.service.logistics.LogisticsService;
import com.haole.logistics.r2dbc.service.snowflake.SnowflakeGenerator;
import com.haole.logistics.r2dbc.service.trace.DriverOperateNodeService;
import com.haole.logistics.r2dbc.util.BigDecimalUtil;
import com.haole.logistics.r2dbc.util.JSON;
import com.haole.logistics.r2dbc.util.LocalDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class LogisticsServiceImpl implements LogisticsService {

    private static final Logger logger = LoggerFactory.getLogger(LogisticsServiceImpl.class);

    private LogisticsOrderBillMapper logisticsOrderBillMapper;

    private LogisticsGoodsItemMapper logisticsGoodsItemMapper;

    private LogisticsAddressMapper logisticsAddressMapper;

    private LogisticsOrderWayMapper logisticsOrderWayMapper;

    private WayBillMapper wayBillMapper;

    private WayGoodsItemMapper wayGoodsItemMapper;

    private WayBillAddressMapper wayBillAddressMapper;

    private LogisticsNodeInfoMapper logisticsNodeInfoMapper;

    private OperLogService operLogService;

    private DriverOperateNodeService driverOperateNodeService;

    private AmapService amapService;

    private BeanMapper beanMapper;

    private SnowflakeGenerator generator;

    @Override
    @Transactional
    public Mono<ActionResult<Long>> createLogistics(Mono<LogisticsOrderCreateBO> param) {
        LocalDateTime now = LocalDateTime.now();
        return param.publishOn(Schedulers.boundedElastic()).flatMap(new Function<LogisticsOrderCreateBO, Mono<ActionResult<Long>>>() {
            @Override
            public Mono<ActionResult<Long>> apply(LogisticsOrderCreateBO p) {
                Mono<LogisticsOrderBill> existLogisticsOrder = logisticsOrderBillMapper.findUniqueLogistics(p.getSourceSystem(),
                                p.getSourceBillSn(), p.getParentSourceBillNo(), p.getSourceBillType())
                        .onErrorMap(original -> new BusinessException(ResultType.FAILURE.code(), original.getMessage(), original));
                LogisticsOrderBill existLogistics = existLogisticsOrder.block(); // 阻塞调用
                if (null != existLogistics) {
//                    throw new BusinessException(ResultType.FAILURE.code(),
//                            "单据" + (StringUtils.hasText(p.getSourceBillSn()) ? p.getSourceBillSn() : p.getParentSourceBillNo())
//                                    + "已经完成发货,请在对应的发货页面查看对应信息!!");
                    return Mono.error(new BusinessException(ResultType.FAILURE.code(),
                            "单据" + (StringUtils.hasText(p.getSourceBillSn()) ? p.getSourceBillSn() : p.getParentSourceBillNo())
                                    + "已经完成发货,请在对应的发货页面查看对应信息!!"));
                }
//                return Mono.just(ActionResult.success(1L));
//                /**
                Set<String> vclns = p.getLogisticsOrderWayInfoList().stream().map(LogisticsWayInfoCreateBO::getVcln).collect(Collectors.toSet());
                if (vclns.size() != p.getLogisticsOrderWayInfoList().size()) {
                    return Mono.error(new BusinessException(ResultType.FAILURE.code(), "您填写的车牌号是一样的,请检查后重新填写!!"));
                }
                //获得物流地址的经纬度信息
                List<AmapReverseAddressBO> addresses = new ArrayList<>(2);

                //增加地址信息中换行的过滤
                p.getReceiveAddress().setDetailAddress(p.getReceiveAddress().getDetailAddress().replaceAll("\\n", "").replaceAll("\\t", ""));
                p.getDeliveryAddress().setDetailAddress(p.getDeliveryAddress().getDetailAddress().replaceAll("\\n", "").replaceAll("\\t", ""));
                // 发货方地址信息
                AmapReverseAddressBO deliveryAddress = new AmapReverseAddressBO();
                deliveryAddress.setAddress(String.format("%s%s%s%s%s", p.getDeliveryAddress().getProvinceName(),
                        p.getDeliveryAddress().getCityName(), p.getDeliveryAddress().getCountyName(),
                        p.getDeliveryAddress().getTownName(), p.getDeliveryAddress().getDetailAddress()));
                //收货方地址
                AmapReverseAddressBO receiveAddress = new AmapReverseAddressBO();
                receiveAddress.setAddress(String.format("%s%s%s%s%s", p.getReceiveAddress().getProvinceName(),
                        p.getReceiveAddress().getCityName(), p.getReceiveAddress().getCountyName(),
                        p.getReceiveAddress().getTownName(), p.getReceiveAddress().getDetailAddress()));
                addresses.add(deliveryAddress);
                addresses.add(receiveAddress);
                // 查询经纬度
                List<AmapReverseAddressDTO> reverseAddress = amapService.queryReverseAddressByZT(addresses);
                //发货地址坐标
                p.getDeliveryAddress().setAddrLon(reverseAddress.get(0).getLonLat().split(",")[0]);
                p.getDeliveryAddress().setAddrLal(reverseAddress.get(0).getLonLat().split(",")[1]);
                //收货地址坐标
                p.getReceiveAddress().setAddrLon(reverseAddress.get(1).getLonLat().split(",")[0]);
                p.getReceiveAddress().setAddrLal(reverseAddress.get(1).getLonLat().split(",")[1]);
                //构建订单信息
                LogisticsOrderBill logisticsOrderBill = beanMapper.map(p, LogisticsOrderBill.class);
                if (null != logisticsOrderBill.getCargoWeight()) {
                    logisticsOrderBill.setCargoWeight(BigDecimalUtil.divide(logisticsOrderBill.getCargoWeight(), 3));
                }
                if (null != logisticsOrderBill.getCargoVolume()) {
                    logisticsOrderBill.setCargoVolume(BigDecimalUtil.divide(logisticsOrderBill.getCargoVolume(), 3 * 3));
                }
                logisticsOrderBill.setLogisticsOrderId(generator.nextId());

                //发货地址信息
                List<LogisticsAddress> logisticsAddressesList = new ArrayList<>();
                LogisticsAddress logisticsDeliveryAddress = beanMapper.map(p.getDeliveryAddress(), LogisticsAddress.class);
                logisticsDeliveryAddress.setLogisticsOrderId(logisticsOrderBill.getLogisticsOrderId());
                logisticsDeliveryAddress.setLogisticsAddressType(LogisticsAddressTypeEnum.START_POINT.getType());
                logisticsDeliveryAddress.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                logisticsDeliveryAddress.setCreateByUserId(p.getCreateByUserId());
                logisticsDeliveryAddress.setCreateTime(now);
                logisticsDeliveryAddress.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                logisticsDeliveryAddress.setUpdateByUserId(p.getCreateByUserId());
                logisticsDeliveryAddress.setUpdateTime(now);
                logisticsDeliveryAddress.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                logisticsAddressesList.add(logisticsDeliveryAddress);
                //收货地址信息
                LogisticsAddress logisticsReverseAddress = beanMapper.map(p.getReceiveAddress(), LogisticsAddress.class);
                logisticsReverseAddress.setLogisticsOrderId(logisticsOrderBill.getLogisticsOrderId());
                logisticsReverseAddress.setLogisticsAddressType(LogisticsAddressTypeEnum.END_POINT.getType());
                logisticsReverseAddress.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                logisticsReverseAddress.setCreateByUserId(p.getCreateByUserId());
                logisticsReverseAddress.setCreateTime(now);
                logisticsReverseAddress.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                logisticsReverseAddress.setUpdateByUserId(p.getCreateByUserId());
                logisticsReverseAddress.setUpdateTime(now);
                logisticsReverseAddress.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                logisticsAddressesList.add(logisticsReverseAddress);

                //商品信息
                List<LogisticsGoodsItem> goodsItemList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(p.getGoodsItems())) {
                    if (null == logisticsOrderBill.getCargoCount()) {
                        logisticsOrderBill.setCargoCount(p.getGoodsItems().stream().map(g -> {
                                    if (null == g.getCargoCount()) {
                                        g.setCargoCount(1);
                                    }
                                    if (null == g.getCargoCountUnit()) {
                                        g.setCargoCountUnit("件");
                                    }
                                    return g.getCargoCount();
                                }
                        ).reduce(Integer::sum).get());
                    }
                    if (null == logisticsOrderBill.getCargoWeight()) {
                        logisticsOrderBill.setCargoWeight(p.getGoodsItems().stream().map(g -> {
                            if (null == g.getCargoWeight()) {
                                g.setCargoWeight(BigDecimal.ZERO);
                            }
                            if (!StringUtils.hasText(g.getCargoWeightUnit())) {
                                g.setCargoWeightUnit("0");
                            }
                            // 默认重量单位是公斤
                            g.setCargoWeight(BigDecimalUtil.divide(g.getCargoWeight(), 3));
                            return g.getCargoWeight();
                        }).reduce(BigDecimal::add).get());
                    }
                    if (null == logisticsOrderBill.getCargoVolume()) {
                        logisticsOrderBill.setCargoVolume(p.getGoodsItems().stream().map(g -> {
                            if (null == g.getCargoVolume()) {
                                g.setCargoVolume(BigDecimal.ZERO);
                            }
                            if (!StringUtils.hasText(g.getCargoVolumeUnit())) {
                                g.setCargoVolumeUnit("0");
                            }
                            // 默认体积单位是m³
                            g.setCargoVolume(BigDecimalUtil.divide(g.getCargoVolume(), 3 * 3));
                            return g.getCargoVolume();

                        }).reduce(BigDecimal::add).get());
                    }
                    goodsItemList.addAll(p.getGoodsItems().stream().map(g -> {
                        LogisticsGoodsItem goodsItem = beanMapper.map(g, LogisticsGoodsItem.class);
                        goodsItem.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                        goodsItem.setCreateByUserId(p.getCreateByUserId());
                        goodsItem.setCreateTime(now);
                        goodsItem.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                        goodsItem.setUpdateByUserId(p.getCreateByUserId());
                        goodsItem.setUpdateTime(now);
                        goodsItem.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                        return goodsItem;

                    }).toList());
                }
                if (null == logisticsOrderBill.getCargoCount()) {
                    logisticsOrderBill.setCargoCount(1);
                }
                if (null == logisticsOrderBill.getCargoWeight()) {
                    logisticsOrderBill.setCargoWeight(BigDecimal.ZERO);
                }
                if (null == logisticsOrderBill.getCargoVolume()) {
                    logisticsOrderBill.setCargoVolume(BigDecimal.ZERO);
                }
                if (null == logisticsOrderBill.getCargoCountUnit()) {
                    logisticsOrderBill.setCargoCountUnit("件");
                }
                if (!StringUtils.hasText(logisticsOrderBill.getCargoWeightUnit())) {
                    logisticsOrderBill.setCargoWeightUnit("0");
                }
                if (!StringUtils.hasText(logisticsOrderBill.getCargoVolumeUnit())) {
                    logisticsOrderBill.setCargoVolumeUnit("0");
                }
                logisticsOrderBill.setLogisticsOrderAuditStatus(LogisticsOrderAuditStatusEnum.PASSED.getType());
                logisticsOrderBill.setLogisticsWayGenerateStatus(LogisticsWayGenerateStatusEnum.YES.getType());

                //                Set<LogisticsOrderBill> logisticsOrderBillList = new HashSet<>();
                //                logisticsOrderBillList.add(logisticsOrderBill);
                logisticsOrderBill.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                operLogService.saveLogisticsOrderLog(logisticsOrderBill.getLogisticsOrderId(), "创建物流订单", JSON.toJSONString(logisticsOrderBill), logisticsOrderBill.getCreateByUserId(), logisticsOrderBill.getCreateByEnterpriseId());


                //构建物流运单信息
                List<WayBill> wayBillList = new ArrayList<>();
                List<LogisticsOrderWay> logisticsWayList = new ArrayList<>();
                //                logisticsOrderBill.setWayBillList(wayBillList);
                List<WayBillAddress> wayAddressList = new ArrayList<>();
                List<WayGoodsItem> wayGoodsItemList = new ArrayList<>();
                List<LogisticsNodeInfo> nodeInfoList = new ArrayList<>();

                for (LogisticsWayInfoCreateBO wayInfo : p.getLogisticsOrderWayInfoList()) {
                    WayBill wayBill = beanMapper.map(wayInfo, WayBill.class);
                    wayBill.setWayBillId(generator.nextId());
                    wayBill.setWayBillSn("YD" + wayBill.getWayBillId());
                    wayBill.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayBill.setCreateByUserId(p.getCreateByUserId());
                    wayBill.setCreateTime(now);
                    wayBill.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayBill.setUpdateByUserId(p.getCreateByUserId());
                    wayBill.setUpdateTime(now);
                    wayBill.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                    //                    wayBill.setLogisticsOrderBillList(logisticsOrderBillList);
                    wayBill.setDeliverTime(now);

                    WayBillAddress wayDeliveryAddress = beanMapper.map(p.getDeliveryAddress(), WayBillAddress.class);
                    wayDeliveryAddress.setWayBillId(wayBill.getWayBillId());
                    wayDeliveryAddress.setLogisticsAddressType(LogisticsAddressTypeEnum.START_POINT.getType());
                    wayDeliveryAddress.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayDeliveryAddress.setCreateByUserId(p.getCreateByUserId());
                    wayDeliveryAddress.setCreateTime(now);
                    wayDeliveryAddress.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayDeliveryAddress.setUpdateByUserId(p.getCreateByUserId());
                    wayDeliveryAddress.setUpdateTime(now);
                    wayDeliveryAddress.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                    wayAddressList.add(wayDeliveryAddress);
                    //收货地址信息
                    WayBillAddress wayReverseAddress = beanMapper.map(p.getReceiveAddress(), WayBillAddress.class);
                    wayReverseAddress.setWayBillId(wayBill.getWayBillId());
                    wayReverseAddress.setLogisticsAddressType(LogisticsAddressTypeEnum.END_POINT.getType());
                    wayReverseAddress.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayReverseAddress.setCreateByUserId(p.getCreateByUserId());
                    wayReverseAddress.setCreateTime(now);
                    wayReverseAddress.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                    wayReverseAddress.setUpdateByUserId(p.getCreateByUserId());
                    wayReverseAddress.setUpdateTime(now);
                    wayReverseAddress.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                    wayAddressList.add(wayReverseAddress);

                    // 商品信息
                    if (!CollectionUtils.isEmpty(wayInfo.getGoodsItems())) {
                        if (null == wayBill.getCargoCount()) {
                            wayBill.setCargoCount(wayInfo.getGoodsItems().stream().map(g -> {
                                        if (null == g.getCargoCount()) {
                                            g.setCargoCount(1);
                                        }
                                        if (null == g.getCargoCountUnit()) {
                                            g.setCargoCountUnit("件");
                                        }
                                        return g.getCargoCount();
                                    }
                            ).reduce(Integer::sum).get());
                        }
                        if (null == wayBill.getCargoWeight()) {
                            wayBill.setCargoWeight(wayInfo.getGoodsItems().stream().map(g -> {
                                if (null == g.getCargoWeight()) {
                                    g.setCargoWeight(BigDecimal.ZERO);
                                }
                                if (!StringUtils.hasText(g.getCargoWeightUnit())) {
                                    g.setCargoWeightUnit("0");
                                }
                                // 默认重量单位是公斤
                                g.setCargoWeight(BigDecimalUtil.divide(g.getCargoWeight(), 3));
                                return g.getCargoWeight();
                            }).reduce(BigDecimal::add).get());
                        }
                        if (null == wayBill.getCargoVolume()) {
                            wayBill.setCargoVolume(wayInfo.getGoodsItems().stream().map(g -> {
                                if (null == g.getCargoVolume()) {
                                    g.setCargoVolume(BigDecimal.ZERO);
                                }
                                if (!StringUtils.hasText(g.getCargoVolumeUnit())) {
                                    g.setCargoVolumeUnit("0");
                                }
                                // 默认体积单位是m³
                                g.setCargoVolume(BigDecimalUtil.divide(g.getCargoVolume(), 3 * 3));
                                return g.getCargoVolume();

                            }).reduce(BigDecimal::add).get());
                        }
                        wayGoodsItemList.addAll(wayInfo.getGoodsItems().stream().map(g -> {
                            WayGoodsItem goodsItem = beanMapper.map(g, WayGoodsItem.class);
                            goodsItem.setCreateByEnterpriseId(p.getCreateByEnterpriseId());
                            goodsItem.setCreateByUserId(p.getCreateByUserId());
                            goodsItem.setCreateTime(now);
                            goodsItem.setUpdateByEnterpriseId(p.getCreateByEnterpriseId());
                            goodsItem.setUpdateByUserId(p.getCreateByUserId());
                            goodsItem.setUpdateTime(now);
                            goodsItem.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                            return goodsItem;

                        }).toList());
                    }
                    if (null == wayBill.getCargoCount()) {
                        wayBill.setCargoCount(1);
                    }
                    if (null == wayBill.getCargoWeight()) {
                        wayBill.setCargoWeight(BigDecimal.ZERO);
                    }
                    if (null == wayBill.getCargoVolume()) {
                        wayBill.setCargoVolume(BigDecimal.ZERO);
                    }
                    if (null == wayBill.getCargoCountUnit()) {
                        wayBill.setCargoCountUnit("件");
                    }
                    if (!StringUtils.hasText(wayBill.getCargoWeightUnit())) {
                        wayBill.setCargoWeightUnit("0");
                    }
                    if (!StringUtils.hasText(wayBill.getCargoVolumeUnit())) {
                        wayBill.setCargoVolumeUnit("0");
                    }

                    wayBill.setWayBillStatus(WayBillStatusEnum.NOT_RECEIVED_ORDER.getType());
                    if (Objects.equals(wayBill.getTransType(), TransType.EXPRESS.getType())) {
                        wayBill.setWayHandleType(WayHandleTypeEnum.KD100.getType());
                        wayBill.setWayBillStatus(WayBillStatusEnum.RECEIVED_ORDER.getType());
                    } else if (Objects.equals(wayBill.getTransType(), TransType.FT.getType())) {
                        wayBill.setWayHandleType(WayHandleTypeEnum.MP.getType());
                    }
                    operLogService.saveWayLog(wayBill.getWayBillId(), "创建运单", JSON.toJSONString(wayBill), wayBill.getCreateByUserId(), wayBill.getCreateByEnterpriseId());
                    //记录创建节点
                    String nodeDetail = driverOperateNodeService.getOperateDes(WayBillStatusEnum.NOT_RECEIVED_ORDER.getType());
                    LogisticsNodeInfo logisticsNodeInfo = new LogisticsNodeInfo();
                    logisticsNodeInfo.setWayBillId(wayBill.getWayBillId());
                    logisticsNodeInfo.setLogisticsBillNo(wayBill.getLogisticsBillNo());
                    logisticsNodeInfo.setLogisticsEnterpriseCode(wayBill.getLogisticsEnterpriseCode());
                    logisticsNodeInfo.setNodeDetail(nodeDetail);
                    logisticsNodeInfo.setTrackData(null);
                    logisticsNodeInfo.setNodeStatus(wayBill.getWayBillStatus());
                    logisticsNodeInfo.setNodeTime(LocalDateTime.now());
                    logisticsNodeInfo.setCreateByUserId(wayBill.getCreateByUserId());
                    logisticsNodeInfo.setCreateByEnterpriseId(wayBill.getCreateByEnterpriseId());
                    logisticsNodeInfo.setCreateTime(LocalDateTime.now());
                    logisticsNodeInfo.setUpdateByUserId(wayBill.getCreateByUserId());
                    logisticsNodeInfo.setUpdateByEnterpriseId(wayBill.getCreateByEnterpriseId());
                    logisticsNodeInfo.setUpdateTime(LocalDateTime.now());
                    logisticsNodeInfo.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                    logisticsNodeInfo.setNodeUniqueCode(logisticsNodeInfo.getNodeStatus() + "_" + LocalDateUtil.dateTime2String(logisticsNodeInfo.getNodeTime(), "yyyy-MM-dd HH:mm"));
                    nodeInfoList.add(logisticsNodeInfo);
                    wayBillList.add(wayBill);

                    LogisticsOrderWay orderWay = new LogisticsOrderWay();
                    orderWay.setLogisticsOrderId(logisticsOrderBill.getLogisticsOrderId());
                    orderWay.setWayBillId(wayBill.getWayBillId());
                    orderWay.setCreateByUserId(wayBill.getCreateByUserId());
                    orderWay.setCreateByEnterpriseId(wayBill.getCreateByEnterpriseId());
                    orderWay.setCreateTime(LocalDateTime.now());
                    orderWay.setUpdateByUserId(wayBill.getCreateByUserId());
                    orderWay.setUpdateByEnterpriseId(wayBill.getCreateByEnterpriseId());
                    orderWay.setUpdateTime(LocalDateTime.now());
                    orderWay.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
                    logisticsWayList.add(orderWay);
                }

                return logisticsOrderBillMapper.save(logisticsOrderBill)
                        .thenMany(Flux.fromIterable(logisticsAddressesList))
                        .buffer()
                        .flatMap(addressList -> logisticsAddressMapper.saveAll(addressList))
                        .thenMany(Flux.fromIterable(goodsItemList))
                        .buffer()
                        .flatMap(goodsList -> logisticsGoodsItemMapper.saveAll(goodsList))
                        .thenMany(Flux.fromIterable(logisticsWayList))
                        .buffer()
                        .flatMap(lwList -> logisticsOrderWayMapper.saveAll(lwList))
                        .thenMany(Flux.fromIterable(wayBillList))
                        .buffer()
                        .flatMap(wlist -> wayBillMapper.saveAll(wlist))
                        .thenMany(Flux.fromIterable(wayAddressList))
                        .buffer()
                        .flatMap(waList -> wayBillAddressMapper.saveAll(waList))
                        .thenMany(Flux.fromIterable(wayGoodsItemList))
                        .buffer()
                        .flatMap(wGoodsList -> wayGoodsItemMapper.saveAll(wGoodsList))
                        .thenMany(Flux.fromIterable(nodeInfoList))
                        .buffer()
                        .flatMap(nl -> logisticsNodeInfoMapper.saveAll(nl))
                        .onErrorMap(original -> new BusinessException(ResultType.FAILURE.code(), original.getMessage(), original))
                        .then(Mono.just(ActionResult.success(logisticsOrderBill.getLogisticsOrderId())));
            }
        });
    }


    @Autowired
    public void setLogisticsOrderBillMapper(LogisticsOrderBillMapper logisticsOrderBillMapper) {
        this.logisticsOrderBillMapper = logisticsOrderBillMapper;
    }

    @Autowired
    public void setAmapService(AmapService amapService) {
        this.amapService = amapService;
    }

    @Autowired
    public void setBeanMapper(BeanMapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    @Autowired
    public void setGenerator(SnowflakeGenerator generator) {
        this.generator = generator;
    }

    @Autowired
    public void setLogisticsGoodsItemMapper(LogisticsGoodsItemMapper logisticsGoodsItemMapper) {
        this.logisticsGoodsItemMapper = logisticsGoodsItemMapper;
    }

    @Autowired
    public void setLogisticsAddressMapper(LogisticsAddressMapper logisticsAddressMapper) {
        this.logisticsAddressMapper = logisticsAddressMapper;
    }

    @Autowired
    public void setLogisticsOrderWayMapper(LogisticsOrderWayMapper logisticsOrderWayMapper) {
        this.logisticsOrderWayMapper = logisticsOrderWayMapper;
    }

    @Autowired
    public void setWayBillMapper(WayBillMapper wayBillMapper) {
        this.wayBillMapper = wayBillMapper;
    }

    @Autowired
    public void setWayGoodsItemMapper(WayGoodsItemMapper wayGoodsItemMapper) {
        this.wayGoodsItemMapper = wayGoodsItemMapper;
    }

    @Autowired
    public void setWayBillAddressMapper(WayBillAddressMapper wayBillAddressMapper) {
        this.wayBillAddressMapper = wayBillAddressMapper;
    }

    @Autowired
    public void setLogisticsNodeInfoMapper(LogisticsNodeInfoMapper logisticsNodeInfoMapper) {
        this.logisticsNodeInfoMapper = logisticsNodeInfoMapper;
    }

    @Autowired
    public void setOperLogService(OperLogService operLogService) {
        this.operLogService = operLogService;
    }

    @Autowired
    public void setDriverOperateNodeService(DriverOperateNodeService driverOperateNodeService) {
        this.driverOperateNodeService = driverOperateNodeService;
    }
}
