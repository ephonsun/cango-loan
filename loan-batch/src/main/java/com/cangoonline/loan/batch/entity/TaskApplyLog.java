package com.cangoonline.loan.batch.entity;

public class TaskApplyLog {
	public static final String STATUS_PASS = "00";
	public static final String STATUS_FAIL = "01";
	private String serialNo;  //日志流水号
	private Long tpSerialNo;  //关联任务流水号
	private String relativeSerialNo;  //关联流水号
	private Integer requestCount;  //请求次数
	private String operateStatus;  //处理状态：00-处理成功 01-处理失败
	private String businessStatus;  //业务状态：01-业务拒绝 00-业务通过
	private String requestMessage;  //请求报文
	private String responseMessage;  //返回报文
	private String code;  //返回code
	private String message;  //返回code
	private String setpNo;  //接口交易代码
	private String inputDate;  //输入日期
	private String updateDate;  //更新日期
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public Long getTpSerialNo() {
		return tpSerialNo;
	}
	public void setTpSerialNo(Long tpSerialNo) {
		this.tpSerialNo = tpSerialNo;
	}
	public String getRelativeSerialNo() {
		return relativeSerialNo;
	}
	public void setRelativeSerialNo(String relativeSerialNo) {
		this.relativeSerialNo = relativeSerialNo;
	}
	public Integer getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}
	public String getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}
	public String getRequestMessage() {
		return requestMessage;
	}
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public String getSetpNo() {
		return setpNo;
	}
	public void setSetpNo(String setpNo) {
		this.setpNo = setpNo;
	}
	
}
