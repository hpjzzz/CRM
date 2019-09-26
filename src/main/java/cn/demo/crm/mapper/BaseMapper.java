package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;

import java.util.List;

public interface BaseMapper<T> {
	int deleteByPrimaryKey(Long id);

	int insert(T t);

	T selectByPrimaryKey(Long id);

	List<T> selectAll();

	int updateByPrimaryKey(T t);
	//分页查询
	List<T> selectByPage(Conditions con);
	//查询数据总数
	int findCount();
	//检查用户名
	T checkUserName(String username);
}
