package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Employee;
import cn.demo.crm.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	@RequestMapping("/page")
	@ResponseBody
	public PageList<Employee> page(Conditions con, Employee employee){
		return employeeService.search(con, employee);
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<Employee> findAll(){
		return employeeService.selectAll();
	}

	//增加数据
	@RequestMapping("/save")
	@ResponseBody
	public Map save(Employee employee){
		HashMap resultMap = new HashMap();
		try {
			int i = employeeService.insert(employee);
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
	public Map update(@ModelAttribute("editEmployee") Employee employee){
		HashMap resultMap = new HashMap();
		try {
			int i = employeeService.updateByPrimaryKey(employee);
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
			int i = employeeService.deleteByPrimaryKey(id);
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

	//检查用户名
	@RequestMapping("/checkUserName")
	@ResponseBody
	public boolean checkUserName(String username, Long id){
		//用于修改名字验证自己和重复的问题
		if (id != null){
			Employee employee = employeeService.selectByPrimaryKey(id);
			if (employee.getUsername().equals(username)){
				return true;
			}
		}
		boolean b = employeeService.checkUserName(username);
		//如果重复返回false
		return b;
	}

	@ModelAttribute("editEmployee")
	public Employee getEmpolyee(Long id, String cmd){
		if ("update".equals(cmd)){
			Employee employee = employeeService.selectByPrimaryKey(id);
			return employee;
		}
		return null;
	}
}
