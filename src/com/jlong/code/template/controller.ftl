package ${packageName}.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import ${packageName}.entity.${className_d};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ${packageName}.service.${className_d}Service;


@Controller
@RequestMapping(value = "/${className}")
public class ${className_d}Controller {

	@Autowired
	private ${className_d}Service ${className}Service;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}

	/**
	 * 显示列表
	 * @param ${className}
	 * @return
	 */
	@RequestMapping
	public String list(${className_d} ${className},ModelMap modelMap) {
		
		List<${className_d}> ${className}List = 
		${className}Service.listPage${className_d}(${className_x});
		modelMap.addAttribute("${className}List", ${className}List);
		
		return "${className_x}/${className_d}List";
	}
	
	/**
	 * 请求编辑页面
	 * @param ${className_x}Id
	 * @return
	 */
	@RequestMapping(value = "/load")
	public String load(@RequestParam(required=false) Integer id,ModelMap modelMap) {
			
		${className_d} ${className_x} = ${className}Service.get${className_d}ById(id);
		modelMap.addAttribute("${className}", ${className_x});
		
		return "${className_x}/${className_d}Info";
	}

	/**
	 * 保存店铺信息
	 * 
	 * @param Equipment
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(${className_d} ${className_x},ModelMap modelMap) {
	    try {
			if (${className_x}.getId() == null || ${className_x}.getId().intValue() == 0) {
				${className}Service.insert${className_d}(${className_x});
			} else {
				${className}Service.update${className_d}(${className_x});
			}
		    modelMap.addAttribute("success","success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "system/save_result";
	}


	/**
	 * 删除
	 * @param  ${className_x}Ids
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(@RequestParam String ids) {
	    String[] delIds = ids.split(",");
		${className}Service.delete${className_d}ByIds(delIds);
		return "success";
	}
	
	/**
	 * 查看详情
	 * @param  ${className_x}Id
	 */
	@RequestMapping(value = "/view")
	@ResponseBody
	public String delete(@RequestParam int id,ModelMap modelMap) {
		${className_d} ${className_x} = ${className}Service.get${className_d}ById(id);
		modelMap.addAttribute("${className}", ${className_x});
		return "${className_x}/${className_d}View";
	}
}
