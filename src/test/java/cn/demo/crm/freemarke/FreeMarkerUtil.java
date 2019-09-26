package cn.demo.crm.freemarke;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class FreeMarkerUtil {
	String fileName = "menu";
	String javaName = "Menu";
	@Test
	public void creatJs() throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		//设置读取模板的文件夹路径
		File f = new File("F:\\java res\\CRM\\src\\main\\webapp\\template");
		cfg.setDirectoryForTemplateLoading(f);

		//设置编码
		cfg.setDefaultEncoding("UTF-8");

		//获取模板对象
		Template template = cfg.getTemplate("domainjs.ftl");

		HashMap<String, Object> map = new HashMap<>();
		map.put("domain", fileName);
		//生成页面
		PrintWriter pw = new PrintWriter(new File("F:\\java res\\CRM\\src\\main\\webapp\\js", fileName+".js"),"UTF-8");
		template.process(map,pw);
		pw.close();
	}
	@Test
	public void creatJsp() throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		//设置读取模板的文件夹路径
		File f = new File("F:\\java res\\CRM\\src\\main\\webapp\\template");
		cfg.setDirectoryForTemplateLoading(f);

		//设置编码
		cfg.setDefaultEncoding("UTF-8");

		//获取模板对象
		Template template = cfg.getTemplate("domainjsp.ftl");

		HashMap<String, Object> map = new HashMap<>();
		map.put("domain", fileName);
		//生成页面
		PrintWriter pw = new PrintWriter(new File("F:\\java res\\CRM\\src\\main\\webapp\\WEB-INF\\views\\"+fileName, fileName+".jsp"),"UTF-8");
		template.process(map,pw);
		pw.close();
	}
	@Test
	public void creatService() throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		//设置读取模板的文件夹路径
		File f = new File("F:\\java res\\CRM\\src\\main\\webapp\\template");
		cfg.setDirectoryForTemplateLoading(f);

		//设置编码
		cfg.setDefaultEncoding("UTF-8");

		//获取模板对象
		Template template = cfg.getTemplate("idomainservice.ftl");

		HashMap<String, Object> map = new HashMap<>();
		map.put("domain", javaName);
		map.put("domainlower", fileName);
		//生成页面
		PrintWriter pw = new PrintWriter(new File("F:\\java res\\CRM\\src\\main\\java\\cn\\demo\\crm\\service", "I"+javaName+"Service.java"),"UTF-8");
		template.process(map,pw);
		pw.close();
	}
	@Test
	public void creatImpl() throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		//设置读取模板的文件夹路径
		File f = new File("F:\\java res\\CRM\\src\\main\\webapp\\template");
		cfg.setDirectoryForTemplateLoading(f);

		//设置编码
		cfg.setDefaultEncoding("UTF-8");

		//获取模板对象
		Template template = cfg.getTemplate("domainserviceimpl.ftl");

		HashMap<String, Object> map = new HashMap<>();
		map.put("domain", javaName);
		map.put("domainlower", fileName);
		//生成页面
		PrintWriter pw = new PrintWriter(new File("F:\\java res\\CRM\\src\\main\\java\\cn\\demo\\crm\\service\\impl", javaName+"ServiceImpl.java"),"UTF-8");
		template.process(map,pw);
		pw.close();
	}
	@Test
	public void creatControl() throws IOException, TemplateException {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		//设置读取模板的文件夹路径
		File f = new File("F:\\java res\\CRM\\src\\main\\webapp\\template");
		cfg.setDirectoryForTemplateLoading(f);

		//设置编码
		cfg.setDefaultEncoding("UTF-8");

		//获取模板对象
		Template template = cfg.getTemplate("domaincontroller.ftl");

		HashMap<String, Object> map = new HashMap<>();
		map.put("domain", javaName);
		map.put("domainlower", fileName);
		//生成页面
		PrintWriter pw = new PrintWriter(new File("F:\\java res\\CRM\\src\\main\\java\\cn\\demo\\crm\\web\\controller", javaName+"Controller.java"),"UTF-8");
		template.process(map,pw);
		pw.close();
	}
}
