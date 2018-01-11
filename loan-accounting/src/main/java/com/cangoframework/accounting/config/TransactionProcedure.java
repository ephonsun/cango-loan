package com.cangoframework.accounting.config;

import java.sql.Connection;
import java.util.Properties;

import com.cangoframework.accounting.core.BusinessObjectManager;

public abstract class TransactionProcedure {
	public static final int RUN_SUCCESS = 1;
	public static final int RUN_FAILED = 2;
	private Integer scriptId;
	private String transactionCode;
	private String transactionName;
	private String type;
	private String userId;
	private String orgId;
	private Properties procedureProperties = new Properties();//外部配置参数
	
	private BusinessObjectManager businessObjectManager;//存储业务数据对象
	private Connection connection;//连接
	
	public abstract int run() throws Exception;
	
	public Integer getScriptId() {
		return scriptId;
	}

	public void setScriptId(Integer scriptId) {
		this.scriptId = scriptId;
	}

	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty(String key) {
		return procedureProperties.getProperty(key);
	}
	public void addProperty(String key,String value) {
		procedureProperties.setProperty(key, value);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public BusinessObjectManager getBusinessObjectManager() {
		return businessObjectManager;
	}

	public void setBusinessObjectManager(BusinessObjectManager businessObjectManager) {
		this.businessObjectManager = businessObjectManager;
	}
	
}
