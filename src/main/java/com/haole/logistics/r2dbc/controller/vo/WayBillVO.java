package com.haole.logistics.r2dbc.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: WayBillVO
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/31 21:05
 */
public class WayBillVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7413373254522443115L;

    /**
     * 物流运单id
     */
    private Long wayBillId;

    /**
     * 运单编号
     */
    private String wayBillSn;

    /**
     * 物流单号 来自三方，或者手工输入
     */
    private String logisticsBillNo;

    /**
     * 状态,1:待接单,10接单,20完成装车,30确认发车,31完成卸车,40已签收,50签收异常
     * 1:待接单,2:已取消,3:已决绝,4:待审核
     * 10:已接单(待装车)
     * 20:揽件(待发车),21:揽件失败
     * 30:运输中(在途、待卸车),31:待签收
     * 40:已签收
     * 50签收异常
     */
    private Integer wayBillStatus;

    private String wayBillStatusStr;

    /**
     * 运输方式,1:快递运输,2:零担运输,3:整车运输
     */
    private Integer transType;

    /**
     * 运输方式中文描述
     */
    private String transTypeStr;

    /**
     * 处理方式
     * 1:快递100
     * 2: 物流小程序
     * 3: 供应商运力
     * 4: 承运商
     */
    private Integer wayHandleType;

    /**
     * 物流公司名称
     */
    private String logisticsEnterpriseName;

    /**
     * 物流公司编码
     */
    private String logisticsEnterpriseCode;

    /**
     * 承运人
     * <p>
     * 快递logisticsEnterpriseName
     * 小程序driverName
     */
    private String carrier;

    /**
     * 车牌颜色,1蓝牌,2黄牌,3黄绿色
     */
    private Integer licensePlateColor;

    /**
     * 车辆牌号
     */
    private String vcln;

    /**
     * 车型
     */
    private String vehicleType;

    /**
     * 司机姓名
     */
    private String driverName;

    /**
     * 司机电话
     */
    private String driverTel;

    /**
     * 驾照类型
     */
    private String driverLicenseType;

    /**
     * 驾驶证号
     */
    private String driverLicenseNumber;

    /**
     * 行驶证号
     */
    private String vehicleLicenseNumber;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 货物类型
     */
    private String cargoType;

    /**
     * 货物总件数
     */
    private Integer cargoCount;

    /**
     * 货物重量
     */
    private BigDecimal cargoWeight;

    /**
     * 货物体积
     */
    private BigDecimal cargoVolume;

    /**
     * 货物总件数单位
     */
    private String cargoCountUnit;

    /**
     * 货物重量单位
     */
    private String cargoWeightUnit;

    /**
     * 货物体积单位
     */
    private String cargoVolumeUnit;

    /**
     * 发货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deliverTime;

    /**
     * 收货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiveTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    private String sourceSystem;

    /**
     * 来源单据单号
     */
    private String sourceBillSn;

    /**
     * 关联来源单号
     */
    private String parentSourceBillNo;

    /**
     * 来源类型,10手工发货,20下单
     */
    private Integer sourceBillType;


    /**
     * 源单据创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime sourceBillCreateTime;

    /**
     * 预计到货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate minEstimateArrivalTime;

    /**
     * 预计最晚到货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate maxEstimateArrivalTime;

    /**
     * 发货方企业编码
     */
    private String deliverEnterpriseCode;

    /**
     * 发货方企业名称
     */
    private String deliverEnterpriseName;


    /**
     * 发货联系人
     */
    private String deliverPersonName;

    /**
     * 发货联系电话
     */
    private String deliverPersonTel;

    /**
     * 发货省code
     */
    private String deliverProvinceCode;

    /**
     * 发货省
     */
    private String deliverProvinceName;

    /**
     * 发货市code
     */
    private String deliverCityCode;

    /**
     * 发货市
     */
    private String deliverCityName;

    /**
     * 发货县code
     */
    private String deliverCountyCode;

    /**
     * 发货县
     */
    private String deliverCountyName;

    /**
     * 发货镇code
     */
    private String deliverTownCode;

    /**
     * 发货镇code
     */
    private String deliverTownName;

    /**
     * 发货详细地址
     */
    private String deliverDetailAddress;

    /**
     * 收货方企业编码
     */
    private String receiverEnterpriseCode;

    /**
     * 收货方企业名称
     */
    private String receiverEnterpriseName;


    /**
     * 收货联系人
     */
    private String receiverPersonName;

    /**
     * 收货联系电话
     */
    private String receiverPersonTel;

    /**
     * 收货省code
     */
    private String receiverProvinceCode;

    /**
     * 收货省
     */
    private String receiverProvinceName;

    /**
     * 收货市code
     */
    private String receiverCityCode;

    /**
     * 收货市
     */
    private String receiverCityName;

    /**
     * 收货县code
     */
    private String receiverCountyCode;

    /**
     * 收货县
     */
    private String receiverCountyName;

    /**
     * 收货镇code
     */
    private String receiverTownCode;

    /**
     * 收货镇code
     */
    private String receiverTownName;

    /**
     * 收货详细地址
     */
    private String receiverDetailAddress;

    /**
     * 运单轨迹是否存在     0 无轨迹   1 有轨迹
     */
    private Integer traceFlag;
    /**
     * 运单轨迹是否存在文本
     */
    private String traceFlagStr;

    /**
     * 运单首次记录轨迹时间
     */
    private LocalDateTime traceFirstTime;

    public Long getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(Long wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getWayBillSn() {
        return wayBillSn;
    }

    public void setWayBillSn(String wayBillSn) {
        this.wayBillSn = wayBillSn;
    }

    public String getLogisticsBillNo() {
        return logisticsBillNo;
    }

    public void setLogisticsBillNo(String logisticsBillNo) {
        this.logisticsBillNo = logisticsBillNo;
    }

    public Integer getWayBillStatus() {
        return wayBillStatus;
    }

    public void setWayBillStatus(Integer wayBillStatus) {
        this.wayBillStatus = wayBillStatus;
    }

    public String getWayBillStatusStr() {
        return wayBillStatusStr;
    }

    public void setWayBillStatusStr(String wayBillStatusStr) {
        this.wayBillStatusStr = wayBillStatusStr;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public String getTransTypeStr() {
        return transTypeStr;
    }

    public void setTransTypeStr(String transTypeStr) {
        this.transTypeStr = transTypeStr;
    }

    public Integer getWayHandleType() {
        return wayHandleType;
    }

    public void setWayHandleType(Integer wayHandleType) {
        this.wayHandleType = wayHandleType;
    }

    public String getLogisticsEnterpriseName() {
        return logisticsEnterpriseName;
    }

    public void setLogisticsEnterpriseName(String logisticsEnterpriseName) {
        this.logisticsEnterpriseName = logisticsEnterpriseName;
    }

    public String getLogisticsEnterpriseCode() {
        return logisticsEnterpriseCode;
    }

    public void setLogisticsEnterpriseCode(String logisticsEnterpriseCode) {
        this.logisticsEnterpriseCode = logisticsEnterpriseCode;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(Integer licensePlateColor) {
        this.licensePlateColor = licensePlateColor;
    }

    public String getVcln() {
        return vcln;
    }

    public void setVcln(String vcln) {
        this.vcln = vcln;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverTel() {
        return driverTel;
    }

    public void setDriverTel(String driverTel) {
        this.driverTel = driverTel;
    }

    public String getDriverLicenseType() {
        return driverLicenseType;
    }

    public void setDriverLicenseType(String driverLicenseType) {
        this.driverLicenseType = driverLicenseType;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getVehicleLicenseNumber() {
        return vehicleLicenseNumber;
    }

    public void setVehicleLicenseNumber(String vehicleLicenseNumber) {
        this.vehicleLicenseNumber = vehicleLicenseNumber;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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

    public String getCargoWeightUnit() {
        return cargoWeightUnit;
    }

    public void setCargoWeightUnit(String cargoWeightUnit) {
        this.cargoWeightUnit = cargoWeightUnit;
    }

    public String getCargoVolumeUnit() {
        return cargoVolumeUnit;
    }

    public void setCargoVolumeUnit(String cargoVolumeUnit) {
        this.cargoVolumeUnit = cargoVolumeUnit;
    }

    public LocalDateTime getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(LocalDateTime deliverTime) {
        this.deliverTime = deliverTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

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

    public Integer getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(Integer sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public LocalDateTime getSourceBillCreateTime() {
        return sourceBillCreateTime;
    }

    public void setSourceBillCreateTime(LocalDateTime sourceBillCreateTime) {
        this.sourceBillCreateTime = sourceBillCreateTime;
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

    public String getDeliverEnterpriseCode() {
        return deliverEnterpriseCode;
    }

    public void setDeliverEnterpriseCode(String deliverEnterpriseCode) {
        this.deliverEnterpriseCode = deliverEnterpriseCode;
    }

    public String getDeliverEnterpriseName() {
        return deliverEnterpriseName;
    }

    public void setDeliverEnterpriseName(String deliverEnterpriseName) {
        this.deliverEnterpriseName = deliverEnterpriseName;
    }

    public String getDeliverPersonName() {
        return deliverPersonName;
    }

    public void setDeliverPersonName(String deliverPersonName) {
        this.deliverPersonName = deliverPersonName;
    }

    public String getDeliverPersonTel() {
        return deliverPersonTel;
    }

    public void setDeliverPersonTel(String deliverPersonTel) {
        this.deliverPersonTel = deliverPersonTel;
    }

    public String getDeliverProvinceCode() {
        return deliverProvinceCode;
    }

    public void setDeliverProvinceCode(String deliverProvinceCode) {
        this.deliverProvinceCode = deliverProvinceCode;
    }

    public String getDeliverProvinceName() {
        return deliverProvinceName;
    }

    public void setDeliverProvinceName(String deliverProvinceName) {
        this.deliverProvinceName = deliverProvinceName;
    }

    public String getDeliverCityCode() {
        return deliverCityCode;
    }

    public void setDeliverCityCode(String deliverCityCode) {
        this.deliverCityCode = deliverCityCode;
    }

    public String getDeliverCityName() {
        return deliverCityName;
    }

    public void setDeliverCityName(String deliverCityName) {
        this.deliverCityName = deliverCityName;
    }

    public String getDeliverCountyCode() {
        return deliverCountyCode;
    }

    public void setDeliverCountyCode(String deliverCountyCode) {
        this.deliverCountyCode = deliverCountyCode;
    }

    public String getDeliverCountyName() {
        return deliverCountyName;
    }

    public void setDeliverCountyName(String deliverCountyName) {
        this.deliverCountyName = deliverCountyName;
    }

    public String getDeliverTownCode() {
        return deliverTownCode;
    }

    public void setDeliverTownCode(String deliverTownCode) {
        this.deliverTownCode = deliverTownCode;
    }

    public String getDeliverTownName() {
        return deliverTownName;
    }

    public void setDeliverTownName(String deliverTownName) {
        this.deliverTownName = deliverTownName;
    }

    public String getDeliverDetailAddress() {
        return deliverDetailAddress;
    }

    public void setDeliverDetailAddress(String deliverDetailAddress) {
        this.deliverDetailAddress = deliverDetailAddress;
    }

    public String getReceiverEnterpriseCode() {
        return receiverEnterpriseCode;
    }

    public void setReceiverEnterpriseCode(String receiverEnterpriseCode) {
        this.receiverEnterpriseCode = receiverEnterpriseCode;
    }

    public String getReceiverEnterpriseName() {
        return receiverEnterpriseName;
    }

    public void setReceiverEnterpriseName(String receiverEnterpriseName) {
        this.receiverEnterpriseName = receiverEnterpriseName;
    }

    public String getReceiverPersonName() {
        return receiverPersonName;
    }

    public void setReceiverPersonName(String receiverPersonName) {
        this.receiverPersonName = receiverPersonName;
    }

    public String getReceiverPersonTel() {
        return receiverPersonTel;
    }

    public void setReceiverPersonTel(String receiverPersonTel) {
        this.receiverPersonTel = receiverPersonTel;
    }

    public String getReceiverProvinceCode() {
        return receiverProvinceCode;
    }

    public void setReceiverProvinceCode(String receiverProvinceCode) {
        this.receiverProvinceCode = receiverProvinceCode;
    }

    public String getReceiverProvinceName() {
        return receiverProvinceName;
    }

    public void setReceiverProvinceName(String receiverProvinceName) {
        this.receiverProvinceName = receiverProvinceName;
    }

    public String getReceiverCityCode() {
        return receiverCityCode;
    }

    public void setReceiverCityCode(String receiverCityCode) {
        this.receiverCityCode = receiverCityCode;
    }

    public String getReceiverCityName() {
        return receiverCityName;
    }

    public void setReceiverCityName(String receiverCityName) {
        this.receiverCityName = receiverCityName;
    }

    public String getReceiverCountyCode() {
        return receiverCountyCode;
    }

    public void setReceiverCountyCode(String receiverCountyCode) {
        this.receiverCountyCode = receiverCountyCode;
    }

    public String getReceiverCountyName() {
        return receiverCountyName;
    }

    public void setReceiverCountyName(String receiverCountyName) {
        this.receiverCountyName = receiverCountyName;
    }

    public String getReceiverTownCode() {
        return receiverTownCode;
    }

    public void setReceiverTownCode(String receiverTownCode) {
        this.receiverTownCode = receiverTownCode;
    }

    public String getReceiverTownName() {
        return receiverTownName;
    }

    public void setReceiverTownName(String receiverTownName) {
        this.receiverTownName = receiverTownName;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }

    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public Integer getTraceFlag() {
        return traceFlag;
    }

    public void setTraceFlag(Integer traceFlag) {
        this.traceFlag = traceFlag;
    }

    public String getTraceFlagStr() {
        return traceFlagStr;
    }

    public void setTraceFlagStr(String traceFlagStr) {
        this.traceFlagStr = traceFlagStr;
    }

    public LocalDateTime getTraceFirstTime() {
        return traceFirstTime;
    }

    public void setTraceFirstTime(LocalDateTime traceFirstTime) {
        this.traceFirstTime = traceFirstTime;
    }
}
