package com.cangoframework.accounting.core;

import java.sql.Connection;
import java.util.Iterator;

import javax.sql.DataSource;

import com.cangoframework.accounting.config.TransactionConfig;
import com.cangoframework.accounting.config.TransactionProcedure;
import com.cangoframework.accounting.config.TransactionProcedureManager;

public class TransactionManager {
	public static final int TRANSACTION_SUCCESS = 1;
	public static final int TRANSACTION_FAILED = 2;
	
	private static TransactionConfig transactionConfig;
	
	public synchronized static void loadTransactionConfig() throws Exception{
		loadTransactionConfig("classpath:transaction-config.xml");
	}
	public synchronized static void loadTransactionConfig(String configPath) throws Exception{
		if(transactionConfig==null){
			transactionConfig = new TransactionConfig();
			transactionConfig.buildConfig(configPath);
		}
	}
	
	public synchronized static void reLoadTransactionConfig(String configPath) throws Exception{
		transactionConfig = new TransactionConfig();
		transactionConfig.buildConfig(configPath);
	}
	
	public static TransactionProcedureManager getTransactionManagerByCode(String transactionCode){
		if(transactionConfig.containsKey(transactionCode)){
			return transactionConfig.get(transactionCode);
		} return null;
	}
	
	public static int excuteTransaction(String transactionCode,String userId,String orgId,BusinessObjectManager manager,DataSource dataSource) 
			throws Exception{
		Connection connection = null;
		if(dataSource!=null){
			connection = dataSource.getConnection();
		}
		return excuteTransaction(transactionCode, userId, orgId, manager, connection);
	}
	
	/**
	 * 交易执行
	 * @param transactionCode
	 * @param userId
	 * @param orgId
	 * @param manager ： 业务对象数据管理器
	 * @param connection ： 事务操作连接
	 * @return
	 * @throws Exception
	 */
	public static int excuteTransaction(String transactionCode,String userId,String orgId,BusinessObjectManager manager,Connection connection)
		throws Exception{
		checkParams(transactionCode,userId,orgId,manager,connection);
		TransactionProcedureManager transactionManager = getTransactionManagerByCode(transactionCode);
		Iterator<TransactionProcedure> it = transactionManager.iterator();
		try {
			if(connection!=null)
				connection.setAutoCommit(false);
			while(it.hasNext()){
				TransactionProcedure procedure = it.next();
				procedure.setBusinessObjectManager(manager);
				procedure.setConnection(connection);
				procedure.setOrgId(orgId);
				procedure.setUserId(userId);
				//开始执行
				int runResult = procedure.run();
				if(runResult!=TransactionProcedure.RUN_SUCCESS){
					System.out.println("程序【"+procedure.getClass().getName()+"】执行时返回非成功代码!");
					if(connection!=null){
						connection.rollback();
					}
					return TransactionManager.TRANSACTION_FAILED;
				}
			}
			//提交事务
			if(connection!=null){
				connection.commit();
			}
			return TransactionManager.TRANSACTION_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			if(connection!=null){
				connection.rollback();
			}
			throw e;
		}
	}
	
	/**
	 * 交易执行前的必要校验
	 * @param transactionCode
	 * @param userId
	 * @param orgId
	 * @param manager
	 * @param connection
	 * @throws Exception
	 */
	private static void checkParams(String transactionCode,
			String userId, String orgId, BusinessObjectManager manager,
			Connection connection) throws Exception {
		if(transactionConfig==null){
			throw new Exception("交易管理器没有初始化!");
		}
		if(transactionCode == null || getTransactionManagerByCode(transactionCode) == null){
			throw new Exception("交易【"+transactionCode+"】不存在!");
		}
		//do something...
	}

	public static void main(String[] args) {
		try {
			TransactionManager.loadTransactionConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
