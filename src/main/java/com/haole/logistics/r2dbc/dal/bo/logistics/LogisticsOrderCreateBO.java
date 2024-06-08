package com.haole.logistics.r2dbc.dal.bo.logistics;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LogisticsOrderCreateBO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4204638984780059763L;

    /**
     * 来源系统标识
     */
    @NotNull(message = "来源系统标识不能为空")
    @Size(max = 50, message = "来源系统标识超过合法长度")
    private String sourceSystem;

    /**
     * 来源单据单号
     */
    // @NotEmpty(message = "来源单据单号不能为空")
    @Size(max = 500, message = "来源单据单号超过合法长度")
    private String sourceBillSn;

    /**
     * 关联来源单号
     */
    @NotEmpty(message = "关联来源单号不能为空")
    @Size(max = 50, message = "关联来源单号超过合法长度")
    private String parentSourceBillNo;

    /**
     * 源单据创建时间
     */
    @NotNull(message = "源单据创建时间不能为空")
    private LocalDateTime sourceBillCreateTime;


    /**
     * 来源类型
     * 业务类型, 10手工发货,20下单
     */
    @NotNull(message = "来源类型不能为空")
    private Integer sourceBillType;

    /**
     * 运费
     */
    private BigDecimal freight;


    /**
     * 预计最初到货时间
     */
    @NotNull(message = "预计最初到货时间不能为空")
    private LocalDate minEstimateArrivalTime;


    /**
     * 预计最晚到货时间
     */
    @NotNull(message = "预计最晚到货时间不能为空")
    private LocalDate maxEstimateArrivalTime;


    /**
     * 发货方地址信息
     */
    @Valid
    @NotNull(message = "发货方地址信息不能为空")
    private LogisticAddressCreateBO deliveryAddress;


    /**
     * 收货方地址信息
     */
    @Valid
    @NotNull(message = "收货方地址信息不能为空")
    private LogisticAddressCreateBO receiveAddress;

    /**
     * 货物类型
     */
    private String cargoType;
    /**
     * 货物件数
     */
    private Integer cargoCount;
    /**
     * 货物重量
     * 如果用户输入，单位是kg
     */
    private BigDecimal cargoWeight;
    /**
     * 货物体积
     * 如果用户输入，单位是立方米
     */
    private BigDecimal cargoVolume;
    /**
     * 货物总件数(包裹件数)单位
     */
    private String cargoCountUnit;
    /**
     * 货物体积单位
     */
    private String cargoVolumeUnit;
    /**
     * 货物重量单位
     */
    private String cargoWeightUnit;

    /**
     * 商品信息
     */
    @Valid
//    @NotNull(message = "商品信息不能为空")
//    @Size(min = 1, message = "至少需要有一个商品")
    private List<LogisticsGoodsCreateBO> goodsItems;


    /**
     * 运单信息
     */
    @Valid
    @NotNull(message = "运单信息不能为空")
    @Size(min = 1, message = "至少需要有一个运单")
    private List<LogisticsWayInfoCreateBO> logisticsOrderWayInfoList;


    /**
     * 创建人用户id
     */
    @NotNull(message = "创建人用户id不能为空")
    private Long createByUserId;

    /**
     * 创建企业id
     */
    @NotNull(message = "创建企业id不能为空")
    private Long createByEnterpriseId;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceBillSn() {
        return sourceBillSn;
    }

    public void setSourceBillSn(String sourceBillSn) {
        this.sourceBillSn = sourceBillSn;
    }

    public String getParentSourceBillNo() {
        return parentSourceBillNo;
    }

    public void setParentSourceBillNo(String parentSourceBillNo) {
        this.parentSourceBillNo = parentSourceBillNo;
    }

    public LocalDateTime getSourceBillCreateTime() {
        return sourceBillCreateTime;
    }

    public void setSourceBillCreateTime(LocalDateTime sourceBillCreateTime) {
        this.sourceBillCreateTime = sourceBillCreateTime;
    }

    public Integer getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(Integer sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public LocalDate getMinEstimateArrivalTime() {
        return minEstimateArrivalTime;
    }

    public void setMinEstimateArrivalTime(LocalDate minEstimateArrivalTime) {
        this.minEstimateArrivalTime = minEstimateArrivalTime;
    }

    public LocalDate getMaxEstimateArrivalTime() {
        return maxEstimateArrivalTime;
    }

    public void setMaxEstimateArrivalTime(LocalDate maxEstimateArrivalTime) {
        this.maxEstimateArrivalTime = maxEstimateArrivalTime;
    }

    public LogisticAddressCreateBO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(LogisticAddressCreateBO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LogisticAddressCreateBO getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(LogisticAddressCreateBO receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public Integer getCargoCount() {
        return cargoCount;
    }

    public void setCargoCount(Integer cargoCount) {
        this.cargoCount = cargoCount;
    }

    public BigDecimal getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(BigDecimal cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public BigDecimal getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(BigDecimal cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public String getCargoCountUnit() {
        return cargoCountUnit;
    }

    public void setCargoCountUnit(String cargoCountUnit) {
        this.cargoCountUnit = cargoCountUnit;
    }

    public String getCargoVolumeUnit() {
        return cargoVolumeUnit;
    }

    public void setCargoVolumeUnit(String cargoVolumeUnit) {
        this.cargoVolumeUnit = cargoVolumeUnit;
    }

    public String getCargoWeightUnit() {
        return cargoWeightUnit;
    }

    public void setCargoWeightUnit(String cargoWeightUnit) {
        this.cargoWeightUnit = cargoWeightUnit;
    }

    public List<LogisticsGoodsCreateBO> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<LogisticsGoodsCreateBO> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public List<LogisticsWayInfoCreateBO> getLogisticsOrderWayInfoList() {
        return logisticsOrderWayInfoList;
    }

    public void setLogisticsOrderWayInfoList(List<LogisticsWayInfoCreateBO> logisticsOrderWayInfoList) {
        this.logisticsOrderWayInfoList = logisticsOrderWayInfoList;
    }

    public Long getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(Long createByUserId) {
        this.createByUserId = createByUserId;
    }

    public Long getCreateByEnterpriseId() {
        return createByEnterpriseId;
    }

    public void setCreateByEnterpriseId(Long createByEnterpriseId) {
        this.createByEnterpriseId = createByEnterpriseId;
    }

}
