package cn.demo.crm.shiro.util;

import cn.demo.crm.domain.Employee;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * 拿到当前的用户
 */
public class UserContext {

	public static final String LOGINUSER = "loginuser";
	//设置当前用户的方法
	public static void setUser(Employee employee){
		//拿到shiro里面的session  支持分布式缓存
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(LOGINUSER, employee);
	}

	//拿到当前用户的方法
	public static Employee getUser(){
		Session session = SecurityUtils.getSubject().getSession();
		return (Employee) session.getAttribute(LOGINUSER);
	}
}
