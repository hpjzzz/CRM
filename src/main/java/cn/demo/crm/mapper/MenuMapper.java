package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
	List<Menu> search(@Param("con") Conditions con, @Param("menu") Menu menu);

	//根据用户查询菜单(只有子菜单)
	List<Menu> findByLoginUser(long empId);

//	List<Menu> findChildren(long id);
}
