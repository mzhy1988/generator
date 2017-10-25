<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${javapackage}.${className}">
	<!-- auto generate default resultMap  jdbcType="CLOB" javaType="java.lang.String" typeHandler="com.hns.iusp.utils.OracleClobTypeHandler" -->
	<resultMap id="${className}Po" type="${javapackage}.${className}Po">
		<id column="ID" property="${primaryKey}" />
		<#list columns as propertyName>
		    <result column="${columns[propertyName_index]}" jdbcType="${columnTypes[propertyName_index]}" property="${columns[propertyName_index]}" />
		</#list>
	</resultMap>

	<select id="find${className}ById"
		parameterType="java.lang.Integer" resultMap="${className}Map">
		SELECT * FROM ${tableName} WHERE ${primaryKey} = ${r'#{id}'}
	</select>
	
	<select id="find${className}Count"
		parameterType="java.util.Map" resultType="int">
		SELECT count(${primaryKey}) FROM 
			 ${tableName} 
		WHERE 1=1 
		<#list columns as propertyName>
		<if test="${columns[propertyName_index]}!=null and ${columns[propertyName_index]}!=''"> 
			AND ${columns[propertyName_index]} = ${r'#{'}${columns[propertyName_index]}${r'}'}
		</if>
		</#list>
	</select>

	<select id="querySchoolTermForPage"
		parameterType="java.util.Map" resultMap="schollTermMap">
			SELECT * FROM 
			(
				SELECT temp.*, rownum row_id FROM 
				( 
					SELECT * FROM ${tableName} WHERE 1=1 
					<#list columns as propertyName>
					<if test="${columns[propertyName_index]}!=null and ${columns[propertyName_index]}!=''"> 
						AND ${columns[propertyName_index]} =  ${r'#{'}${columns[propertyName_index]}${r'}'}
					</if>
					</#list>
		 		) temp 
		 		WHERE rownum &lt;= ${r'#{endIndex}'}
			 ) 
			 WHERE row_id &gt;${r'#{startIndex}'}
	</select>
	
	<select id="query${className}Limit"
		parameterType="java.util.Map" resultMap="${className}Map">
			SELECT * FROM 
			(
				SELECT temp.*, rownum row_id FROM 
				( 
					SELECT * FROM ${tableName} where 1=1 ORDER BY ${primaryKey} DESC
		 		) temp 
		 		WHERE rownum &lt;=  ${r'#{endIndex}'}
			 ) 
			 WHERE row_id &gt;0
	</select>
	
	 
	 
	


	<delete id="delete${className}" parameterType="${javapackage}.${className}Po">
	    DELETE FROM ${tableName} where ${primaryKey} = ${r'#{id}'}
	</delete>
	
</mapper>