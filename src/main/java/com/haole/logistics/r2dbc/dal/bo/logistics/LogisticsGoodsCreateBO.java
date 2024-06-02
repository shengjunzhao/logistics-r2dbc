package com.haole.logistics.r2dbc.dal.bo.logistics;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class LogisticsGoodsCreateBO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4278017229789823159L;

    /**
     * 商品code
     */
    @NotEmpty(message = "商品code不能为空")
    @Size(max = 100, message = "商品code超过合法长度")
    private String goodsCode;

    /**
     * 商品名称
     */
    @NotEmpty(message = "商品名称不能为空")
    @Size(max = 200, message = "商品名称超过合法长度")
    private String goodsName;


    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    private BigDecimal goodsAmount;

    /**
     * 商品件数
     */
    @NotNull(message = "商品件数不能为空")
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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
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
}
