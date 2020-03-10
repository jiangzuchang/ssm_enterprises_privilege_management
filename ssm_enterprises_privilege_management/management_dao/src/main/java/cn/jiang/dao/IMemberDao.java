package cn.jiang.dao;


import cn.jiang.domain.Member;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 成员操作的类
 */
@Repository
public interface IMemberDao {
    /**
     * 通过id值来查询成员实体对象
     * @param id 成员id
     * @return 成员对象
     */
    @Select("select * from member where id = #{id}")
    Member findById(String id);

}
