package cn.demo.crm.shiro.realm;

import cn.demo.crm.domain.Permission;
import cn.demo.crm.mapper.PermissionMapper;
import cn.demo.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * ShiroFilterFactoryBean.class
 * private Map<String, String> mp = new LinkedHashMap();
 */
public class FilterChainDefinitionMapBuilder {

	@Autowired
	private IPermissionService permissionService;


	//写一个方法返回一个map 需要的是LinkedHashMap
	public Map creatFMap(){
		Map mp = new LinkedHashMap();
		//以后操作数据 查询所有权限(交给shiro管理)
		//不需要权限访问的内容
		//匿名访问
		mp.put("/login","anon");
		//静态资源
		mp.put("*.js","anon");
		mp.put("*.css","anon");
		mp.put("/css/**","anon");
		mp.put("/js/**","anon");
		mp.put("/easyui/**","anon");
		mp.put("/jquery/**","anon");
		mp.put("/images/**","anon");
		List<Permission> permissions = permissionService.selectAll();
		for (Permission p : permissions) {
			mp.put(p.getUrl(), "myPerms["+p.getSn()+"]");
		}
		//需要权限访问
		mp.put("/s/permission.jsp", "perms[user:*]");
		mp.put("/**", "authc");
		return mp;
	}
}
