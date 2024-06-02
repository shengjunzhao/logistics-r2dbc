package com.haole.logistics.r2dbc.dal.bo.logistics;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class LogisticsWayInfoCreateBO implements Serializable {
    @Serial
    private static final long serialVersionUID = 9115620947405244238L;

    /**
     * 物流单号
     */
    @Size(max = 50, message = "物流单号超过合法长度")
    private String logisticsBillNo;


    /**
     * 运输方式
     * 1:快递运输
     * 2:零担运输
     * 3:整车运输
     */
    @NotNull(message = "运输方式不能为空")
    private Integer transType;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 物流公司名称
     */
    @Size(max = 100, message = "物流公司名称超过合法长度")
    private String logisticsEnterpriseName;


    /**
     * 物流公司编码
     */
    @Size(max = 50, message = "物流公司编码超过合法长度")
    private String logisticsEnterpriseCode;


    /**
     * 车牌颜色
     */
    private Integer licensePlateColor;

    /**
     * 车辆牌号
     */
    @Size(max = 8, message = "车辆牌号超过合法长度")
    @Pattern(regexp = "^(([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z](([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-Z][A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳使领]))$", message = "车辆牌号输入不正确!")
    private String vcln;

    /**
     * 车型
     */
    @Size(max = 20, message = "车型超过合法长度")
    private String vehicleType;


    /**
     * 驾照类型
     */
    @Size(max = 5, message = "驾照类型超过合法长度")
    private String driverLicenseType;

    /**
     * 驾驶证号
     */
    @Size(max = 18, message = "驾驶证号为18位数字,请检查后重新填写!")
    @Pattern(regexp = "^$|^[1-9]\\d{16}[\\dXx]$", message = "驾驶证号为18位数字,请检查后重新填写!!")
    private String driverLicenseNumber;


    /**
     * 行驶证号
     */
    @Size(max = 13, message = "行驶证号为12或13位数字,请检查后重新填写!")
    @Pattern(regexp = "^$|^\\d{12,13}$", message = "行驶证号为12或13位数字,请检查后重新填写!!")
    private String vehicleLicenseNumber;


    /**
     * 物流联系人(司机)
     */
    @Size(max = 8, message = "司机姓名输入过长,只能填入8个字符!!")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z▪•·]*$", message = "物流联系人(司机)输入有误,只能输入汉字和英文以及[·]")
    private String driverName;


    /**
     * 物流联系电话
     */
    @Pattern(regexp = "^(\\s*|1[3456789]\\d{9})$", message = "物流联系电话超过合法长度,只能输入11位的手机号码,请检查后重新输入!!")
    private String driverTel;

    /**
     * 货物类型
     */
    // @NotEmpty(message = "货物类型不能为空")
    @Size(max = 300, message = "货物类型超过合法长度")
    private String cargoType;
    /**
     * 商品信息
     */
    @Valid
//    @NotNull(message = "商品信息不能为空")
//    @Size(min = 1, message = "至少需要有一个商品")
    private List<LogisticsGoodsCreateBO> goodsItems;

    public String getLogisticsBillNo() {
        return logisticsBillNo;
    }

    public void setLogisticsBillNo(String logisticsBillNo) {
        this.logisticsBillNo = logisticsBillNo;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
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

    public void setVcln(String vcln) {
        this.vcln = vcln;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public List<LogisticsGoodsCreateBO> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<LogisticsGoodsCreateBO> goodsItems) {
        this.goodsItems = goodsItems;
    }
}
