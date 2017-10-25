package ${javapackage}.bean;

/**
 * <p>${title}</p>
 * 类名:${className}PO<br>
 * 创建人:${author}<br>
 * 创建时间:${createTime}<br>
 */
 
public class ${className}Po {

	<#list columns as propertyName>
    
    private ${columnTypes[propertyName_index]} ${propertyName};
    </#list>
    
    <#list columns as propertyName>
    
    public ${columnTypes[propertyName_index]} get${propertyName?cap_first}() {
		return ${propertyName};
	}
	
	public void set${propertyName?cap_first}(${columnTypes[propertyName_index]} ${propertyName}) {
		this.${propertyName} = ${propertyName};
	}
    </#list>

}