package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu> {
	//带搜索的分页查询
	PageList<Menu> search(Conditions con, Menu menu);
	//根据用户查询菜单(只有子菜单)
	List<Menu> findByLoginUser(long empId);
}
