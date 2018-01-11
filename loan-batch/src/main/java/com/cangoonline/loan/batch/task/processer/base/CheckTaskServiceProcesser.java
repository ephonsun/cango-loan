package com.cangoonline.loan.batch.task.processer.base;

import com.cangoframework.dbpool.DBHelper;
import com.cangoonline.loan.batch.entity.TaskPool;
import com.cangoonline.loan.batch.task.Processer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class CheckTaskServiceProcesser implements Processer {
	private static Logger logger = LoggerFactory.getLogger(CheckTaskServiceProcesser.class);
	@Override
	public boolean process(TaskPool task, Connection conn) {
		
		String sql = "select count(1) from service_setup "
				+ "where serviceflag = '1' "
				+ "and '@'||businessType||'@' like '%@"+task.getBusinessType()+"@%' "
				+ "and to_number(to_char(sysdate,'HH24')) between starttime and endtime-1 ";// TODO 函数转换
		try {
			int count = DBHelper.getCount(conn, sql);
			if(count > 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//系统关闭-更新任务状态02
		try {
			//ApproveDao.updateTaskStatus(ThirdTaskPool.STATUS_READY, conn, task);
			// TODO 更新任务池任务状态
			conn.commit();
			logger.info("产品业务["+task.getBusinessType()+"]系统已关闭！");
		} catch (SQLException e) {
			e.printStackTrace();
			try {conn.rollback();} catch (SQLException ex) {
				logger.error("事物回滚失败！");
			}
		}
		return false;
	}

}
