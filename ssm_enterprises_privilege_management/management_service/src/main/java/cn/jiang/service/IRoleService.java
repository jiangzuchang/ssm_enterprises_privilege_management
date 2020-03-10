package cn.jiang.service;

import cn.jiang.domain.Role;
import java.util.List;

public interface IRoleService {

    List<Role> findAll() throws Exception;

    void save(Role role);

    List<Role> findUserByIdAndAllRole(String id);
}
