package cn.jiang.service.impl;

import cn.jiang.dao.ISysLogDao;
import cn.jiang.domain.SysLog;
import cn.jiang.service.ISysLogService;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志业务处理
 */
@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 日志保存
     * @param sysLog 需要保存的日志对象
     */
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询所有
     * @param page 页码
     * @param pageSize 单页大小
     * @return 日志列表
     * @throws Exception
     */
    @Override
    public List<SysLog> findAll(int page, int pageSize) throws Exception{
        PageHelper.startPage(page, pageSize);
        return sysLogDao.findAll();
    }
}
