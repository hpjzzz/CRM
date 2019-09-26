package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.${domain};
import cn.demo.crm.service.I${domain}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/${domainlower}")
public class ${domain}Controller {

	@Autowired
	private I${domain}Service ${domainlower}Service;
	@RequestMapping("/page")
	@ResponseBody
	public PageList<${domain}> page(Conditions con, ${domain} ${domainlower}){
		return ${domainlower}Service.search(con, ${domainlower});
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<${domain}> findAll(){
		return ${domainlower}Service.selectAll();
	}

	//增加数据
	@RequestMapping("/save")
	@ResponseBody
	public Map save(${domain} ${domainlower}){
		HashMap resultMap = new HashMap();
		try {
			int i = ${domainlower}Service.insert(${domainlower});
			if (i==1){
				resultMap.put("success", true);
			}else {
				resultMap.put("success", false);
				resultMap.put("msg", "数据不存在");
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	//更新数据
	@RequestMapping("/update")
	@ResponseBody
	public Map update(@ModelAttribute("edit${domain}") ${domain} ${domainlower}){
		HashMap resultMap = new HashMap();
		try {
			int i = ${domainlower}Service.updateByPrimaryKey(${domainlower});
			if (i==1){
				resultMap.put("success", true);
			}else {
				resultMap.put("success", false);
				resultMap.put("msg", "数据不存在");
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}
	//删除方法
	@RequestMapping("/delete")
	@ResponseBody
	public Map delete(Long id){
		HashMap resultMap = new HashMap();
		try {
			int i = ${domainlower}Service.deleteByPrimaryKey(id);
			if (i==1){
				resultMap.put("success", true);
			}else {
				resultMap.put("success", false);
				resultMap.put("msg", "数据不存在");
			}
		} catch (Exception e) {
			resultMap.put("success", false);
			resultMap.put("msg", e.getMessage());
		}
		return resultMap;
	}

	@ModelAttribute("edit${domain}")
	public ${domain} getEmpolyee(Long id, String cmd){
		if ("update".equals(cmd)){
			${domain} ${domainlower} = ${domainlower}Service.selectByPrimaryKey(id);
			return ${domainlower};
		}
		return null;
	}
}
