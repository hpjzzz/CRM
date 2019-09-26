package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Department;
import cn.demo.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;
	@RequestMapping("/page")
	@ResponseBody
	public PageList<Department> page(Conditions con, Department department){
		return departmentService.search(con, department);
	}

	@RequestMapping("/findAll")
	@ResponseBody
	public List<Department> findAll(){
		return departmentService.selectAll();
	}

	//增加数据
	@RequestMapping("/save")
	@ResponseBody
	public Map save(Department department){
		HashMap resultMap = new HashMap();
		try {
			int i = departmentService.insert(department);
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
	public Map update(@ModelAttribute("editDepartment") Department department){
		HashMap resultMap = new HashMap();
		try {
			int i = departmentService.updateByPrimaryKey(department);
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
			int i = departmentService.deleteByPrimaryKey(id);
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

	@ModelAttribute("editDepartment")
	public Department getEmpolyee(Long id, String cmd){
		if ("update".equals(cmd)){
			Department department = departmentService.selectByPrimaryKey(id);
			return department;
		}
		return null;
	}
}
