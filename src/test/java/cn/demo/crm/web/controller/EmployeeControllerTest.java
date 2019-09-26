package cn.demo.crm.web.controller;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Menu;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.domain.Role;
import cn.demo.crm.domain.RolePermission;
import cn.demo.crm.mapper.BaseMapper;
import cn.demo.crm.mapper.EmployeeMapper;
import cn.demo.crm.mapper.MenuMapper;
import cn.demo.crm.mapper.RoleMapper;
import cn.demo.crm.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:F:\\java res\\CRM\\src\\test\\resources\\applicationContext.xml")
public class EmployeeControllerTest {

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private MenuMapper menuMapper;

	@Test
	public void delete() {
		try {
			int i = employeeMapper.deleteByPrimaryKey(275L);
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void find() {
		//准备父菜单
		List<Menu> parentMenu = new ArrayList<>();
		//查询所有子菜单
		List<Menu> childMenus = menuMapper.findByLoginUser(1L);
		System.out.println(childMenus.toString());
		//遍历子菜单(去重)
//		for (Menu childMenu : childMenus) {
//			Menu parent = childMenu.getParent();
//			//判断父集合里面有父菜单 不需要添加
//			if (!parentMenu.contains(parent)) {
//
//				parentMenu.add(parent);
//			}
//			//再把子菜单放进父菜单里面
//			parent.getChildren().add(childMenu);
//		}
//		System.out.println(parentMenu);
	}


}