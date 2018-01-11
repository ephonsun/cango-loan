package com.cangoonline.loan.batch.task;

import com.cangoframework.dbpool.DBHelper;
import com.cangoframework.task.ExecuteUnit;
import com.cangoframework.task.TaskConstants;
import com.cangoframework.task.util.Tools;
import com.cangoonline.loan.batch.dao.CommonStaticDao;
import com.cangoonline.loan.batch.dao.PutoutApplyStaticDao;
import com.cangoonline.loan.batch.entity.TaskPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cango on 2018/1/10.
 */
public class PutoutApplyThreadUnit extends ExecuteUnit{
    private static Logger logger = LoggerFactory.getLogger(PutoutApplyThreadUnit.class);

    private Connection conn = null;
    private int threadNumber;
    private int threadId;

    @Override
    public int execute() {

        try {

            logger.info("==========>PutoutApplyThreadUnit 执行开始....");

            //初始化数据
            conn = DBHelper.getConnection();
            conn.setAutoCommit(false);

            //检查定时任务执行条件
            if(!CommonStaticDao.checkTaskPoolFlag(conn)){
                return TaskConstants.ES_SUCCESSFUL;
            }

            //分配线程任务
            List<TaskPool> tasks = getReadyTasks(conn,threadNumber,threadId);

            //循环处理任务
            for (int i = 0; i < tasks.size(); i++) {
                TaskPool task = tasks.get(i);
                long startTime = System.currentTimeMillis();
                doTask(task,conn);
                long endTime = System.currentTimeMillis();
                logger.info("@任务【{}】处理完成,花费时间为:{}毫秒!",task.getSerialNo(), endTime - startTime);
            }
            logger.info("==========>PutoutApplyThreadUnit 执行完成....");
            return TaskConstants.ES_SUCCESSFUL;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DBHelper.close(conn);
        }
        return TaskConstants.ES_FAILED;
    }

    /**
     * 单笔记录的处理逻辑
     */
    private void doTask(TaskPool task,Connection conn) {

        String[] processers = null;
        //根据业务类型处理不同的逻辑
        String sClass = super.getTarget().getProperty(task.getBusinessType(),"");
        if(!Tools.isEmpty(sClass)){
            processers = sClass.split(",");
        }else{
            logger.warn("["+task.getBusinessType()+"]不支持的渠道贷款申请业务！");
            return;
        }

        String processer = "";

        for (int i = 0; i < processers.length; i++) {//针对单条记录，都会循环
            processer = processers[i];
            try {
                Object Oprocesser = Class.forName(processer.trim()).newInstance();
                if (Oprocesser instanceof Processer) {

                    if(!CommonStaticDao.checkTaskPoolFlag(conn)) break;//检查批量状态，直接跳过该条，跳出for循环

                    boolean processflag = ((Processer) Oprocesser).process(task,conn);
                    if (!processflag) break;//处理失败，直接跳过该条，跳出for循环

                } else {
                    throw new IllegalArgumentException("processers属性中配置的类必须实现["+Processer.class.getName()+"]接口");
                }

            } catch (Exception e) {
                e.printStackTrace();
                //跳过当前申请处理
                break;
            }
        }
    }


    /**
     * 根据线程号码取数据，并且更新任务池数据状态为处理中
     */
    private List<TaskPool> getReadyTasks(Connection conn, int threadNumber, int threadId) throws Exception {

        //分配线程任务
        List<TaskPool> tasks = PutoutApplyStaticDao.getReadyTasks(conn ,threadNumber, threadId);

        if(tasks==null||tasks.isEmpty()){
            tasks = new ArrayList<TaskPool>();
            logger.info("没有分配到任务,休息一下...");
            return tasks;
        }

        //立即更新所选的任务状态为处理中
        String[] ids = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            ids[i] = String.valueOf(tasks.get(i).getSerialNo());
        }
        PutoutApplyStaticDao.updateTaskStatusById(conn,TaskPool.STATUS_RUNNING,ids);

        logger.info("本次从任务池中总共分配到["+tasks.size()+"]条任务...");
        return tasks;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public int getThreadId() {
        return threadId;
    }
}
