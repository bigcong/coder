package ${packageName}.entity;

import java.util.*;
/**
 * ${className}
 */
public class ${className_d} {

    <#--列出所有的字段 -->
	<#list tableCarrays as tableCarray>
	/**
	   ${tableCarray.remark}
	 */
	private ${tableCarray.carrayType} ${tableCarray.carrayName_x};
	
	</#list>
		
	//分页
	private Page page;
	
	
	 <#--字段的get和set -->
	<#list tableCarrays as tableCarray>
	
	public ${tableCarray.carrayType} get${tableCarray.carrayName_d}() {
		return ${tableCarray.carrayName_x};
	}

	public void set${tableCarray.carrayName_d}(${tableCarray.carrayType} ${tableCarray.carrayName_x}) {
		this.${tableCarray.carrayName_x} = ${tableCarray.carrayName_x};
	} 
	</#list>
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
