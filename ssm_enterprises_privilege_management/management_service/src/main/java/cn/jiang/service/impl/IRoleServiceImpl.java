package cn.jiang.service.impl;

import cn.jiang.dao.IRoleDao;
import cn.jiang.domain.Role;
import cn.jiang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色业务处理
 */

@Service
@Transactional
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    IRoleDao roleDao;

    /**
     * 查询所有
     *
     * @return 所有角色列表
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     *
     * @param role 所需添加的角色对象
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 通过User的id查找所有的角色列表
     *
     * @param id 角色id
     * @return 角色列表
     */
    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
        return roleDao.findUserByIdAndAllRole(id);
    }
}
