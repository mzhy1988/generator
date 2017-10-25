/*
 * Copyright (C)
 * FileName: ArticleDaoImpl.java
 * Author:  ${author} 
 * Date:    ${createTime} 
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package ${javapackage}.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ${javapackage}.base.BaseDao;
import ${javapackage}.bean.${className}Po;
import ${javapackage}.dao.${className}Dao;
import ${javapackage}.utils.QueryParam;
import ${javapackage}.utils.QueryResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author  ${author} 
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class ${className}DaoImpl extends BaseDao implements ${className}Dao {

    /*
     * (non-Javadoc)
     * 
     * @see ${javapackage}.${className}Dao#${className}(${javapackage}.${className})
     */
    @Override
    public Integer add${className}(${className}Po bean) {
        return this.getSqlSession().insert("${className}.add${className}", bean);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public Integer delete${className}ById(Integer id) {
        return this.getSqlSession().delete("${className}.delete${className}ById", id);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public ${className}Po find${className}ById(Integer id) {
        return this.getSqlSession().selectOne("${className}.find${className}ById", id);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public QueryResult<${className}Po> query${className}ForPage(QueryParam<${className}Po> queryParam) {

    	${className}Po queryParamObj = queryParam.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

       // paramMap.put("downName", StringUtil.trim(queryParamObj.getDownName()));
       // paramMap.put("uploader", queryParamObj.getUploader());

        Integer count = this.getSqlSession().selectOne("${className}.find${className}Count", paramMap);

        QueryResult<${className}Po> queryResult = new QueryResult<${className}Po>(count, queryParam.getPageSize(), queryParam
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<${className}Po> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("${className}.query${className}ForPage", paramMap);
            queryResult.setDatas(list);
        }

        return queryResult;
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public Integer update${className}(${className}Po bean) {
        return this.getSqlSession().update("${className}.update${className}", bean);
    }

    /*
     * (non-Javadoc)
     */
    @Override
    public List<${className}Po> query${className}ByLimit(String type,int size) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("endIndex", size);
        return this.getSqlSession().selectList("${className}.query${className}ByLimit", paramMap);
    }

}
