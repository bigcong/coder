package ${packageName}.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ${packageName}.entity.${className_d};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${packageName}.service.${className_d}Service;

@Controller
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
* @return
*/
@RequestMapping("list")
public String list(${className_d} ${className_x},ModelMap modelMap) {

List<${className_d}> ${className_x}List =
${className_x}Service.listPage${className_d}(${className_x});
modelMap.addAttribute("${className_x}List", ${className_x}List);
modelMap.addAttribute("${className_x}", ${className_x});
return "${className_x}/${className_d}List";
}

/**
* 请求编辑页面
* @return
*/
@RequestMapping(value = "/load")
public String load( ${className_d} ${className_x},ModelMap modelMap) {
${className_x} = ${className_x}Service.get${className_d}ById(${className_x}.get${key_d}()+"");
modelMap.addAttribute("${className_x}", ${className_x});

return "${className_x}/${className_d}Info";
}






/**
* 查看详情
*/
@RequestMapping(value = "/delete")
@ResponseBody
public String delete(${className_d} ${className_x} ,ModelMap modelMap) {
${className_x}Service.delete${className_d}(${className_x});
return "success";
}






	/**
	 * 保存
	 *
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
	public ${className_d} save(${className_d} ${className_x}) {
	    try {
			if (${className_x}.get${key_d}() == null ) {
				${className_x}Service.insertSelective(${className_x});
			} else {
				${className_x}Service.updateByPrimaryKeySelective(${className_x});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ${className_x};
	}





}
