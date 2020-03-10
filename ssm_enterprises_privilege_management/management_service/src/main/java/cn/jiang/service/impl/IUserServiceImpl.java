package cn.jiang.service.impl;

import cn.jiang.dao.IUserDao;
import cn.jiang.domain.Role;
import cn.jiang.domain.UserInfo;
import cn.jiang.service.IUserService;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务处理
 */
@Service("userService")
@Transactional
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 用户信息列表
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return userDao.findAll();
    }

    /**
     * 用户信息保存
     *
     * @param user 需要保存的用户对象
     */
    @Override
    public void save(UserInfo user) {
        String encodePassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodePassword);
        System.out.println(encodePassword);
        userDao.save(user);
    }

    /**
     * 查找用户
     *
     * @param id 需要查找的用户id
     * @return 用户信息
     */
    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findById(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    /**
     * 根据用户id查询用户信息（排除掉所不具备的角色）
     *
     * @param id 用户id
     * @return 用户信息（包含的角色列表是用户不具备的）
     */
    @Override
    public UserInfo findByIdAndRoleNotIn(String id) {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByIdAndRoleNotIn(id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    /**
     * 为用户添加角色
     *
     * @param uid  需要添加角色的用户id
     * @param rids 需要添加的角色id
     */
    @Override
    public void addRoleToUser(String uid, String[] rids) {
        for (String rid : rids) {
            userDao.addRoleToUser(uid, rid);
        }
    }

    /**
     * 用户登录验证（重写UserDetailsService接口的方法）
     *
     * @param username 用户名
     * @return UserDetails接口，用于spring-security框架验证
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        User user = null;
        try {
            userInfo = userDao.findByUsername(username);
            user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 1, true, true, true, getAuthorities(userInfo.getRoles()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("password wrong");
        }
        return user;
    }

    /**
     * 通过用户具备的角色列表，为用户注册具体的权限
     *
     * @param roles 用户角色列表
     * @return 用户注册得到的权限列表
     */
    private List<SimpleGrantedAuthority> getAuthorities(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {//遍历用户角色列表
            //组装权限
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }
}
