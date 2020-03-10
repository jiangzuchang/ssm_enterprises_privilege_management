package cn.jiang.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息实体类
 */
public class UserInfo implements Serializable {
    private String id;//用户id，主键
    private String username;//用户姓名
    private String email;//邮件地址
    private String password;//用户登录密码
    private String phoneNum;//用户手机号码
    private Integer status;//账户状态：0未开启、1开启
    private String statusStr;
    private List<Role> roles;//用户关联的角色列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status != null) {
            if (status == 0) {
                return "未开启";
            } else if (status == 1) {
                return "开启";
            }
        }
        return null;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
