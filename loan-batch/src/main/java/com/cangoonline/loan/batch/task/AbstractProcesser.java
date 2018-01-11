package com.cangoonline.loan.batch.task;

import com.cangoframework.base.utils.DateUtils;
import com.cangoonline.loan.batch.entity.TaskApplyLog;
import com.cangoonline.loan.batch.entity.TaskPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Date;

/**
 * Created by cango on 2018/1/11.
 */
public abstract class AbstractProcesser implements Processer {

    protected static Logger logger = LoggerFactory.getLogger(AbstractProcesser.class);

    protected TaskPool task;
    protected Connection conn;
    protected TaskApplyLog taskApplyLog = new TaskApplyLog();

    public void setLogResult(String code, String message) {
        taskApplyLog.setCode(code);
        taskApplyLog.setMessage(message);
    }
    public void setProcessResult(String businessStatus, String operateStatus) {
        taskApplyLog.setBusinessStatus(businessStatus);
        taskApplyLog.setOperateStatus(operateStatus);
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setTask(TaskPool task) {
        this.task = task;
    }

    public TaskApplyLog getTaskApplyLog() {
        taskApplyLog.setTpSerialNo(task.getSerialNo());
        taskApplyLog.setSetpNo(task.getProcesserName());
        taskApplyLog.setUpdateDate(DateUtils.YYYYMMDDHHMMSS_HR.format(new Date()));
        return taskApplyLog;
    }

}
