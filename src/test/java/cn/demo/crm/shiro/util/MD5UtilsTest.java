package cn.demo.crm.shiro.util;

import cn.demo.crm.domain.Employee;
import cn.demo.crm.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MD5UtilsTest {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Test
	public void createMD5Str() {
		List<Employee> employees = employeeMapper.selectAll();
		for (Employee e : employees) {
			String password = MD5Utils.createMD5Str(e.getPassword());
			e.setPassword(password);
			employeeMapper.updateByPrimaryKey(e);
		}
	}
}