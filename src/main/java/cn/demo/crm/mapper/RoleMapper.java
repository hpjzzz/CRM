package cn.demo.crm.mapper;

import cn.demo.crm.condition.Conditions;
import cn.demo.crm.domain.Permission;
import cn.demo.crm.domain.Role;
import cn.demo.crm.domain.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
	List<Role> search(@Param("con") Conditions con, @Param("role") Role role);

	List<Permission> selectPermissionList(long id);
	//删除role_permission的相关数据
	int deleteMiddle(long id);

	int insertMiddle(List<RolePermission> rolePermission);
}
