package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.domain.Role;
import cn.demo.crm.domain.RolePermission;
import cn.demo.crm.mapper.RoleMapper;
import cn.demo.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements IRoleService{
	@Autowired
	private RoleMapper RoleMapper;
	@Override
	public PageList<Role> search(Conditions con, Role role) {
		Integer total = RoleMapper.findCount();
		List<Role> list = RoleMapper.search(con, role);
		PageList<Role> pageList = new PageList<>(total, list);
		return pageList;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		int i = RoleMapper.deleteByPrimaryKey(id);
		RoleMapper.deleteMiddle(id);
		return i;
	}

	@Override
	public int insert(Role role) {
		int i = RoleMapper.insert(role);
		List<Permission> permission = role.getPermission();
		RolePermission rolePermission = null;
		//设置中间表中左边的id
		Long lId = role.getId();
		//设置中间表中右边的id
		List<RolePermission> middleList = new ArrayList<RolePermission>();
		//组装中间表的集合
		for (Permission p : permission) {
			rolePermission = new RolePermission();
			rolePermission.setlId(lId);
			rolePermission.setrId(p.getId());
			middleList.add(rolePermission);
		}

		System.out.println(middleList);
		RoleMapper.insertMiddle(middleList);
		return i;
	}

	@Override
	public int updateByPrimaryKey(Role role) {
		int i = RoleMapper.updateByPrimaryKey(role);
		RoleMapper.deleteMiddle(role.getId());

		List<Permission> permission = role.getPermission();
		//设置中间表中左边的id
		Long lId = role.getId();
		//设置中间表中右边的id
		List<RolePermission> middleList = new ArrayList<RolePermission>();
		//组装中间表的集合
		RolePermission rolePermission = null;
		for (Permission p : permission) {
			rolePermission = new RolePermission();
			rolePermission.setlId(lId);
			rolePermission.setrId(p.getId());
			middleList.add(rolePermission);
		}
		RoleMapper.insertMiddle(middleList);

		return i;
	}
}
