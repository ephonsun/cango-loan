package com.cangoonline.loan.batch.dao;

import com.cangoframework.dbpool.DBHelper;
import com.cangoonline.loan.batch.dao.base.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by huangchengkang on 2018/1/11.
 *
 * 一些公共的數據庫操作
 */
public class CommonStaticDao extends BaseDao {

    /**
     * 判定第Taskpool标志，如果为关闭，则返回系统已关闭。
     *
     * @param conn
     * @return
     */
    public static boolean checkTaskPoolFlag(Connection conn) {

        try {
            int count = DBHelper.getCount("select count(1) from SYSTEM_SETUP where thirdflag = '2'");
            if(count > 0){
                return false;//系统关闭
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
