package com.cangoonline.loan.batch.entity.bjbank;

import com.cangoonline.loan.batch.entity.BusinessObject;

/**
 * Created by cango on 2018/1/11.
 */
public class PutoutApplyModel {

    private String accessToken; //访问令牌，登录成功后获取得到
    private String timestamp; //时间戳，格式为yyyyMMddHHmmss
    private String orderNo; //订单号（位数不能超过128位）
    private String creditNo; //授信编号，用户订单授信标识授信不通过，授信编号为0
    private String channelId; //渠道id（渠道ID由北京银行上海分行统一分配）
    private String dealerName; //经销商名称
    private String idNo; //申请人身份证号（位数：18位）
    private String name; //申请人姓名
    private String bankCard; //入账银行卡号(必须是借记卡)（位数15-21位）
    private String mobile; //申请人手机号（位数：11位）
    private Double loanAmount; //贷款金额，单位元,保留小数点后两位
    private Integer loanPeriod; //贷款期限：3期，6期，9期，10期，12期，24期，36期 （数字为多少就代表多少期，数字可取任意值）
    private Double invoiceAmount; //发票金额，单位元，保留小数点后两位；businessType=05时，传空值
    private Double downPayment; //首付金额，单位元，保留小数点后两位，businessType=05时，传空值
    private String idFile; //身份证扫描件及借款人手持身份证照片，jpg或 pdf格式，参照文件传输命名规则章节中对应的相应文件的命名，文件命名为：Id_1.JPG，多张图片地址以分号间隔
    private String loanContractFile; //借款合同扫描件，格式、地址同上，
    private String authorizationFile; //个人人行征信查询授权书文件，格式、地址同上
    private String creditContractFile; //个人授信业务合同扫描件，格式、地址同上
    private String loanUsageFile; //借款用途证明文件扫描件，格式、地址同上
    private String loanApplicationFile; //个人消费贷款申请书扫描件，格式、地址同上
    private String loanReceiptFile; //借款借据扫描件，格式、地址同上
    private String businessType; //01-车贷，02-装修贷，03-加盟贷，04-租房贷 05-其他消费，06-车险分期，07-业主贷，08-教育分期，09-商品分期
    private BusinessObject businessObject; //业务对象，不同业务类型对象中包括的数据属性不同，具体业务类型对象参见细化说明

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Double downPayment) {
        this.downPayment = downPayment;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getLoanContractFile() {
        return loanContractFile;
    }

    public void setLoanContractFile(String loanContractFile) {
        this.loanContractFile = loanContractFile;
    }

    public String getAuthorizationFile() {
        return authorizationFile;
    }

    public void setAuthorizationFile(String authorizationFile) {
        this.authorizationFile = authorizationFile;
    }

    public String getCreditContractFile() {
        return creditContractFile;
    }

    public void setCreditContractFile(String creditContractFile) {
        this.creditContractFile = creditContractFile;
    }

    public String getLoanUsageFile() {
        return loanUsageFile;
    }

    public void setLoanUsageFile(String loanUsageFile) {
        this.loanUsageFile = loanUsageFile;
    }

    public String getLoanApplicationFile() {
        return loanApplicationFile;
    }

    public void setLoanApplicationFile(String loanApplicationFile) {
        this.loanApplicationFile = loanApplicationFile;
    }

    public String getLoanReceiptFile() {
        return loanReceiptFile;
    }

    public void setLoanReceiptFile(String loanReceiptFile) {
        this.loanReceiptFile = loanReceiptFile;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public BusinessObject getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(BusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(Integer loanPeriod) {
        this.loanPeriod = loanPeriod;
    }
}
