package com.cangoonline.loan.batch.entity.bjbank;

import com.cangoonline.loan.batch.entity.BusinessObject;

/**
 * Created by cango on 2018/1/11.
 */
public class CXFQBusinessObject extends BusinessObject{

    private String brandTrademark; //品牌型号
    private String carType; //车辆类型
    private String transactionPrice; //成交价，单位元，保留小数点两位
    private String carAddress; //车辆所在地
    private String isNew; //是否是新车：0-新车，1-旧车
    private String insuranceName; //保险公司名称
    private String drivingLicenseFile; //行驶证扫描件，jpg或pdf格式，参照文件传输命名规则章节中对应的相应文件的命名，文件命名为：Travel_1.JPG 地址多个以分号间隔
    private String driverLicenceFile; //驾驶证扫描件，格式、地址同上，，参照文件传输命名规则章节中对应的相应文件的命名；
    private String serviceAgreement; //服务协议文件电子版，格式、地址同上，
    private Integer carAmount; //车辆数量，最大20
    private String applyType; //申请人类型: 01-个人申请，02-多人申请

    private String institutionalNumber; //组织机构代码
    private String businessFile; //企业营业执照扫描件

    private String hasDetection; //车辆是否检测过（当isNew为1时有效）：0-已检测，1-未检测，2-免检
    private String initialRegistrationDate; //首次上牌时间，yyyy/MM/dd（当isNew为1时有效）
    private String licensePlateAddress; //购车上牌所在地（当isNew为1时有效）
    private String insuranceCount; //用户在保险公司获取保费的账号
    private String vehicleInvoiceFile; //O :购车发票扫描件，格式、地址同上
    private String insurancePolicy; //O :保单，jpg或 pdf格式，如：InsurancePolicy_1.JPG 多张图片地址以分号间隔，

    public String getBrandTrademark() {
        return brandTrademark;
    }

    public void setBrandTrademark(String brandTrademark) {
        this.brandTrademark = brandTrademark;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(String transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public String getCarAddress() {
        return carAddress;
    }

    public void setCarAddress(String carAddress) {
        this.carAddress = carAddress;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getDrivingLicenseFile() {
        return drivingLicenseFile;
    }

    public void setDrivingLicenseFile(String drivingLicenseFile) {
        this.drivingLicenseFile = drivingLicenseFile;
    }

    public String getDriverLicenceFile() {
        return driverLicenceFile;
    }

    public void setDriverLicenceFile(String driverLicenceFile) {
        this.driverLicenceFile = driverLicenceFile;
    }

    public String getServiceAgreement() {
        return serviceAgreement;
    }

    public void setServiceAgreement(String serviceAgreement) {
        this.serviceAgreement = serviceAgreement;
    }

    public Integer getCarAmount() {
        return carAmount;
    }

    public void setCarAmount(Integer carAmount) {
        this.carAmount = carAmount;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getInstitutionalNumber() {
        return institutionalNumber;
    }

    public void setInstitutionalNumber(String institutionalNumber) {
        this.institutionalNumber = institutionalNumber;
    }

    public String getBusinessFile() {
        return businessFile;
    }

    public void setBusinessFile(String businessFile) {
        this.businessFile = businessFile;
    }

    public String getHasDetection() {
        return hasDetection;
    }

    public void setHasDetection(String hasDetection) {
        this.hasDetection = hasDetection;
    }

    public String getInitialRegistrationDate() {
        return initialRegistrationDate;
    }

    public void setInitialRegistrationDate(String initialRegistrationDate) {
        this.initialRegistrationDate = initialRegistrationDate;
    }

    public String getLicensePlateAddress() {
        return licensePlateAddress;
    }

    public void setLicensePlateAddress(String licensePlateAddress) {
        this.licensePlateAddress = licensePlateAddress;
    }

    public String getInsuranceCount() {
        return insuranceCount;
    }

    public void setInsuranceCount(String insuranceCount) {
        this.insuranceCount = insuranceCount;
    }

    public String getVehicleInvoiceFile() {
        return vehicleInvoiceFile;
    }

    public void setVehicleInvoiceFile(String vehicleInvoiceFile) {
        this.vehicleInvoiceFile = vehicleInvoiceFile;
    }

    public String getInsurancePolicy() {
        return insurancePolicy;
    }

    public void setInsurancePolicy(String insurancePolicy) {
        this.insurancePolicy = insurancePolicy;
    }
}
