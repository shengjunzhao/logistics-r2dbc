package com.haole.logistics.r2dbc.dal.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "logistics_order_way")
public class LogisticsOrderWay implements Serializable {
    @Serial
    private static final long serialVersionUID = -2409203340090956177L;

    /**
     * 关系id
     */
    @Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Long relationId;

    /**
     * 物流订单id
     */
    @Column(name = "logistics_order_id")
    private Long logisticsOrderId;

    /**
     * 物流运单id
     */
    @Column(name = "way_bill_id")
    private Long wayBillId;

    /**
     * 创建人用户id
     */
    @Column(name = "create_by_user_id")
    private Long createByUserId;

    /**
     * Database Column Remarks:
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

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getLogisticsOrderId() {
        return logisticsOrderId;
    }

    public void setLogisticsOrderId(Long logisticsOrderId) {
        this.logisticsOrderId = logisticsOrderId;
    }

    public Long getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(Long wayBillId) {
        this.wayBillId = wayBillId;
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
