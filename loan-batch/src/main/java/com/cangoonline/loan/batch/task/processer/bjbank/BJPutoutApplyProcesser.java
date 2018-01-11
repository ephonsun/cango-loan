package com.cangoonline.loan.batch.task.processer.bjbank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cangoframework.base.utils.DateUtils;
import com.cangoframework.base.utils.HttpUtils;
import com.cangoframework.task.util.Tools;
import com.cangoonline.loan.batch.commons.BjBankConsts;
import com.cangoonline.loan.batch.dao.PutoutApplyStaticDao;
import com.cangoonline.loan.batch.entity.TaskApplyLog;
import com.cangoonline.loan.batch.entity.TaskPool;
import com.cangoonline.loan.batch.entity.bjbank.CXFQBusinessObject;
import com.cangoonline.loan.batch.entity.bjbank.PutoutApplyModel;
import com.cangoonline.loan.batch.entity.bjbank.Token;
import com.cangoonline.loan.batch.entity.bjbank.TokenResult;
import com.cangoonline.loan.batch.task.processer.base.PutoutApplyProcesser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

/**
 * Created by cango on 2018/1/11.
 * 发送北京放款申请
 */
public class BJPutoutApplyProcesser extends PutoutApplyProcesser {
    private static Logger logger = LoggerFactory.getLogger(BJPutoutApplyProcesser.class);
    private int maxTokenRetry = 0;
    private int maxPutoutApplyRetry = 0;
    @Override
    public boolean process(TaskPool task , Connection conn) {

        try {
            PutoutApplyModel model = new PutoutApplyModel();
            CXFQBusinessObject businessObject = new CXFQBusinessObject();
            model.setBusinessObject(businessObject);

            //M
            String accessToken = getAccessToken();
            model.setAccessToken(accessToken);//访问令牌，登录成功后获取得到
            model.setTimestamp(DateUtils.YYYYMMDDHHMMSS.format(new Date()));
            model.setOrderNo("");//订单号（位数不能超过128位）
            model.setCreditNo("");//授信编号，用户订单授信标识授信不通过，授信编号为0
            model.setChannelId(BjBankConsts.CHANNEL_ID);
            model.setIdNo("");//申请人身份证号（位数：18位）
            model.setName("");//申请人姓名
            model.setBankCard("");//入账银行卡号(必须是借记卡)（位数15-21位）
            model.setMobile("");//申请人手机号
            model.setLoanAmount(0.0);//贷款金额，单位元,保留小数点后两位
            model.setLoanPeriod(12);//贷款期限：3期，6期，9期，10期，12期，24期，36期 （数字为多少就代表多少期，数字可取任意值）
            model.setInvoiceAmount(0.0);//发票金额，单位元，保留小数点后两位；businessType=05时，传空值
            model.setDownPayment(0.0);//首付金额，单位元，保留小数点后两位，businessType=05时，传空值
            model.setIdFile("");//身份证扫描件及借款人手持身份证照片，jpg或 pdf格式，参照文件传输命名规则章节中对应的相应文件的命名，文件命名为：Id_1.JPG，多张图片地址以分号间隔
            model.setLoanContractFile("");//借款合同扫描件，格式、地址同上
            model.setAuthorizationFile("");//个人人行征信查询授权书文件，格式、地址同上
            model.setBusinessType("06");//01-车贷，02-装修贷，03-加盟贷，04-租房贷 05-其他消费，06-车险分期，07-业主贷，08-教育分期，09-商品分期

            //businessObject
            businessObject.setBrandTrademark("");//品牌型号
            businessObject.setCarType("");//車輛類型
            businessObject.setIsNew("1");//是否是新车：0-新车，1-旧车
            businessObject.setApplyType("01");//申请人类型: 01-个人申请，02-多人申请
            businessObject.setCarAmount(0);//车辆数量，最大20

            businessObject.setCarAddress("");//车辆所在地
            businessObject.setInsuranceName("");//保险公司名称
            businessObject.setInsuranceCount("");//用户在保险公司获取保费的账号
            businessObject.setDrivingLicenseFile("");//行驶证扫描件，jpg或pdf格式，参照文件传输命名规则章节中对应的相应文件的命名，文件命名为：Travel_1.JPG 地址多个以分号间隔
            businessObject.setDriverLicenceFile("");//驾驶证扫描件，格式、地址同上，，参照文件传输命名规则章节中对应的相应文件的命名；
            businessObject.setServiceAgreement("");//服务协议文件电子版，格式、地址同上

            // O
            model.setDealerName("");//经销商名称
            model.setCreditContractFile("");//个人授信业务合同扫描件
            model.setLoanUsageFile("");//借款用途证明文件扫描件
            model.setLoanApplicationFile("");//个人消费贷款申请书扫描件
            model.setLoanReceiptFile("");//借款借据扫描件

            //数据封装完毕
            taskApplyLog.setRequestMessage(JSON.toJSONString(model));
            logger.info("放款申请数据模型:\n" + JSON.toJSONString(model,true));
            String modelJson = JSON.toJSONString(model);
            JSONObject jsonObject = JSON.parseObject(modelJson);
            jsonObject.put("businessObject",JSON.toJSONString(businessObject));
            logger.info("放款申请请求报文:\n" + jsonObject.toJSONString());

            String loanResult = getPutoutApplyResult(jsonObject);
            taskApplyLog.setResponseMessage(loanResult);
            JSONObject loanResultJson = JSON.parseObject(loanResult);
            if(loanResultJson!=null){
                //0-自动审核通过、1-放款信息风险评估不通过，2-申请放款金额超出范围，3-渠道授信额度已用完，4-发票金额与车辆认定金额差距太大（仅当消费场景为购车时有效），5-不可重复支用，6-风控审核已过期
                //系统错误则显示系统错误码，错误码枚举说明参见最后章节
                String applyNo = loanResultJson.getString("applyNo");
                String result = loanResultJson.getString("result");
                String description = loanResultJson.getString("description");
                logger.info("放款申请[{}]结果:applyNo = {} , result= {} , description = {}",task.getSerialNo(),applyNo,result,description);
                if("0".equals(result)){
                    //設置第三方放款申请号
                    task.setThridPutoutSerialNo(applyNo);
                    //设置日志状态
                    setLogResult(TaskApplyLog.STATUS_PASS,"成功");
                    setProcessResult(TaskApplyLog.STATUS_PASS,TaskApplyLog.STATUS_PASS);
                    doPutoutApplyPassResult();
                }else{
                    setLogResult(TaskApplyLog.STATUS_FAIL,description);
                    setProcessResult(TaskApplyLog.STATUS_FAIL,TaskApplyLog.STATUS_PASS);
                    doPutoutApplyRejectResult();
                }
            } else{
                throw new Exception("放款申请返回结果异常");
            }

            //更据任务流水更新任务信息
            PutoutApplyStaticDao.updateTaskStatusById(conn,task);

            //插入执行日志
            PutoutApplyStaticDao.saveTaskApplyLog(conn, getTaskApplyLog());

        } catch (IOException e) {
            e.printStackTrace();
            doException(conn,task,e);
        } catch (Exception e) {
            e.printStackTrace();
            doException(conn,task,e);
        }
        return false;
    }

