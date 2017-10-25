package org.mybaties.freemark.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析config.xml
 */
public class UtilXML {
	public static Params params = new Params();
	public static DataSource dataSource = new DataSource();
	public static List<Tables> tableList = new ArrayList<Tables>();
	static{
		parseXml("config.xml");
	}
	private static void parseXml(String xmlResource){
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(UtilXML.class.getClassLoader().getResource(xmlResource).getFile());
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for(Element element : elements){
				if("params".equals(element.getName())){
					List<Element> paramElements = element.elements();
					for(Element param : paramElements){
						if("osdir".equals(param.getName())){
							params.setOsdir(param.attributeValue("value"));
						}else if("javapackage".equals(param.getName())){
							params.setJavapackage(param.attributeValue("value"));
						}else if("author".equals(param.getName())){
							params.setAuthor(param.attributeValue("value"));
						}else if("project".equals(param.getName())){
							params.setProject(param.attributeValue("value"));
						}
					}
				}else if("dataSource".equals(element.getName())){
					List<Element> ele = element.elements();
					for(Element e : ele){
						if("driver".equals(e.getName())){
							dataSource.setDriver(e.attributeValue("value"));
						}else if("url".equals(e.getName())){
							dataSource.setUrl(e.attributeValue("value"));
						}else if("username".equals(e.getName())){
							dataSource.setUsername(e.attributeValue("value"));
						}else if("password".equals(e.getName())){
							dataSource.setPassword(e.attributeValue("value"));
						}
					}
				}else if("tables".equals(element.getName())){
					List<Element> tables = element.elements();
					for(Element table : tables){
						if("table".equals(table.getName())){
							//yes 全部生成数据库中的所有表
							if("yes".equalsIgnoreCase(table.attributeValue("value"))){
								List<String> tableNames = UtilDB.getTableNames();
								for (String tableName : tableNames) {
									Tables t = new Tables();
									t.setTableName(tableName);
									tableList.add(t);
								}
								break;
								//no 只生成config.xml配置的表
							} else if ("no".equalsIgnoreCase(table.attributeValue("value"))) {
								continue;
							}
							Tables t = new Tables();
							t.setTableName(table.attributeValue("value"));
							tableList.add(t);
						}
					}
				}
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	public static Params getParams() {
		return params;
	}
	public static void setParams(Params params) {
		UtilXML.params = params;
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	public static void setDataSource(DataSource dataSource) {
		UtilXML.dataSource = dataSource;
	}
	public static List<Tables> getTableList() {
		return tableList;
	}
	public static void setTableList(List<Tables> tableList) {
		UtilXML.tableList = tableList;
	}
}
