package com.cangoonline.loan.batch.entity.bjbank;

import com.cangoframework.dbpool.annotation.Table;

/**
 * Created by cango on 2018/1/10.
 */
@Table(value = "bj_issued_batch_file",primary = "serialNo")
public class BatchIssuedFile {
    public static final String FILETYPE_LOANRESULT = "1";
    public static final String RESULT_SUCCESS = "1";
    public static final String RESULT_FAILED = "0";
    public static final String APPLYSTATUS_SUCCESS = "成功";
    public static final String APPLYSTATUS_FAILED = "失败";
    private String serialNo; //流水号
    private String orderNo; //订单编号，渠道标识该笔业务的唯一编号
    private String creditNo; //授信编号，用户订单授信标识
    private String applyNo; //申请编号
    private String channelId; //渠道id(由分行统一分配)
    private String name; //用户姓名
    private String idNo; //用户身份证号
    private String applyStatus; //放款状态，如成功，失败
    private String schedule; //个人还款计划
    private String payAmount; //个人放款金额，单位元，保留小数点后两位，如1000.00
    private String repayPrincipal; //还款本金，单位元，保留小数点后两位
    private String repayInterest; //还款利息，单位元，保留小数点后两位
    private String totalAmount; //应还总金额，单位元，保留小数点后两位
    private String periodNumber; //当前应还账款的第几期
    private String repaymentDate; //还款日，格式yyyy/MM/dd
    private String failReason; //失败原因
    private String fileType; //1：放款申请结果 ，2：还款文件，3:还款校对结果，4：还款结果，5：提前还款结果，6：强制提前还款文件
    private String transDate; //交易日期YYYY-MM-DD
    private String resultCode; //处理结果code
    private String resultMessage; //处理结果mssage

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreditNo() {
        return creditNo;
    }

    public void setCreditNo(String creditNo) {
        this.creditNo = creditNo;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getRepayPrincipal() {
        return repayPrincipal;
    }

    public void setRepayPrincipal(String repayPrincipal) {
        this.repayPrincipal = repayPrincipal;
    }

    public String getRepayInterest() {
        return repayInterest;
    }

    public void setRepayInterest(String repayInterest) {
        this.repayInterest = repayInterest;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
