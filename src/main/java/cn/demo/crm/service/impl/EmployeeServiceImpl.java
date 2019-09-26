package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Employee;
import cn.demo.crm.mapper.EmployeeMapper;
import cn.demo.crm.service.IEmployeeService;
import cn.demo.crm.shiro.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService{
	@Autowired
	private EmployeeMapper employeeMapper;
	@Override
	public PageList<Employee> search(Conditions con, Employee employee) {
		Integer total = employeeMapper.findCount();
		List<Employee> list = employeeMapper.search(con, employee);
		PageList<Employee> pageList = new PageList<>(total, list);
		return pageList;
	}

	@Override
	public Set<String> findSnByEmp(long id) {
		return employeeMapper.findSnByEmp(id);
	}

	@Override
	public int insert(Employee employee) {
		String password = MD5Utils.createMD5Str(employee.getPassword());
		employee.setPassword(password);
		return employeeMapper.insert(employee);
	}
}
