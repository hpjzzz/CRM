package cn.demo.crm.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

        // The easiest way to create a Shiro SecurityManager with configured
        // realms, users, roles and permissions is to use the simple INI config.
        // We'll do that by using a factory that can ingest a .ini file and
        // return a SecurityManager instance:

        // Use the shiro.ini file at the root of the classpath
        // (file: and url: prefixes load from files and urls respectively):
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();

        // for this simple example quickstart, make the SecurityManager
        // accessible as a JVM singleton.  Most applications wouldn't do this
        // and instead rely on their container configuration or web.xml for
        // webapps.  That is outside the scope of this simple quickstart, so
        // we'll just do the bare minimum so you can continue to get a feel
        // for things.
        SecurityUtils.setSecurityManager(securityManager);

        // Now that a simple Shiro environment is set up, let's see what you can do:

        //得到当前的主题
        Subject currentUser = SecurityUtils.getSubject();

        //设置会话 key value
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        //取出key value
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            System.out.println("该会话里面有someKey用户");
        }

        // 判断当前用户是否认证过
        if (!currentUser.isAuthenticated()) {
            //封装一个用户名和密码的token（令牌）
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            //记住令牌
            token.setRememberMe(true);
            try {
            //进行登陆认证
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("用户不存在" + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("该用户" + token.getPrincipal() + " 密码错误");
            } catch (LockedAccountException lae) {
                System.out.println("该用户" + token.getPrincipal() + "被锁定" +
                        "请联系管理员以解锁");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //其他认证异常
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        System.out.println("用户[" + currentUser.getPrincipal() + "] 登陆成功");

        //测试一个角色
        if (currentUser.hasRole("schwartz")) {
            System.out.println("用户有该角色");
        } else {
            System.out.println("用户没有该角色");
        }

        //测试用户是否有该权限
        if (currentUser.isPermitted("lightsaber:wield")) {
            System.out.println("有lightsaber:wield权限");
        } else {
            System.out.println("没有lightsaber:wield权限");
        }

        //all done - log out!
        currentUser.logout();

        System.exit(0);
    }
}
