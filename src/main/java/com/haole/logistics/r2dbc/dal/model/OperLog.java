package com.haole.logistics.r2dbc.dal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "oper_log")
public class OperLog implements Serializable {

	private static final long serialVersionUID = 3403945240235012467L;

	/**
	 * 日志ID
	 */
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "log_id")
	private Long logId;
	/**
	 * 业务id
	 */
	@Column(name = "business_id")
	private Long businessId;
	/**
	 * 日志类别,此表支持多业务日志
	 */
	@Column(name = "log_class")
	private String logClass;
	/**
	 * 日志类型,用于区分同一业务下,区分日志
	 */
	@Column(name = "log_type")
	private String logType;
	/**
	 * 操作内容
	 */
	@Column(name = "content")
	private String content;
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


	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}


	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


	public String getLogClass() {
		return logClass;
	}

	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}


	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
