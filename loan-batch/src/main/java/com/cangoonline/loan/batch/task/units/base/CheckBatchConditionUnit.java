package com.cangoonline.loan.batch.task.units.base;

import com.cangoframework.api.service.MailServiceApi;
import com.cangoframework.api.service.SmsServiceApi;
import com.cangoframework.task.ExecuteUnit;
import com.cangoframework.task.TaskConstants;
import com.cangoonline.loan.batch.dao.BatchTaskControlStaticDao;
import com.cangoonline.loan.batch.entity.BatchTaskControl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cango on 2018/1/5.
 */
public class CheckBatchConditionUnit extends ExecuteUnit {
    private  String notifyType = "none";
    private  String notifyTitle ="批量运行条件检查";
    private  String notifyPhone = "";
    private  String notifyEmail = "";
    private SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
    private BatchTaskControlStaticDao controlDao = new BatchTaskControlStaticDao();
    @Override
    public int execute() {

        try {
            //1.根据业务日期去批量执行控制表中查询改任务是否可以被执行
            List<BatchTaskControl> controlList = controlDao.listBatchTaskControl(this);
            if(controlList == null || controlList.isEmpty()){
                logger.info("不存在的目标任务[{}]",getTarget().getName());
                return TaskConstants.ES_FAILED;
            }

            for (BatchTaskControl batch: controlList){
                //1.1查看今天是否存在执行成功的任务
                if("1".equals(batch.getExecuteStatus())){
                    sendNotify("目标任务["+getTarget().getName()+"]已经成功执行过了");
                    return TaskConstants.ES_FAILED;
                }

                //1.2查看是否还有前置任务没有执行完成
                String prepositionTask = batch.getPrepositionTask();
                if(!(prepositionTask==null||"".equals(prepositionTask))){
                    boolean successFlag = controlDao.checkPrepositionTask(getTarget().getName(), prepositionTask);
                    if(!successFlag){
                        sendNotify("目标任务["+getTarget().getName()+"]的前置任务["+prepositionTask+"]没有自行完成");
                        return TaskConstants.ES_FAILED;
                    }
                }

                //1.3查看是否过了执行时间区间
                String nowTime = sf.format(new Date());
                Date parse = sf.parse(batch.getStartTime());

                if(!(nowTime.compareTo(batch.getStartTime())>=0
                        && nowTime.compareTo(batch.getEndTime())<0)){
                    String message = "目标任务["+getTarget().getName()+"]不能在指定时间范围外开始执行";
                    sendNotify(message);
                    return TaskConstants.ES_FAILED;
                }
            }
            //如果无法有效执行，需要短信通知运维手工触发

        } catch (SQLException e) {
            e.printStackTrace();
            sendNotify("批量运行条件检查出现异常："+e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TaskConstants.ES_SUCCESSFUL;
    }

    /**
     * 通知
     * @param content
     */
    private void sendNotify(String content) {
        logger.info(content);
        switch (notifyType){
            case "sms":
                SmsServiceApi.sendSms(content,notifyPhone.trim());
                break;
            case "email":
                MailServiceApi.sendEmail(MailServiceApi.TYPE_TXT,notifyTitle,content,notifyEmail.trim());
                break;
            case "all":
                SmsServiceApi.sendSms(content,notifyPhone.trim());
                MailServiceApi.sendEmail(MailServiceApi.TYPE_TXT,notifyTitle,content,notifyEmail.trim());
                break;
            case "none":
                break;
        }
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public void setNotifyPhone(String notifyPhone) {
        this.notifyPhone = notifyPhone;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }
}
