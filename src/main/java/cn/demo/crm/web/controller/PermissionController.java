package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private IPermissionService permissionService;
	@RequestMapping("/page")
	@ResponseBody
	public PageList<Permission> page(Conditions con, Permission permission){
		return permissionService.search(con, permission);
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<Permission> findAll(){
		return permissionService.selectAll();
	}

	//增加数据
	@RequestMapping("/save")
	@ResponseBody
	public Map save(Permission permission){
		HashMap resultMap = new HashMap();
		try {
			int i = permissionService.insert(permission);
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
	public Map update(@ModelAttribute("editPermission") Permission permission){
		HashMap resultMap = new HashMap();
		try {
			int i = permissionService.updateByPrimaryKey(permission);
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
			int i = permissionService.deleteByPrimaryKey(id);
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

	@ModelAttribute("editPermission")
	public Permission getEmpolyee(Long id, String cmd){
		if ("update".equals(cmd)){
			Permission permission = permissionService.selectByPrimaryKey(id);
			return permission;
		}
		return null;
	}
}
