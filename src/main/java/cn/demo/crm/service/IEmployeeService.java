package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Employee;

import java.util.Set;

public interface IEmployeeService extends IBaseService<Employee> {
	//带搜索的分页查询
	PageList<Employee> search(Conditions con, Employee employee);
	//根据用户查权限
	Set<String> findSnByEmp(long id);
}
