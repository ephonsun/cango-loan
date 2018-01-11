package com.cangoonline.loan.batch.dao;

import com.cangoframework.dbpool.DBHelper;
import com.cangoframework.task.ExecuteUnit;
import com.cangoframework.task.TaskObject;
import com.cangoonline.loan.batch.dao.base.BaseDao;
import com.cangoonline.loan.batch.entity.BatchTaskControl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cango on 2018/1/5.
 */
public class BatchTaskControlStaticDao extends BaseDao {

    public List<BatchTaskControl> listBatchTaskControl(TaskObject taskObject) throws SQLException {
        List<BatchTaskControl> list = null;
        ExecuteUnit unit = (ExecuteUnit) taskObject;
        String sql = "select serialNo,taskName,targetName,startTime,endTime,prepositionTask,executeCount,executeStatus,transDate " +
                "from batch_task_control " +
                "where targetName= ? " +
                "and transDate = (select businessDate from system_setup where systemid = 1)";
        list = DBHelper.getListBean(sql,BatchTaskControl.class,unit.getTarget().getName());
        return list;
    }

    public boolean checkPrepositionTask(String targetName, String prepositionTask) throws SQLException {
        String[] preTasks = prepositionTask.split(",");

        String sql = "select count(1) " +
                "from batch_task_control " +
                "where targetName in (" + getInString(preTasks) + ") " +
                "and executeStatus = '2' "+
                "and transDate = (select businessDate from system_setup where systemid = 1)";
        int maxExecuteStatus = DBHelper.getCount(sql);
        return maxExecuteStatus == preTasks.length;
    }

}
