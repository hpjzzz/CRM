package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface EmployeeMapper extends BaseMapper<Employee>{
	List<Employee> search(@Param("con") Conditions con, @Param("employee") Employee employee);
	//根据用户名查询用户
	Employee selectByUsername(String username);
	//找到用户的权限
	Set<String> findSnByEmp(long id);
}