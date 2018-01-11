package com.cangoonline.loan.batch.task.units.bjbank;

import com.cangoframework.dbpool.DBHelper;
import com.cangoframework.task.ExecuteUnit;
import com.cangoframework.task.TaskConstants;
import com.cangoonline.loan.batch.entity.BatchIssuedFile;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cango on 2018/1/5.
 */
public class LoanResultUnit extends ExecuteUnit{
    private Connection connection;
    @Override
    public int execute() {

        //取出今日的放款申请结果数据
        try {
            connection = DBHelper.getConnection();
            connection.setAutoCommit(false);

            String sql = "select * from bj_issued_batch_file " +
                    "where fileType = ? " +
                    "and resultCode=? " +
                    "and transDate = (select businessDate from system_setup where systemid = 1)";

            DBHelper.query(connection, sql, new ResultSetHandler<Boolean>() {
                @Override
                public Boolean handle(ResultSet rs) throws SQLException {
                    while(rs.next()){
                        String applyStatus = rs.getString("applyStatus");
                        //TODO 当某一笔出现异常怎么办，是终止还是继续：直接终止批量，等待下次重新执行
                        if(BatchIssuedFile.APPLYSTATUS_SUCCESS.equals(applyStatus)){
                            doRejectLoanApply(rs);
                        }else if(BatchIssuedFile.APPLYSTATUS_FAILED.equals(applyStatus)){
                            doPassLoanApply(rs);
                        }
                    }
                    return true;
                }
            });

            //2.一次性把处理成功的bj_issued_batch_file数据，插入到putout_info
            batchInsertPutoutInfo();

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO 短信通知
            return TaskConstants.ES_FAILED;
        }finally {
            DBHelper.close(connection);
        }
        return TaskConstants.ES_SUCCESSFUL;
    }

    private void batchInsertPutoutInfo() {

        //查询bj_issued_batch_file中放款处理成功的数据

        //根据流水号插入putout_info

    }

    private void doPassLoanApply(ResultSet rs) throws SQLException  {

        //1.合同生效 2

        //2.变更放款状态为放款审批通过 2

        //3.更新putout_info放款表的处理状态

    }

    private void doRejectLoanApply(ResultSet rs) throws SQLException  {

        //对于放款申请拒绝的贷款，需要进行一下操作

        //1.执行冲放款交易，合同失效/终止 3，变更放款状态为审批拒绝 3

        //2.通知客户
    }
}
