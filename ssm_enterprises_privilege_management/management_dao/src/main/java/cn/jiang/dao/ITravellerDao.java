package cn.jiang.dao;


import cn.jiang.domain.Traveller;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 旅游成员操作类
 */
@Repository
public interface ITravellerDao {
    /**
     * 根据订单id查询旅游成员列表
     * @param id 订单id
     * @return 旅游成员列表
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{id})")
    List<Traveller> findById(String id) throws Exception;

}
