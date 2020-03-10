package cn.jiang.dao;

import cn.jiang.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 日志操作类
 */
@Repository
public interface ISysLogDao {

    /**
     * 查询所有
     * @return 日志列表
     * @throws Exception
     */
    @Select("select * from sysLog")
    List<SysLog> findAll()  throws Exception;

    /**
     * 日志信息保存
     * @param sysLog 要保存的日志对象
     */
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method}) ")
    void save(SysLog sysLog);
}
