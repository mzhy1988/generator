/*
 * Copyright (C),  
 * FileName:
 * Author:  ${author} 
 * Date:     ${createTime} 
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package ${javapackage}.dao;

import java.util.List;

import ${javapackage}.bean.${className}Po;
import ${javapackage}.utils.QueryParam;
import ${javapackage}.utils.QueryResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * @author mazy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ${className}Dao {

    QueryResult<${className}Po> query${className}ForPage(QueryParam<${className}Po> param);

    Integer add${className}(${className}Po bean);

    Integer update${className}(${className}Po bean);

    Integer delete${className}ById(Integer id);

    ${className}Po find${className}ById(Integer id);
	
	List<${className}Po> query${className}ByLimit(String type,int size);
}