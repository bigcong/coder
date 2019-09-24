package ${packageName}.dao;
import java.util.*;
import ${packageName}.entity.${className_d}Entity;
import ${packageName}.condition.${className_d}Condition;

/**
* @description: ${tableRemark}
* @author:   ${author}
* @date: ${dateTime}
**/
public interface ${className_d}DAO{


	/**
	* 根据条件查询
	* @param ${className_x}
	* @return
	*/
	 List<${className_d}Entity> queryByCondition(${className_d}Condition ${className_x});

	/**
	* 有条件的更新
	* @param ${className_x}
	* @return
	*/
	 void insertSelective(${className_d}Entity ${className_x});

	/**
	* 根据主键有条件的更新
	* @param ${className_x}
	* @return
	*/
	 void updateByPrimaryKeySelective(${className_d}Entity ${className_x});
	

	/**
	* 根据主键查询
	* @param ${key_x}
	* @return
	*/
	 ${className_d}Entity getByPrimaryKey(${keyCarrayType} ${key_x});
	

	 

	
}