    private String getPutoutApplyResult(JSONObject jsonObject) throws Exception {

        try {
            String result = HttpUtils.sendPost(BjBankConsts.HTTP_URL_LOANAPPLY, jsonObject);
            if(!Tools.isEmpty(result)){
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            maxTokenRetry --;
            if(maxTokenRetry >=0 ){
                return getPutoutApplyResult(jsonObject);
            }
        }
        throw new Exception("无法成功获取北京银行放款申请结果");
    }

    private void doPutoutApplyPassResult() {

        //1.BUSINESS_SUMMARY 放款审批拒绝,同时添加日志BUSINESS_SUMMARY_LOG

        //2.结束（下一个单元处理合同和放款信息，核算）
    }

    private void doPutoutApplyRejectResult() {

        //1.通知客户，放款申请拒绝

        //2.BUSINESS_SUMMARY 放款审批拒绝,同时添加日志BUSINESS_SUMMARY_LOG (6 - 放款申请中，7 - 放款审批拒绝，8 - 放款审批成功)

        //3.结束

    }

    /**
     * 处理异常信息
     * @param conn
     * @param task
     * @param e
     */
    private void doException(Connection conn, TaskPool task, Exception e) {

    }

    public String getAccessToken() throws Exception {
        try {
            TokenResult tokenResult = Token.getToken();
            if(tokenResult!=null){
                if("0".equalsIgnoreCase(tokenResult.getResult())
                        && !Tools.isEmpty(tokenResult.getAccessToken())){
                    return tokenResult.getAccessToken();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            maxTokenRetry --;
            if(maxTokenRetry >=0 ){
                return getAccessToken();
            }
        }
        throw new Exception("无法成功获取北京银行AccessToken");
    }
}
