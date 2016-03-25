package ${packageName}.service.impl;
import java.util.*;
import ${packageName}.mapper.${className_d}Mapper;
import ${packageName}.entity.${className_d};
import ${packageName}.service.${className_d}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${className}
 */
@Service
public  class ${className_d}ServiceImpl implements ${className_d}Service{
    @Autowired
	private ${className_d}Mapper ${className_x}Mapper;
	
	@Override
	public List<${className_d}> listPage${className_d}(${className_d} ${className_x}){
		return ${className_x}Mapper.listPage${className_d}(${className_x});
	}
	
	@Override
	public Integer get${className_d}Count(){
	    return ${className_x}Mapper.get${className_d}Count();
	}
	
	@Override
	public void insert(${className_d} ${className_x}){
	     ${className_x}Mapper.insert(${className_x});
	}
	
	@Override
	public ${className_d} get${className_d}ById(${className_d} ${className_x}){
	    return ${className_x}Mapper.get${className_d}ById(${className_x});
	}
	
	@Override
	public List<${className_d}> list${className_d}(${className_d} ${className_x}){
	    return ${className_x}Mapper.list${className_d}(${className_x});
	}  
	
	@Override
	public void update${className_d}(${className_d} ${className_x}){
	     ${className_x}Mapper.update${className_d}(${className_x});
	}
	
	@Override
	public void  delete${className_d}(${className_d} ${className_x}){
	     ${className_x}Mapper.delete${className_d}(${className_x});
	}
	
	@Override
	public void  delete${className_d}ByIds (String[] ids){
	    ${className_x}Mapper.delete${className_d}ByIds(ids); 
	}
	
	@Override
	public void insertSelective(${className_d} ${className_x}){
	 ${className_x}Mapper.insertSelective(${className_x});
	}
	
	@Override
	public void updateByPrimaryKeySelective(${className_d} ${className_x}){
		 ${className_x}Mapper.updateByPrimaryKeySelective(${className_x});
	}
	
}