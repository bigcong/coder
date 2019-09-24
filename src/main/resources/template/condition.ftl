package ${packageName}.condition;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


/**
* @description: ${tableRemark}
* @author:   ${author}
* @date: ${dateTime}
**/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ${className_d}Condition {

    <#--列出所有的字段 -->
	<#list tableCarraySet as tableCarray>
	/**
	   ${tableCarray.remark}
	 */
	private ${tableCarray.carrayType} ${tableCarray.carrayName_x};

	</#list>



}
