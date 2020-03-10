package cn.jiang.dao;

import cn.jiang.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户操作类
 */
@Repository
public interface IUserDao {
    /**
     * 查询所有
     * @return 用户信息列表
     * @throws Exception
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     * @param user 需要保存的用户对象
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo user);

    /**
     * 通过用户名查询用户信息
     * @param username 需要查询的用户名
     * @return 对应的用户信息
     */
    @Select("select * from users where username=#{username}")
    @Results(id = "userResultMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "cn.jiang.dao.IRoleDao.findById", fetchType = FetchType.LAZY))

    })
    UserInfo findByUsername(String username);

    /**
     * 通过用户id查询用户信息
     * @param id 用户id
     * @return 对应的用户实体
     */
    @Select("select * from users where id = #{id}")
    @ResultMap("userResultMap")
    UserInfo findById(String id);

    /**
     * 通过用户id查询用户
     * 是利用cn.jiang.dao.IRoleDao.findUserByIdAndAllRole排除掉用户不具有的角色
     * @param id 用户id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "cn.jiang.dao.IRoleDao.findUserByIdAndAllRole", fetchType = FetchType.LAZY))

    })
    UserInfo findByIdAndRoleNotIn(String id);

    /**
     * 通过角色id查询具备该角色的用户信息
     * @param id 角色id
     * @return 用户信息
     */
    @Select("select * from users where id in (select userId from users_role where roleId = #{id})")
    @ResultMap("userResultMap")
    UserInfo findByRoleId(String id);

    /**
     * 为某一用户添加角色
     * @param uid 需要添加角色的用户id
     * @param rid 需要添加的角色id
     */
    @Insert("insert into users_role(userId,roleId) values(#{uid},#{rid})")
    void addRoleToUser(@Param("uid") String uid, @Param("rid") String rid);
}
