package com.cangoonline.loan.batch.dao.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cango on 2018/1/5.
 */
public class BaseDao {
    protected static Logger logger = LoggerFactory.getLogger(BaseDao.class);
    protected static String getInString(String ... preTasks) {
        String inStr = "";
        if(preTasks==null||preTasks.length==0){
            return "''";
        }
        for (int i = 0; i < preTasks.length; i++) {
            inStr += "'"+preTasks[i]+"',";
        }
        return inStr.substring(0,inStr.lastIndexOf(","));
    }

}
