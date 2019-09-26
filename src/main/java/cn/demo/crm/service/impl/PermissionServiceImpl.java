package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.mapper.PermissionMapper;
import cn.demo.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements IPermissionService{
	@Autowired
	private PermissionMapper PermissionMapper;
	@Override
	public PageList<Permission> search(Conditions con, Permission Permission) {
		Integer total = PermissionMapper.findCount();
		List<Permission> list = PermissionMapper.search(con, Permission);
		PageList<Permission> pageList = new PageList<>(total, list);
		return pageList;
	}
}
