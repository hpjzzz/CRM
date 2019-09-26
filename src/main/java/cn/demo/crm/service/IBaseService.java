package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;

import java.util.List;

public interface IBaseService<T> {
	int deleteByPrimaryKey(Long id);

	int insert(T t);

	T selectByPrimaryKey(Long id);

	List<T> selectAll();

	int updateByPrimaryKey(T t);
	//展示数据的分页查询
	PageList<T> selectByPage(Conditions con);
	//查询总数
	int findCount();
	//检查用户名是否存在
	boolean checkUserName(String username);
}
