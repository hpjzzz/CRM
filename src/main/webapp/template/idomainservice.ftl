package cn.demo.crm.service;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.condition.PageList;
import cn.demo.crm.domain.${domain};

public interface I${domain}Service extends IBaseService<${domain}> {
	//带搜索的分页查询
	PageList<${domain}> search(Conditions con, ${domain} ${domainlower});
}
