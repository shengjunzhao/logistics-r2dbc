package com.haole.logistics.r2dbc.controller.param.logistics;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

public class LogisticAddressCreateParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -8911064702222511912L;

    /**
     * 企业编码
     */
    @NotEmpty(message = "企业编码不能为空")
    @Size(max =  50, message = "企业编码超过合法长度")
    private String enterpriseCode;


    /**
     * 企业名称
     */
    @NotEmpty(message = "企业名称不能为空")
    @Size(max =  50, message = "企业名称超过合法长度")
    private String enterpriseName;


    /**
     * 联系人
     */
    @NotEmpty(message = "联系人不能为空")
    @Size(max =  30, message = "联系人超过合法长度")
    private String contractName;

    /**
     * 联系电话
     */
    @NotEmpty(message = "联系电话不能为空")
    @Size(max =  30, message = "联系电话超过合法长度")
    private String contactTel;


    /**
     * 省code
     */
    @NotEmpty(message = "省code不能为空")
    @Size(max =  50, message = "省code超过合法长度")
    private String provinceCode;

    /**
     * 省名称
     */
    @NotEmpty(message = "省名称不能为空")
    @Size(max =  50, message = "省名称超过合法长度")
    private String provinceName;


    /**
     * 市code
     */
    @NotEmpty(message = "市code不能为空")
    @Size(max =  50, message = "市code超过合法长度")
    private String cityCode;


    /**
     * 市名称
     */
    @NotEmpty(message = "市名称不能为空")
    @Size(max =  50, message = "市名称超过合法长度")
    private String cityName;


    /**
     * 县code
     */
    @NotEmpty(message = "县code不能为空")
    @Size(max =  50, message = "县code超过合法长度")
    private String countyCode;

    /**
     * 县名称
     */
    @NotEmpty(message = "县名称不能为空")
    @Size(max =  50, message = "县名称超过合法长度")
    private String countyName;


    /**
     * 镇code
     */
//    @NotEmpty(message = "镇code不能为空")
    @Size(max =  50, message = "镇code超过合法长度")
    private String townCode;


    /**
     * 镇名称
     */
//    @NotEmpty(message = "镇名称不能为空")
    @Size(max =  50, message = "镇名称超过合法长度")
    private String townName;


    /**
     * 详细地址
     */
    @NotEmpty(message = "详细地址不能为空")
    @Size(max =  100, message = "详细地址超过合法长度")
    private String detailAddress;

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }
}
