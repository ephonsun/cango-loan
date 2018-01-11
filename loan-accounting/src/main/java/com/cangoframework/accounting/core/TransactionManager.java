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
	 * ����ִ��
	 * @param transactionCode
	 * @param userId
	 * @param orgId
	 * @param manager �� ҵ��������ݹ�����
	 * @param connection �� �����������
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
				//��ʼִ��
				int runResult = procedure.run();
				if(runResult!=TransactionProcedure.RUN_SUCCESS){
					System.out.println("����"+procedure.getClass().getName()+"��ִ��ʱ���طǳɹ�����!");
					if(connection!=null){
						connection.rollback();
					}
					return TransactionManager.TRANSACTION_FAILED;
				}
			}
			//�ύ����
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
	 * ����ִ��ǰ�ı�ҪУ��
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
			throw new Exception("���׹�����û�г�ʼ��!");
		}
		if(transactionCode == null || getTransactionManagerByCode(transactionCode) == null){
			throw new Exception("���ס�"+transactionCode+"��������!");
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
