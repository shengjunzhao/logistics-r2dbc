package com.haole.logistics.r2dbc.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "snowflake_worker")
public class SnowflakeWorker implements Serializable {

	private static final long serialVersionUID = 6799669246028227719L;

	/**
	 * 分组名称
	 */
	@Id
	@org.springframework.data.annotation.Id
	@Column(name = "group_name")
	private String groupName;
	/**
	 * 最新的workerId
	 */
	@Column(name = "latest_worker_id")
	private Integer latestWorkerId;
	/**
	 * 数据版本
	 */
	@Column(name = "data_version")
	private Long dataVersion;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updateTime;


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public Integer getLatestWorkerId() {
		return latestWorkerId;
	}

	public void setLatestWorkerId(Integer latestWorkerId) {
		this.latestWorkerId = latestWorkerId;
	}


	public Long getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Long dataVersion) {
		this.dataVersion = dataVersion;
	}


	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
