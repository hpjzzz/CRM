package cn.demo.crm.web.controller;

import cn.demo.crm.domain.Employee;
import cn.demo.crm.shiro.util.JsonResult;
import cn.demo.crm.shiro.util.UserContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	//get方式进入方法
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult loginGet(String username, String password) {
		//得到操作主题
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			//认证 --封装一个令牌
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			//调用登录方法认证
			try {
				//进行登陆认证
				subject.login(token);
			} catch (UnknownAccountException e) {
				e.printStackTrace();
				return new JsonResult(false, "用户不存在");
			} catch (IncorrectCredentialsException e) {
				e.printStackTrace();
				return new JsonResult(false, "密码不正确");
			} catch (AuthenticationException ae){
				ae.printStackTrace();
				return new JsonResult(false, "其他异常请联系管理员");
			}
		}

		//登陆成功
		System.out.println(subject.getPrincipal());
		UserContext.setUser((Employee)subject.getPrincipal());
		return new JsonResult();
	}

	//登陆提交进入方法
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPost() {
		return "login";
	}

	//登出方法
	@RequestMapping("/logout")
	public String logout(){
		//登出
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}
}