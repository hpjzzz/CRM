package cn.demo.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class JumpController {

	@RequestMapping("/{method}")
	public String main(@PathVariable("method")String method, HttpServletResponse resp){
		resp.setHeader("Cache-Control", "max-age=5");
		return method;
	}
	@RequestMapping("/{menu}/index")
	public String menu(@PathVariable("menu")String menu, HttpServletResponse resp){
		resp.setHeader("Cache-Control", "max-age=5");
		return menu+"/"+menu;
	}
}
