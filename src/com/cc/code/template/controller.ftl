package ${packageName}.controller;

import ${packageName}.entity.${className_d};
import ${packageName}.service.${className_d}Service;






@RestController
@RequestMapping(value = "/${className_x}")
public class ${className_d}Controller {

	@Autowired
	private ${className_d}Service ${className_x}Service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

	/**
	 * 显示列表
	 * @param ${className_x}
	 * @return
	 */
	@RequestMapping("list")
	public Map<String, Object> list(${className_d} ${className_x}) {
		
		List<${className_d}> ${className_x}List = ${className_x}Service.listPage${className_d}(${className_x});
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", ${className_x}List);
        map.put("page", ${className_x});
		return map;
	}
	
	/**
	 * 请求编辑页面
	 * @param ${className_x}
	 * @return
	 */
	@RequestMapping(value = "/load")
	public ${className_d}  load( ${className_d} ${className_x}) {
	 ${className_x} = ${className_x}Service.get${className_d}ById(${className_x}.get${key_d}());
		return ${className_x};
	}

	/**
	 * 保存
	 * 
	 * @param ${className_x}
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ${className_d} save(${className_d} ${className_x}) {
	    try {
			if (${className_x}.get${key_d}() == null || ${className_x}.get${key_d}().intValue() == 0) {
				${className_x}Service.insertSelective(${className_x});
			} else {
				${className_x}Service.updateByPrimaryKeySelective(${className_x});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ${className_x};
	}



	
	/**
	 * 查看详情
	 * @param  ${className_x}
	 */
	@RequestMapping(value = "/delete")
	public String delete(${className_d} ${className_x} ,ModelMap modelMap) {
		${className_x}Service.delete${className_d}(${className_x});
		return "success";
	}
}
