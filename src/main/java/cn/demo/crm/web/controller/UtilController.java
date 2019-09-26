package cn.demo.crm.web.controller;

import cn.demo.crm.domain.Department;
import cn.demo.crm.domain.Employee;
import cn.demo.crm.domain.Menu;
import cn.demo.crm.service.IDepartmentService;
import cn.demo.crm.service.IMenuService;
import cn.demo.crm.shiro.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/util")
public class UtilController {

	@Autowired
	private IDepartmentService department;

	@Autowired
	private IMenuService menuService;

	@RequestMapping("/departmentList")
	@ResponseBody
	public List<Department> list(){
		return department.selectAll();
	}

	//根据用户查询菜单
	@RequestMapping("/findMenu")
	@ResponseBody
	public List<Menu> findMenu(){
		Employee user = UserContext.getUser();
		return menuService.findByLoginUser(user.getId());
	}
}
