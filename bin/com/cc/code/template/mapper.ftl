package ${packageName}.mapper;
import java.util.*;
import ${packageName}.entity.${className_d};
/**
 * ${className}
 */
public interface ${className_d}Mapper{
   
	
	/**
	 * 根据条件 查询全部listPageAccount
	 */
	 
	public List<${className_d}> listPage${className_d}(${className_d} ${className_x});
	
	/**
	 *  获取${className_d}的数量
	 */
	public Integer get${className_d}Count();
	
	
	public void insert(${className_d} ${className_x});
	/**
	 *有条件的更新
	 */
	
	public void insertSelective(${className_d} ${className_x});
	/**
	 *根据主键有条件的更新
	 */
	
	public void updateByPrimaryKeySelective(${className_d} ${className_x});
	
	/**
	 * 根据主键查询(唯一)
	 */
	public ${className_d} get${className_d}ById(${className_d} ${className_x});
	
	/**
	 * 根据根据条件查询${className_d} 
	 */
	public List<${className_d}> list${className_d}(${className_d} ${className_x});  
	 
	 /**
	 * 更新${className_d}
	 */
	public void update${className_d}(${className_d} ${className_x});
	
	 /**
	 * 很据实体类删除
	 */
	
	public void  delete${className_d}(${className_d} ${className_x});
	
	 /**
	 * 根据主键删除
	 */
	public void  delete${className_d}ByIds (String[] id);
	
}