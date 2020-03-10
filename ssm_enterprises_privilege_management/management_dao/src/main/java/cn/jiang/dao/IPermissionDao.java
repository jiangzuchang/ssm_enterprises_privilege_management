package cn.jiang.dao;

import cn.jiang.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权限操作的类
 */
@Repository
public interface IPermissionDao {

    /**
     * 查询所有权限
     * @return 权限列表
     */
    @Select("select * from permission")
    @Results(id = "permissionResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url"),
            @Result(property = "roles", column = "id", many = @Many(select = "cn.jiang.dao.IRoleDao.findById", fetchType = FetchType.LAZY))
    })
    List<Permission> findAll();

    /**
     * 根据角色的id值查询权限列表
     * @param id 角色id值
     * @return 权限对象List集合
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findById(String id);

    /**
     * 保存权限
     * @param permission 要保存的权限对象
     */
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
