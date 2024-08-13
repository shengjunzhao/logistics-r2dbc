package com.haole.logistics.r2dbc.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "way_bill_address")
public class WayBillAddress implements Serializable {

	private static final long serialVersionUID = 1738812729781455436L;

	/**
	 * 物流地址id
	 */
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logistics_address_id")
	private Long logisticsAddressId;
	/**
	 * 物流运单id
	 */
	@Column(name = "way_bill_id")
	private Long wayBillId;
	/**
	 * 地址类型 1 起点  2 终点
	 */
	@Column(name = "logistics_address_type")
	private Integer logisticsAddressType;
	/**
	 * 收发货方编码
	 */
	@Column(name = "enterprise_code")
	private String enterpriseCode;
	/**
	 * 收发货方名称
	 */
	@Column(name = "enterprise_name")
	private String enterpriseName;
	/**
	 * 联系人
	 */
	@Column(name = "contract_name")
	private String contractName;
	/**
	 * 联系电话
	 */
	@Column(name = "contact_tel")
	private String contactTel;
	/**
	 * 省code,国标
	 */
	@Column(name = "province_code")
	private String provinceCode;
	/**
	 * 省
	 */
	@Column(name = "province_name")
	private String provinceName;
	/**
	 * 市code,国标
	 */
	@Column(name = "city_code")
	private String cityCode;
	/**
	 * 市
	 */
	@Column(name = "city_name")
	private String cityName;
	/**
	 * 县code,国标
	 */
	@Column(name = "county_code")
	private String countyCode;
	/**
	 * 县
	 */
	@Column(name = "county_name")
	private String countyName;
	/**
	 * 镇code,国标
	 */
	@Column(name = "town_code")
	private String townCode;
	/**
	 * 镇
	 */
	@Column(name = "town_name")
	private String townName;
	/**
	 * 详细地址
	 */
	@Column(name = "detail_address")
	private String detailAddress;
	/**
	 * 地址纬度
	 */
	@Column(name = "addr_lal")
	private String addrLal;
	/**
	 * 地址经度
	 */
	@Column(name = "addr_lon")
	private String addrLon;
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


	public Long getLogisticsAddressId() {
		return logisticsAddressId;
	}

	public void setLogisticsAddressId(Long logisticsAddressId) {
		this.logisticsAddressId = logisticsAddressId;
	}


	public Long getWayBillId() {
		return wayBillId;
	}

	public void setWayBillId(Long wayBillId) {
		this.wayBillId = wayBillId;
	}


	public Integer getLogisticsAddressType() {
		return logisticsAddressType;
	}

	public void setLogisticsAddressType(Integer logisticsAddressType) {
		this.logisticsAddressType = logisticsAddressType;
	}


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


	public String getAddrLal() {
		return addrLal;
	}

	public void setAddrLal(String addrLal) {
		this.addrLal = addrLal;
	}


	public String getAddrLon() {
		return addrLon;
	}

	public void setAddrLon(String addrLon) {
		this.addrLon = addrLon;
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

}
