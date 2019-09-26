package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
	List<Permission> search(@Param("con") Conditions con, @Param("permission") Permission permission);
}
