package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Role;
import cn.demo.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	@RequestMapping("/page")
	@ResponseBody
	public PageList<Role> page(Conditions con, Role role){
		return roleService.search(con, role);
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<Role> findAll(){
		return roleService.selectAll();
	}

	//增加数据
	@RequestMapping("/save")
	@ResponseBody
	public Map save(Role role){
		HashMap resultMap = new HashMap();
		try {
			int i = roleService.insert(role);
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
	public Map update(Role role){
		HashMap resultMap = new HashMap();
		try {
			int i = roleService.updateByPrimaryKey(role);
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
			int i = roleService.deleteByPrimaryKey(id);
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

//	@ModelAttribute("editRole")
//	public Role getEmpolyee(Long id, String cmd){
//		if ("update".equals(cmd)){
//			Role role = roleService.selectByPrimaryKey(id);
//			return role;
//		}
//		return null;
//	}
}
