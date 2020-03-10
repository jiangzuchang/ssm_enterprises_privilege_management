package cn.jiang.service;


import cn.jiang.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(int page, int pageSize);

    void save(Permission permission);
}
