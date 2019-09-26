package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Menu;
import cn.demo.crm.mapper.MenuMapper;
import cn.demo.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService{
	@Autowired
	private MenuMapper menuMapper;
	@Override
	public PageList<Menu> search(Conditions con, Menu menu) {
		Integer total = menuMapper.findCount();
		List<Menu> list = menuMapper.search(con, menu);
		PageList<Menu> pageList = new PageList<>(total, list);
		return pageList;
	}

	@Override
	public List<Menu> findByLoginUser(long empId) {
		List<Menu> byLoginUser = menuMapper.findByLoginUser(empId);
		System.out.println(byLoginUser);
		return byLoginUser;
	}
}
