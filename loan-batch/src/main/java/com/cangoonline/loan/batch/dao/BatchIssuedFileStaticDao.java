package com.cangoonline.loan.batch.dao;

import com.cangoframework.dbpool.DBHelper;
import com.cangoonline.loan.batch.dao.base.BaseDao;
import com.cangoonline.loan.batch.entity.BatchIssuedFile;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cango on 2018/1/10.
 */
public class BatchIssuedFileStaticDao extends BaseDao {

    public List<BatchIssuedFile> listBatchIssuedFile(String fileType) throws SQLException {
        String sql = "select * from bj_issued_batch_file " +
                "where fileType = ? " +
                "and resultCode=? " +
                "and transDate = (select businessDate from system_setup where systemid = 1)";
        return DBHelper.getListBean(sql, BatchIssuedFile.class, fileType , BatchIssuedFile.RESULT_SUCCESS);
    }
}
