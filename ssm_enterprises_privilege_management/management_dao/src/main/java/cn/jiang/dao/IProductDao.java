package cn.jiang.dao;

import cn.jiang.domain.Product;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品操作类
 */
@Repository
public interface IProductDao {
    /**
     * 查询所有
     * @return 订单列表
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 保存订单
     * @param product 要保存的订单对象
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 通过id查询订单
     * @param id 要查询的订单id
     * @return 订单对象
     */
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
