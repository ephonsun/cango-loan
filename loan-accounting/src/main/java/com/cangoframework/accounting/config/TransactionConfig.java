package com.cangoframework.accounting.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class TransactionConfig extends HashMap<String,TransactionProcedureManager>{
	private static final long serialVersionUID = 1216803206560476043L;
	
	public void buildConfig(String configPath) throws Exception {
		Document document = getDocument(configPath);
		Element rootElement = document.getRootElement();
		List<Element> xTransactionConfigs = rootElement.getChildren("TransactionConfig");
		for (Element xTransactionConfig : xTransactionConfigs) {
			String transactionCode = xTransactionConfig.getAttributeValue("TransactionCode", "");
			String transactionName = xTransactionConfig.getAttributeValue("TransactionName", "");
			
			//初始化TransactionManager
			TransactionProcedureManager manager = new TransactionProcedureManager();
			Map<Integer,TransactionProcedure> procedureMap = new TreeMap<Integer,TransactionProcedure>();
			List<Element> xProcedures = xTransactionConfig.getChildren("Procedure");
			for (Element xProcedure : xProcedures) {
				int id = Integer.parseInt(notNull(xProcedure.getAttributeValue("id")));
				if(procedureMap.containsKey(id)){
					throw new IllegalArgumentException("Procedure Id is repeated.");
				}
				String className = notNull(xProcedure.getAttributeValue("class"));
				String type = notNull(xProcedure.getAttributeValue("type"));
				TransactionProcedure transactionProcedure = (TransactionProcedure) Class.forName(className).newInstance();
				transactionProcedure.setScriptId(id);
				transactionProcedure.setTransactionCode(transactionCode);
				transactionProcedure.setTransactionName(transactionName);
				transactionProcedure.setType(type);
				//添加外部注入属性
				List<Element> xPropertys = xProcedure.getChildren("Property");
				for (Element xProperty : xPropertys) {
					String name = xProperty.getAttributeValue("name");
					if(name!=null){
						transactionProcedure.addProperty(name, xProperty.getAttributeValue("value"));
					}
				}
				procedureMap.put(id, transactionProcedure);
			}
			//把transactionProcedure添加到管理器
			Set<Integer> keySet = procedureMap.keySet();
			for (Integer integer : keySet) {
				manager.add(procedureMap.get(integer));
			}
			this.put(transactionCode, manager);
		}
	}

	private Document getDocument(String xmlConfigPath) throws FileNotFoundException, JDOMException,
			IOException {
		SAXBuilder builder = new SAXBuilder();
		InputStream inputStream = null;
		if(xmlConfigPath.startsWith("classpath:")){
			inputStream = TransactionConfig.class.getClassLoader().getResourceAsStream(xmlConfigPath.replace("classpath:", "").trim());
		}else{
			inputStream = new FileInputStream(xmlConfigPath);
		}
		Document document = builder.build(inputStream);
		return document;
	}
	
	private String notNull(String attributeValue) {
		if(attributeValue==null||"".equals(attributeValue.trim())){
			new IllegalArgumentException("attributeValue is empty.");
		}
		return attributeValue;
	}
}
