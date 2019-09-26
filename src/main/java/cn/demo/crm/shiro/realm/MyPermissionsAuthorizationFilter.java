package cn.demo.crm.shiro.realm;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义的权限过滤器，告诉shiro使用自定义的过滤器--xml配置
 * 如果是ajax请求，就返回json
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = this.getSubject(request, response);
		if (subject.getPrincipal() == null) {
			this.saveRequestAndRedirectToLogin(request, response);
		} else {

			//有登录之后，判断请求如果是ajax，就返回json格式
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;

			//设置请求头
			String xmlHttpRequest = req.getHeader("X-Requested-With");
			if (xmlHttpRequest != null && "XMLHttpRequest".equals(xmlHttpRequest)) {
				resp.setContentType("text/json;charset=UTF-8");
				resp.getWriter().print("{\"success\":false,\"msg\":\"没有权限\"}");
			} else {

				String unauthorizedUrl = this.getUnauthorizedUrl();
				if (StringUtils.hasText(unauthorizedUrl)) {
					WebUtils.issueRedirect(request, response, unauthorizedUrl);
				} else {
					WebUtils.toHttp(response).sendError(401);
				}
			}
		}

		return false;
	}
}
