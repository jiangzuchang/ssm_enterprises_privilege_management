package cn.jiang.dao;

import cn.jiang.domain.Role;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色操作类
 */
@Repository
public interface IRoleDao {

    /**
     * 查询所有
     * @return 所有角色列表
     */
    @Select("select * from role")
    @Results(id = "roleResultMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "cn.jiang.dao.IPermissionDao.findById", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "id", many = @Many(select = "cn.jiang.dao.IUserDao.findByRoleId", fetchType = FetchType.LAZY))
    })
    List<Role> findAll();

    /**
     * 通过User对象的id值查询角色列表
     * @param id User对象的id值
     * @return 角色列表
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{id})")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roleName", property = "roleName"),
            @Result(column = "roleDesc", property = "roleDesc"),
            @Result(property = "permissions", column = "id", many = @Many(select = "cn.jiang.dao.IPermissionDao.findById", fetchType = FetchType.LAZY))
    })
    List<Role> findById(String id);

    /**
     * 保存角色
     * @param role 需要保存的角色对象
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 通过User对象的Id查询该对象所不具有的角色列表
     * @param id User对象的id
     * @return 角色列表
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id} )")
    List<Role> findUserByIdAndAllRole(String id);
}
