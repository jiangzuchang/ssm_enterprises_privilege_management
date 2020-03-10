package cn.jiang.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 权限实体类
 */
public class Permission implements Serializable {
    private String id;//主键
    private String permissionName;//权限名
    private String url;//权限可访问的url
    private List<Role> roles;//权限关联的角色列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
