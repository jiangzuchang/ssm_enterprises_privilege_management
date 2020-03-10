package cn.jiang.service.impl;

import cn.jiang.dao.IPermissionDao;
import cn.jiang.domain.Permission;
import cn.jiang.service.IPermissionService;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限业务处理
 */
@Service
@Transactional
public class IPermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 权限列表
     */
    @Override
    public List<Permission> findAll(int page, int pageSize) {

        PageHelper.startPage(page, pageSize);
        return permissionDao.findAll();
    }

    /**
     * 添加权限
     *
     * @param permission 需要添加的权限对象
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }
}
