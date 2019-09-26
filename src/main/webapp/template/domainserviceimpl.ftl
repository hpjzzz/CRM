package cn.demo.crm.service.impl;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.${domain};
import cn.demo.crm.mapper.${domain}Mapper;
import cn.demo.crm.service.I${domain}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${domain}ServiceImpl extends BaseServiceImpl<${domain}> implements I${domain}Service{
	@Autowired
	private ${domain}Mapper ${domainlower}Mapper;
	@Override
	public PageList<${domain}> search(Conditions con, ${domain} ${domainlower}) {
		Integer total = ${domainlower}Mapper.findCount();
		List<${domain}> list = ${domainlower}Mapper.search(con, ${domainlower});
		PageList<${domain}> pageList = new PageList<>(total, list);
		return pageList;
	}
}
