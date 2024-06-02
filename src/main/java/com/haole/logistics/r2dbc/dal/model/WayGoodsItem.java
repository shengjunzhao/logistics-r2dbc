package com.haole.logistics.r2dbc.dal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;

@Entity
@Table(name = "way_goods_item")
public class WayGoodsItem implements Serializable {

	private static final long serialVersionUID = 8458903144246251158L;

	/**
	 * 运单明细ID
	 */
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "way_item_id")
	private Long wayItemId;
	/**
	 * 物流运单id
	 */
	@Column(name = "way_bill_id")
	private Long wayBillId;
	/**
	 * 商品code(sku)
	 */
	@Column(name = "goods_code")
	private String goodsCode;
	/**
	 * 商品ID
	 */
	@Column(name = "goods_id")
	private Long goodsId;
	/**
	 * 商品名称
	 */
	@Column(name = "goods_name")
	private String goodsName;
	/**
	 * 商品数量
	 */
	@Column(name = "goods_amount")
	private BigDecimal goodsAmount;
	/**
	 * 货物总件数(包裹件数)
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


	public Long getWayItemId() {
		return wayItemId;
	}

	public void setWayItemId(Long wayItemId) {
		this.wayItemId = wayItemId;
	}


	public Long getWayBillId() {
		return wayBillId;
	}

	public void setWayBillId(Long wayBillId) {
		this.wayBillId = wayBillId;
	}


	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}


	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
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

}
