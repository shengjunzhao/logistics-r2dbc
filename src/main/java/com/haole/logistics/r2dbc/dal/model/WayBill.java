package com.haole.logistics.r2dbc.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "way_bill")
public class WayBill implements Serializable {

    private static final long serialVersionUID = 5928147218741637921L;

    /**
     * 物流运单id
     */
    @Id
    @Column(name = "way_bill_id")
    private Long wayBillId;
    /**
     * 运单编号
     */
    @Column(name = "way_bill_sn")
    private String wayBillSn;
    /**
     * 物流单号
     * 来自三方，或者手工输入
     */
    @Column(name = "logistics_bill_no")
    private String logisticsBillNo;
    /**
     * 状态
     * 1:未接单,2:已取消,3:已决绝,4:待审核
     * 10:已接单(待装车)
     * 20:揽件(待发车),21:揽件失败
     * 30:运输中(在途、待卸车),31:待签收
     * 40:签收
     * 50签收异常
     */
    @Column(name = "way_bill_status")
    private Integer wayBillStatus;
    /**
     * 运输方式
     * 1:快递运输
     * 2:零担运输
     * 3:整车运输
     */
    @Column(name = "trans_type")
    private Integer transType;
    /**
     * 处理方式
     * 1:快递100
     * 2: 物流小程序
     * 3: 供应商运力
     * 4: 承运商
     */
    @Column(name = "way_handle_type")
    private Integer wayHandleType;
    /**
     * 调用三方接口状态 0:错误,1:正常
     */
    @Column(name = "third_invoke_status")
    private Integer thirdInvokeStatus;
    /**
     * 物流公司名称
     */
    @Column(name = "logistics_enterprise_name")
    private String logisticsEnterpriseName;
    /**
     * 物流公司编码
     */
    @Column(name = "logistics_enterprise_code")
    private String logisticsEnterpriseCode;
    /**
     * 车牌颜色
     */
    @Column(name = "license_plate_color")
    private Integer licensePlateColor;
    /**
     * 车辆牌号
     */
    @Column(name = "vcln")
    private String vcln;
    /**
     * 车型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;
    /**
     * 司机姓名
     */
    @Column(name = "driver_name")
    private String driverName;
    /**
     * 司机电话
     */
    @Column(name = "driver_tel")
    private String driverTel;
    /**
     * 驾照类型
     */
    @Column(name = "driver_license_type")
    private String driverLicenseType;
    /**
     * 驾驶证号
     */
    @Column(name = "driver_license_number")
    private String driverLicenseNumber;
    /**
     * 行驶证号
     */
    @Column(name = "vehicle_license_number")
    private String vehicleLicenseNumber;
    /**
     * 运费
     */
    @Column(name = "freight")
    private BigDecimal freight;
    /**
     * 货物类型
     */
    @Column(name = "cargo_type")
    private String cargoType;
    /**
     * 货物总件数
     */
    @Column(name = "cargo_count")
    private Integer cargoCount;
    /**
     * 货物重量
     */
    @Column(name = "cargo_weight")
    private BigDecimal cargoWeight;
    /**
     * 货物体积
     */
    @Column(name = "cargo_volume")
    private BigDecimal cargoVolume;
    /**
     * 货物总件数(包裹件数)单位
     */
    @Column(name = "cargo_count_unit")
    private String cargoCountUnit;
    /**
     * 货物体积单位
     */
    @Column(name = "cargo_volume_unit")
    private String cargoVolumeUnit;
    /**
     * 货物重量单位
     */
    @Column(name = "cargo_weight_unit")
    private String cargoWeightUnit;
    /**
     * 发货时间
     */
    @Column(name = "deliver_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime deliverTime;
    /**
     * 收货时间
     */
    @Column(name = "receive_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiveTime;
    /**
     * 收货验证码
     */
    @Column(name = "receive_verification_code")
    private String receiveVerificationCode;
    /**
     * 轨迹标记  0 无轨迹 1 有轨迹
     */
    @Column(name = "trace_flag")
    private Integer traceFlag;
    /**
     * 首次记录轨迹时间
     */
    @Column(name = "trace_first_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime traceFirstTime;
    /**
     * 创建人用户id
     */
    @Column(name = "create_by_user_id")
    private Long createByUserId;
    /**
     * 创建企业id
     */
    @Column(name = "create_by_enterprise_id")
    private Long createByEnterpriseId;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 更新人用户id
     */
    @Column(name = "update_by_user_id")
    private Long updateByUserId;
    /**
     * 更新人企业id
     */
    @Column(name = "update_by_enterprise_id")
    private Long updateByEnterpriseId;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    /**
     * 删除标识 0:未删除 1:已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "wayBillList")
//    private Set<LogisticsOrderBill> logisticsOrderBillList;


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


    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }


    public Integer getWayHandleType() {
        return wayHandleType;
    }

    public void setWayHandleType(Integer wayHandleType) {
        this.wayHandleType = wayHandleType;
    }


    public Integer getThirdInvokeStatus() {
        return thirdInvokeStatus;
    }

    public void setThirdInvokeStatus(Integer thirdInvokeStatus) {
        this.thirdInvokeStatus = thirdInvokeStatus;
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


    public Integer getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(Integer licensePlateColor) {
        this.licensePlateColor = licensePlateColor;
    }


    public String getVcln() {
        return vcln;
    }

    public void setVcln(String vcLn) {
        this.vcln = vcLn;
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


    public String getReceiveVerificationCode() {
        return receiveVerificationCode;
    }

    public void setReceiveVerificationCode(String receiveVerificationCode) {
        this.receiveVerificationCode = receiveVerificationCode;
    }


    public Integer getTraceFlag() {
        return traceFlag;
    }

    public void setTraceFlag(Integer traceFlag) {
        this.traceFlag = traceFlag;
    }


    public LocalDateTime getTraceFirstTime() {
        return traceFirstTime;
    }

    public void setTraceFirstTime(LocalDateTime traceFirstTime) {
        this.traceFirstTime = traceFirstTime;
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


    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    public Long getUpdateByUserId() {
        return updateByUserId;
    }

    public void setUpdateByUserId(Long updateByUserId) {
        this.updateByUserId = updateByUserId;
    }


    public Long getUpdateByEnterpriseId() {
        return updateByEnterpriseId;
    }

    public void setUpdateByEnterpriseId(Long updateByEnterpriseId) {
        this.updateByEnterpriseId = updateByEnterpriseId;
    }


    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

//    public Set<LogisticsOrderBill> getLogisticsOrderBillList() {
//        return logisticsOrderBillList;
//    }
//
//    public void setLogisticsOrderBillList(Set<LogisticsOrderBill> logisticsOrderBillList) {
//        this.logisticsOrderBillList = logisticsOrderBillList;
//    }
}
