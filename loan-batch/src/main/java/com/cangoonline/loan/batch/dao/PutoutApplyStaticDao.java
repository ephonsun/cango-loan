package com.cangoonline.loan.batch.dao;

import com.cangoframework.dbpool.DBHelper;
import com.cangoframework.task.util.Tools;
import com.cangoonline.loan.batch.dao.base.BaseDao;
import com.cangoonline.loan.batch.entity.TaskApplyLog;
import com.cangoonline.loan.batch.entity.TaskPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by cango on 2018/1/11.
 */
public class PutoutApplyStaticDao extends BaseDao {

    /**
     * 获取就绪的放款申请任务
     * @param conn
     * @param threadNumber
     * @param threadId
     * @return
     * @throws SQLException
     */
    public static List<TaskPool> getReadyTasks(Connection conn, int threadNumber, int threadId) throws SQLException {
        String sql = "select serialNo ," +
                "relativeserialNo ," +
                "customerId ," +
                "erpCustomerId ," +
                "customerName ," +
                "businessType ," +
                "channel ," +
                "baSerialNo ," +
                "bapSerialNo ," +
                "thridApplySerialNo ," +
                "operateStatus ," +
                "inputDate ," +
                "updateDate ," +
                "transDate " +
                "from task_taskpool " +
                "where operateStatus = ? " +
                "and mod(serialNo, ?) = ?";
        return DBHelper.getListBean(conn, sql, TaskPool.class, TaskPool.STATUS_READY, threadNumber, threadId);
    }

    /**
     * 通过流水号更新任务状态
     * @param taskStatus
     * @param ids
     */
    public static int updateTaskStatusById(Connection conn, String taskStatus, String ... ids) throws SQLException {

        String sql = "update task_taskpool set operateStatus = ? " +
                "where serialNo in ("+ getInString(ids)+ ") ";

       return DBHelper.update(conn,sql);
    }

    public static int updateTaskStatusById(Connection conn, TaskPool task) throws SQLException {
        String serialNo = String.valueOf(task.getSerialNo());
        String sql = "update task_taskpool set ";
        StringBuffer sqlBuffer = new StringBuffer(sql);
        sqlBuffer.append(" operateStatus = ? ,");
        sqlBuffer.append(" updateDate = ? ,");
        sqlBuffer.append(" bcSerialNo = ? ,");
        sqlBuffer.append(" bpSerialNo = ? ,");
        sqlBuffer.append(" bdSerialNo = ? ,");
        sqlBuffer.append(" thridPutoutSerialNo = ? ,");
        sqlBuffer.append(" processerName = ? ");
        sqlBuffer.append("where serialNo = ?");
        sql = sqlBuffer.toString();
        return DBHelper.update(conn,sql,task.getOperateStatus() ,task.getUpdateDate(),task.getBcSerialNo(),
                task.getBpSerialNo(),task.getBdSerialNo(),task.getThridPutoutSerialNo(),task.getProcesserName(),
                serialNo);
    }

    public static int saveTaskApplyLog(Connection conn, TaskApplyLog log) throws Exception {
        return DBHelper.save(log);
    }
}
