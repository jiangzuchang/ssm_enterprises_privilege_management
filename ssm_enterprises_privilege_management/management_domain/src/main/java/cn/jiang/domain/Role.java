package cn.jiang.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
public class Role implements Serializable {
    private String id;//主键
    private String roleName;//角色名
    private String roleDesc;//角色描述
    private List<Permission> permissions;//角色关联的权限列表
    private List<UserInfo> users;//角色关联的用户列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

}
