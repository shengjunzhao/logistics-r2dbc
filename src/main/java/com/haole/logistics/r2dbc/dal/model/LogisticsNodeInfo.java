package com.haole.logistics.r2dbc.dal.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
@Table(name = "logistics_node_info")
public class LogisticsNodeInfo implements Serializable {

	private static final long serialVersionUID = 3351046966407351810L;

	/**
	 * 节点id
	 */
	@Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "logistics_node_id")
	private Long logisticsNodeId;
	/**
	 * 运单id
	 */
	@Column(name = "way_bill_id")
	private Long wayBillId;
	/**
	 * 物流单号
            来自三方，或者手工输入
            
	 */
	@Column(name = "logistics_bill_no")
	private String logisticsBillNo;
	/**
	 * 物流公司编码
	 */
	@Column(name = "logistics_enterprise_code")
	private String logisticsEnterpriseCode;
	/**
	 * 节点详情
	 */
	@Column(name = "node_detail")
	private String nodeDetail;
	/**
	 * 轨迹原数据
	 */
	@Column(name = "track_data")
	private String trackData;
	/**
	 * 节点状态(来自节点详情)
	 */
	@Column(name = "node_status")
	private Integer nodeStatus;
	/**
	 * 节点详情时间
	 */
	@Column(name = "node_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime nodeTime;
	/**
	 * 节点详情标识
            快递100的status+time
	 */
	@Column(name = "node_unique_code")
	private String nodeUniqueCode;
	/**
	 * 创建人企业ID
	 */
	@Column(name = "create_by_enterprise_id")
	private Long createByEnterpriseId;
	/**
	 * 创建人用户ID
	 */
	@Column(name = "create_by_user_id")
	private Long createByUserId;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createTime;
	/**
	 * 更新人企业ID
	 */
	@Column(name = "update_by_enterprise_id")
	private Long updateByEnterpriseId;
	/**
	 * 更新人
	 */
	@Column(name = "update_by_user_id")
	private Long updateByUserId;
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


	public Long getLogisticsNodeId() {
		return logisticsNodeId;
	}

	public void setLogisticsNodeId(Long logisticsNodeId) {
		this.logisticsNodeId = logisticsNodeId;
	}


	public Long getWayBillId() {
		return wayBillId;
	}

	public void setWayBillId(Long wayBillId) {
		this.wayBillId = wayBillId;
	}


	public String getLogisticsBillNo() {
		return logisticsBillNo;
	}

	public void setLogisticsBillNo(String logisticsBillNo) {
		this.logisticsBillNo = logisticsBillNo;
	}


	public String getLogisticsEnterpriseCode() {
		return logisticsEnterpriseCode;
	}

	public void setLogisticsEnterpriseCode(String logisticsEnterpriseCode) {
		this.logisticsEnterpriseCode = logisticsEnterpriseCode;
	}


	public String getNodeDetail() {
		return nodeDetail;
	}

	public void setNodeDetail(String nodeDetail) {
		this.nodeDetail = nodeDetail;
	}


	public String getTrackData() {
		return trackData;
	}

	public void setTrackData(String trackData) {
		this.trackData = trackData;
	}


	public Integer getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}


	public LocalDateTime getNodeTime() {
		return nodeTime;
	}

	public void setNodeTime(LocalDateTime nodeTime) {
		this.nodeTime = nodeTime;
	}


	public String getNodeUniqueCode() {
		return nodeUniqueCode;
	}

	public void setNodeUniqueCode(String nodeUniqueCode) {
		this.nodeUniqueCode = nodeUniqueCode;
	}


	public Long getCreateByEnterpriseId() {
		return createByEnterpriseId;
	}

	public void setCreateByEnterpriseId(Long createByEnterpriseId) {
		this.createByEnterpriseId = createByEnterpriseId;
	}


	public Long getCreateByUserId() {
		return createByUserId;
	}

	public void setCreateByUserId(Long createByUserId) {
		this.createByUserId = createByUserId;
	}


	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}


	public Long getUpdateByEnterpriseId() {
		return updateByEnterpriseId;
	}

	public void setUpdateByEnterpriseId(Long updateByEnterpriseId) {
		this.updateByEnterpriseId = updateByEnterpriseId;
	}


	public Long getUpdateByUserId() {
		return updateByUserId;
	}

	public void setUpdateByUserId(Long updateByUserId) {
		this.updateByUserId = updateByUserId;
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
