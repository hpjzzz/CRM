package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Department;

public interface IDepartmentService extends IBaseService<Department> {
	//带搜索的分页查询
	PageList<Department> search(Conditions con, Department department);
}
