package cn.demo.crm.shiro.realm;


import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * AuthenticatingRealm只支持登陆验证
 */
public class JpaRealm1 extends AuthenticatingRealm {

	//完成登陆验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token1) throws AuthenticationException {
		//得到认证令牌
		UsernamePasswordToken token = (UsernamePasswordToken) token1;

		//完成认证 得到身份(用户名)
		String username = token.getUsername();

		//根据用户查询数据库
		String pwd = getByName(username);
		if (pwd==null){
			return null;
		}
		ByteSource salt = ByteSource.Util.bytes("itsource");

		//有用户名和密码就可以完成一个认证
		//public SimpleAuthenticationInfo(Object principal, Object credentials, String realmName) {
		return new SimpleAuthenticationInfo(username, pwd, salt, getName());
	}

	//模拟数据库
	public String getByName(String username) {
		if ("admin".equals(username)) {
			return "831d092d59f6e305ebcfa77e05135eac";//123456加密后的密码(10次)
		} else if ("wangteacher".equals(username)) {
			return "51wang";
		} else {
			return null;
		}
	}
	//测试加密
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		int hashIterations = 10;//4a95737b032e98a50c056c41f2fa9ec6 加了盐值831d092d59f6e305ebcfa77e05135eac
		//加salt
		ByteSource salt = ByteSource.Util.bytes("itsource");
		SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials,salt, hashIterations);
		System.out.println(simpleHash.toString());
	}
}
