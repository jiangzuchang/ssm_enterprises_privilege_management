package cn.jiang.dao;


import cn.jiang.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单操作的类
 */
@Repository
public interface IOrderDao {
    /**
     * 查询所有订单
     * @return 所有订单的List集合
     */
    @Select("select * from orders")
    @ResultMap("resultMap")
    List<Orders> findAll();

    /**
     * 通过订单的id值查询指定订单
     * @param id 订单的id值
     * @return 指定的订单实体对象
     */
    @Select("select * from orders where id = #{id}")
    @Results(id = "resultMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product", one = @One(select = "cn.jiang.dao.IProductDao.findById", fetchType = FetchType.LAZY)),
            @Result(column = "memberId", property = "member", one = @One(select = "cn.jiang.dao.IMemberDao.findById", fetchType = FetchType.LAZY)),
            @Result(column = "id", property = "travellers", many = @Many(select = "cn.jiang.dao.ITravellerDao.findById", fetchType = FetchType.LAZY)),
    })
    Orders findById(String id);
}
