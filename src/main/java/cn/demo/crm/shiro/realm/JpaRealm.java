package cn.demo.crm.shiro.realm;


import cn.demo.crm.domain.Employee;
import cn.demo.crm.mapper.EmployeeMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * AuthenticatingRealm只支持登陆验证
 * AuthorizingRealm 还支持授权
 */
public class JpaRealm extends AuthorizingRealm {

	@Autowired
	private EmployeeMapper employeeMapper;

	//完成登陆验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
		//得到认证令牌
		UsernamePasswordToken token = (UsernamePasswordToken) token1;

		//完成认证 得到身份(用户名)
		String username = token.getUsername();
		//数据库查询用户是否存在
		Employee employee = employeeMapper.selectByUsername(username);
		//根据用户查询数据库
		if (employee == null) {
			return null;
		}

		String pwd = employee.getPassword();
		ByteSource salt = ByteSource.Util.bytes("itsource");

		//有用户名和密码就可以完成一个认证
		//public SimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
		return new SimpleAuthenticationInfo(employee, pwd, salt, getName());
	}
	/**
	 * 授权方法
	 * 给登陆用户授权权限
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

		//必然是登陆成功了，所以可以直接获取当前主体
//		String username = (String) principal.getPrimaryPrincipal();
		Employee loginuser = (Employee) principal.getPrimaryPrincipal();
		System.out.println("登陆之后的主体用户"+loginuser);
		//根据登陆用户获取从数据库查询权限
		Set<String> permissions = new HashSet<>();
		permissions = employeeMapper.findSnByEmp(loginuser.getId());

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}
}
