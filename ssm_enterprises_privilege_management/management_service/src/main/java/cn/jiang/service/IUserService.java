package cn.jiang.service;

import cn.jiang.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page, int pageSize) throws Exception;

    void save(UserInfo user);

    UserInfo findById(String id);

    UserInfo findByIdAndRoleNotIn(String id);

    void addRoleToUser(String uid, String[] rids);
}
