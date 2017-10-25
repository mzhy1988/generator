package org.mybaties.freemark.generator;

import java.io.File;
import java.util.List;
import java.util.Map;
public class TestFreeMarker {
	public static void main(String[] args) {
		// 1.创建目录
		Params params = UtilXML.params;
		UtilFile.initDirName(params);
		// 2.生成文件
		List<Tables> tables = UtilXML.tableList;
		for (Tables table : tables) {
			System.out.println(table.getTableName());
			String javaClassName = UtilString.capitalize(UtilString
					.dbNameToVarName(table.getTableName()));
			Map<Object, Object> map = UtilFreemarker.getTableInfo(table
					.getTableName());
			map.put("author", params.getAuthor());
			map.put("createTime", UtilDate.getToday());
			map.put("project", params.getProject());
			map.put("className", javaClassName);
			map.put("voClassName", javaClassName);
			map.put("javapackage", params.getJavapackage());
			//1.po
			String poName = params.getOsdir() + UtilFile.getPackageDir(params) + "bean" + File.separatorChar + javaClassName + "Po.java";
			UtilFreemarker.generateFile(poName, "javapo.ftl", map);
			// 2.vo
			//String voName = params.getOsdir() + File.separatorChar + "vo" + File.separatorChar + javaClassName + "VO.java";
			//UtilFreemarker.generateFile(voName, "javavo.ftl", map);
			// 2.dao
			String daoName = params.getOsdir() + UtilFile.getPackageDir(params) + "dao" + File.separatorChar + javaClassName + "Dao.java";
			UtilFreemarker.generateFile(daoName, "JavaDao.ftl", map);
			// 2.daoimpl
			String daoImplName = params.getOsdir() + UtilFile.getPackageDir(params) + "dao" + File.separatorChar + javaClassName + "DaoImpl.java";
			UtilFreemarker.generateFile(daoImplName, "JavaDaoImpl.ftl", map);
			// 2.daoimpl
			String mapName = params.getOsdir() + UtilFile.getPackageDir(params) + "bean" + File.separatorChar + javaClassName + "Map.xml";
			UtilFreemarker.generateFile(mapName, "Map.ftl", map);
			// 3.xml
			//String xmlName = params.getOsdir() + File.separatorChar + "xml" + File.separatorChar + javaClassName + ".xml";
			//UtilFreemarker.generateFile(xmlName, "xml.ftl", map);
		}
	}
}
