package cn.jiang.service;

import cn.jiang.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll(int page, int pageSize) throws Exception;
}
