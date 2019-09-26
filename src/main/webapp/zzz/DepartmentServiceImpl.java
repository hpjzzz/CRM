package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Department;
import cn.demo.crm.mapper.DepartmentMapper;
import cn.demo.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService{
	@Autowired
	private DepartmentMapper DepartmentMapper;
	@Override
	public PageList<Department> search(Conditions con, Department Department) {
		Integer total = DepartmentMapper.findCount();
		List<Department> list = DepartmentMapper.search(con, Department);
		PageList<Department> pageList = new PageList<>(total, list);
		return pageList;
	}
}
