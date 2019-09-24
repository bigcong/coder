package ${packageName}.service;
import java.util.*;
import java.util.stream.Collectors;



import ${packageName}.dao.${className_d}DAO;
import ${packageName}.dto.${className_d}DTO;
import ${packageName}.entity.${className_d}Entity;
import ${packageName}.param.${className_d}Param;
import ${packageName}.condition.${className_d}Condition;
import ${packageName}.wrapper.${className_d}Wrapper;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* @description: ${tableRemark}
* @author:   ${author}
* @date: ${dateTime}
**/
@Service
public class ${className_d}Service{
    @Resource
    private  ${className_d}DAO  ${className_x}DAO;

   

	/**
	* 根据条件查询
	* @param ${className_x}
	* @return
	*/
	public List<${className_d}DTO> queryByCondition(${className_d}Param ${className_x}){

	  ${className_d}Condition ${className_x}Condition =${className_d}Wrapper.paramToCondition(${className_x});

          return      ${className_x}DAO.queryByCondition(${className_x}Condition).stream().map(${className_d}Wrapper::entityToDTO).collect(
                                                                                               Collectors.toList());



	}

	/**
	* 有条件的更新
	* @param ${className_x}
	* @return
	*/
	public void insertSelective(${className_d}Param ${className_x}){

	  ${className_d}Entity ${className_x}Entity =${className_d}Wrapper.paramToEntity(${className_x});
                ${className_x}DAO.insertSelective(${className_x}Entity);
	}

	/**
	* 根据主键有条件的更新
	* @param ${className_x}
	* @return
	*/
	public void updateByPrimaryKeySelective(${className_d}Param ${className_x}){
	        ${className_d}Entity ${className_x}Entity =${className_d}Wrapper.paramToEntity(${className_x});
            ${className_x}DAO.updateByPrimaryKeySelective(${className_x}Entity);
	}


	/**
	* 根据主键查询
	* @param ${key_x}
	* @return
	*/
	public ${className_d}DTO getByPrimaryKey(${keyCarrayType} ${key_x}){
      ${className_d}Entity ${className_x}Entity  =    ${className_x}DAO.getByPrimaryKey(${key_x});
      return ${className_d}Wrapper.entityToDTO( ${className_x}Entity );
	}

	
}