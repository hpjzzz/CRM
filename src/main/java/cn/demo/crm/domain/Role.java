package cn.demo.crm.domain;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private Long id;
    //角色名称
    private String name;
    //角色编号
    private String sn;

    //role和permisson多对多关联 role_permission中间表
    private List<Permission> permission = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sn='" + sn + '\'' +
                ", permission=" + permission +
                '}';
    }
}