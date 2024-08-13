package com.haole.logistics.r2dbc.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "logistics_his_track_record")
public class LogisticsHisTrackRecord implements Serializable {

    private static final long serialVersionUID = 3583531949590056619L;

    /**
     * 轨迹调用记录id
     */
    @Id
    @Column(name = "his_track_id")
    private Long hisTrackId;
    /**
     * 司机电话
     */
    @Column(name = "mobile_no")
    private String mobileNo;
    /**
     * 操作时间
     */
    @Column(name = "opt_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime optTime;
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
     * 更新人用户名称
     */
    @Column(name = "update_by_user_name")
    private String updateByUserName;
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


    public Long getHisTrackId() {
        return hisTrackId;
    }

    public void setHisTrackId(Long hisTrackId) {
        this.hisTrackId = hisTrackId;
    }


    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


    public LocalDateTime getOptTime() {
        return optTime;
    }

    public void setOptTime(LocalDateTime optTime) {
        this.optTime = optTime;
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


    public String getUpdateByUserName() {
        return updateByUserName;
    }

    public void setUpdateByUserName(String updateByUserName) {
        this.updateByUserName = updateByUserName;
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
