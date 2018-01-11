package com.cangoonline.loan.batch.entity;


@SuppressWarnings("serial")
public class TaskPool {

	/** 就绪 02*/
	public static final String STATUS_READY = "01";

	/** 处理中 03*/
	public static final String STATUS_RUNNING = "02";

	/** 处理失败04 */
	public static final String STATUS_FAILURE = "03";
	
	/** 挂起状态 05*/
	public static final String STATUS_HANGUP = "04";

	/** 处理成功 00*/
	public static final String STATUS_SUCCESS = "00";
	
	private Long serialNo; //任务流水号
	private String relativeserialNo; //审批池流水号
	private String customerId; //客户号
	private String customerName; //客户姓名
	private String businessType; //业务品种
	private String baSerialNo; //申请流水号
	private String bapSerialNo; //批复流水号
	private String operateStatus; //处理状态：01-待处理 02-就绪 03-处理中 04-处理失败 00-处理成功
	private String inputDate; //录入日期
	private String updateDate; //更新日期
	private String transDate; //交易日期
	private String channel; //渠道号
	private String processerName; //processerName
	private String erpCustomerId; //erp客户id
	private String bcSerialNo; //合同流水号
	private String bpSerialNo; //放款流水号
	private String bdSerialNo; //借据流水号
	private String thridApplySerialNo; //第三方授信申请号
	private String thridPutoutSerialNo; //第三方放款申请号

	public String getErpCustomerId() {
		return erpCustomerId;
	}
	public void setErpCustomerId(String erpCustomerId) {
		this.erpCustomerId = erpCustomerId;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public String getRelativeserialNo() {
		return relativeserialNo;
	}
	public void setRelativeserialNo(String relativeserialNo) {
		this.relativeserialNo = relativeserialNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBaSerialNo() {
		return baSerialNo;
	}
	public void setBaSerialNo(String baSerialNo) {
		this.baSerialNo = baSerialNo;
	}
	public String getBapSerialNo() {
		return bapSerialNo;
	}
	public void setBapSerialNo(String bapSerialNo) {
		this.bapSerialNo = bapSerialNo;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getThridApplySerialNo() {
		return thridApplySerialNo;
	}

	public void setThridApplySerialNo(String thridApplySerialNo) {
		this.thridApplySerialNo = thridApplySerialNo;
	}

	public String getThridPutoutSerialNo() {
		return thridPutoutSerialNo;
	}

	public void setThridPutoutSerialNo(String thridPutoutSerialNo) {
		this.thridPutoutSerialNo = thridPutoutSerialNo;
	}

	public String getBcSerialNo() {
		return bcSerialNo;
	}

	public void setBcSerialNo(String bcSerialNo) {
		this.bcSerialNo = bcSerialNo;
	}

	public String getBpSerialNo() {
		return bpSerialNo;
	}

	public void setBpSerialNo(String bpSerialNo) {
		this.bpSerialNo = bpSerialNo;
	}

	public String getBdSerialNo() {
		return bdSerialNo;
	}

	public void setBdSerialNo(String bdSerialNo) {
		this.bdSerialNo = bdSerialNo;
	}

	public String getProcesserName() {
		return processerName;
	}

	public void setProcesserName(String processerName) {
		this.processerName = processerName;
	}
}
