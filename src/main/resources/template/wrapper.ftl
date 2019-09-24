package ${packageName}.wrapper;




import ${packageName}.dto.${className_d}DTO;
import ${packageName}.param.${className_d}Param;
import ${packageName}.entity.${className_d}Entity;
import ${packageName}.condition.${className_d}Condition;

/**
* @description: ${tableRemark}
* @author:   ${author}
* @date: ${dateTime}
**/

public class ${className_d}Wrapper {
     /**
      * param 转 entity
      * @param param
      * @return
      */
   	public static ${className_d}Entity paramToEntity(${className_d}Param param) {
   		    ${className_d}Entity ${className_x}Entity = new ${className_d}Entity();
   		    <#list tableCarrays as tableCarray>
   		     ${className_x}Entity.set${tableCarray.carrayName_d}(param.get${tableCarray.carrayName_d}());
            </#list>

        return ${className_x}Entity;

   	}

   	  /**
          * entity 转 dto
          * @param entity
          * @return
          */
       	public static ${className_d}DTO entityToDTO(${className_d}Entity entity) {
       		    ${className_d}DTO ${className_x}DTO= new ${className_d}DTO();
       		    <#list tableCarrays as tableCarray>
       		     ${className_x}DTO.set${tableCarray.carrayName_d}(entity.get${tableCarray.carrayName_d}());
                </#list>

            return ${className_x}DTO;

       	}

   	  /**
          * param 转 condition
          * @param param
          * @return
          */
       	public static  ${className_d}Condition paramToCondition(${className_d}Param param) {
       		    ${className_d}Condition ${className_x}Condition= new ${className_d}Condition();
       		    <#list tableCarraySet as tableCarray>
       		     ${className_x}Condition.set${tableCarray.carrayName_d}(param.get${tableCarray.carrayName_d}());
                </#list>

            return ${className_x}Condition;

       	}



}
