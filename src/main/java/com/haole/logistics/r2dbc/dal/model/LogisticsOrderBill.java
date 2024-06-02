package com.haole.logistics.r2dbc.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "logistics_order_bill")
public class LogisticsOrderBill implements Serializable {

    private static final long serialVersionUID = 7753029359295510252L;

    /**
     * 物流订单id
     */
    @Id
    @Column(name = "logistics_order_id")
    private Long logisticsOrderId;
    /**
     * 物流单号
     * 来自三方，或者手工输入
     */
    @Column(name = "logistics_bill_no")
    private String logisticsBillNo;
    /**
     * source_system: zc表示中平台，zg表示新平台
     */
    @Column(name = "source_system")
    private String sourceSystem;
    /**
     * 来源单据单号
     */
    @Column(name = "source_bill_sn")
    private String sourceBillSn;
    /**
     * 关联来源单号
     */
    @Column(name = "parent_source_bill_no")
    private String parentSourceBillNo;
    /**
     * 来源类型
     * 业务类型, 10手工发货,20下单
     */
    @Column(name = "source_bill_type")
    private Integer sourceBillType;
    /**
     * 源单据创建时间
     */
    @Column(name = "source_bill_create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sourceBillCreateTime;
    /**
     * 物流订单状态
     * 10:未接单,
     * 20:已取消
     * 30:已接单
     * 40:已配送
     */
    @Column(name = "logistics_order_status")
    private Integer logisticsOrderStatus;
    /**
     * 物流订单审核状态 1 待审核 2  审核通过  3 审核不通过
     */
    @Column(name = "logistics_order_audit_status")
    private Integer logisticsOrderAuditStatus;
    /**
     * 物流运单生成状态0:未生成运单,1:已生成运单
     */
    @Column(name = "logistics_way_generate_status")
    private Integer logisticsWayGenerateStatus;
    /**
     * 运输方式
     * 1:快递运输
     * 2:零担运输
     * 3:整车运输
     */
    @Column(name = "trans_type")
    private Integer transType;
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
    private String vcLn;
    /**
     * 车型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;
    /**
     * 物流联系人(司机)
     */
    @Column(name = "driver_name")
    private String driverName;
    /**
     * 物流联系电话
     */
    @Column(name = "driver_contact_tel")
    private String driverContactTel;
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
     * 预计到货时间
     */
    @Column(name = "min_estimate_arrival_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate minEstimateArrivalTime;
    /**
     * 预计最晚到货时间
     */
    @Column(name = "max_estimate_arrival_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate maxEstimateArrivalTime;
    /**
     * 货物类型
     */
    @Column(name = "cargo_type")
    private String cargoType;
    /**
     * 货物件数
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
//            })
//    @JoinTable(
//            name = "logistics_order_way",
//            joinColumns = @JoinColumn(name = "logistics_order_id"),
//            inverseJoinColumns = @JoinColumn(name = "way_bill_id")
//    )
//    private Set<WayBill> wayBillList;


    public Long getLogisticsOrderId() {
        return logisticsOrderId;
    }

    public void setLogisticsOrderId(Long logisticsOrderId) {
        this.logisticsOrderId = logisticsOrderId;
    }


    public String getLogisticsBillNo() {
        return logisticsBillNo;
    }

    public void setLogisticsBillNo(String logisticsBillNo) {
        this.logisticsBillNo = logisticsBillNo;
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


    public Integer getLogisticsOrderStatus() {
        return logisticsOrderStatus;
    }

    public void setLogisticsOrderStatus(Integer logisticsOrderStatus) {
        this.logisticsOrderStatus = logisticsOrderStatus;
    }


    public Integer getLogisticsOrderAuditStatus() {
        return logisticsOrderAuditStatus;
    }

    public void setLogisticsOrderAuditStatus(Integer logisticsOrderAuditStatus) {
        this.logisticsOrderAuditStatus = logisticsOrderAuditStatus;
    }


    public Integer getLogisticsWayGenerateStatus() {
        return logisticsWayGenerateStatus;
    }

    public void setLogisticsWayGenerateStatus(Integer logisticsWayGenerateStatus) {
        this.logisticsWayGenerateStatus = logisticsWayGenerateStatus;
    }


    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
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


    public String getVcLn() {
        return vcLn;
    }

    public void setVcLn(String vcLn) {
        this.vcLn = vcLn;
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


    public String getDriverContactTel() {
        return driverContactTel;
    }

    public void setDriverContactTel(String driverContactTel) {
        this.driverContactTel = driverContactTel;
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

//    public Set<WayBill> getWayBillList() {
//        return wayBillList;
//    }
//
//    public void setWayBillList(Set<WayBill> wayBillList) {
//        this.wayBillList = wayBillList;
//    }
}
