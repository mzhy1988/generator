package org.mybaties.freemark.generator;

import java.io.File;

public class UtilFile {
	public static void createDir(String path){
		if(null != path && !"".equals(path)){
			File file = new File(path);
			file.mkdirs();
		}
	}
	public static String getPackageDir(Params params){
		String separatorChar =  String.valueOf(File.separatorChar);
		String packageDir = separatorChar;
		String[] lst = params.getJavapackage().split("\\.");
		for (int i = 0; i < lst.length; i++) {
			packageDir = packageDir + lst[i]+separatorChar;
		}
		return packageDir;
	}
	public static void initDirName(Params params){
		String packageDir = getPackageDir(params);
		//1.po
		String poDir = params.getOsdir() + packageDir + "dao";
		createDir(poDir);
		//2.vo
		String voDir = params.getOsdir() + packageDir + "bean";
		createDir(voDir);
		//3.xml
		String xmlDir = params.getOsdir() + packageDir + "xml";
		createDir(xmlDir);
	}
}
