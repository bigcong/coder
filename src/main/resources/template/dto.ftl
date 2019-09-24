package ${packageName}.dto;


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
public class ${className_d}DTO {

    <#--列出所有的字段 -->
	<#list tableCarrays as tableCarray>
	/**
	   ${tableCarray.remark}
	 */
	private ${tableCarray.carrayType} ${tableCarray.carrayName_x};

	</#list>



}
