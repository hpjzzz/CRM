package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.mapper.BaseMapper;
import cn.demo.crm.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class BaseServiceImpl<T> implements IBaseService<T> {

	@Autowired
	private BaseMapper<T> baseMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T t) {
		int i = baseMapper.insert(t);
		return i;
	}

	@Override
	public T selectByPrimaryKey(Long id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> selectAll() {
		return baseMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(T t) {
		int i = baseMapper.updateByPrimaryKey(t);
		return i;
	}

	@Override
	public PageList<T> selectByPage(Conditions con) {
		Integer total = baseMapper.findCount();
		List<T> list = baseMapper.selectByPage(con);
		PageList<T> pageList = new PageList<>(total, list);
		return pageList;
	}

	@Override
	public int findCount() {
		return baseMapper.findCount();
	}

	@Override
	public boolean checkUserName(String username) {
		T t = baseMapper.checkUserName(username);
		//有值返回false
		return t == null;
	}
}
