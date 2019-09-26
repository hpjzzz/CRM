package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Permission;

public interface IPermissionService extends IBaseService<Permission> {
	//带搜索的分页查询
	PageList<Permission> search(Conditions con, Permission permission);
}
