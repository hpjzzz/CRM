package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Role;

public interface IRoleService extends IBaseService<Role> {
	//带搜索的分页查询
	PageList<Role> search(Conditions con, Role role);
}
