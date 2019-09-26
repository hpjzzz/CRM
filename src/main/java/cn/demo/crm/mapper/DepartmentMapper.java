package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Department;
import cn.demo.crm.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department>{
	List<Department> search(@Param("con") Conditions con, @Param("department") Department department);
}