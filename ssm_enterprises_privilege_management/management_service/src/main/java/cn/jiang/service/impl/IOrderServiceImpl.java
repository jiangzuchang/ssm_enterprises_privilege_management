package cn.jiang.service.impl;

import cn.jiang.dao.IOrderDao;
import cn.jiang.domain.Orders;
import cn.jiang.service.IOrderService;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单业务处理
 */
@Service
@Transactional
public class IOrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao orderDao;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 所有订单列表
     * @throws Exception
     */
    @Override
    public List<Orders> findAll(int page, int pageSize) throws Exception {
        //分页插件：pageHelper
        PageHelper.startPage(page, pageSize);
        return orderDao.findAll();
    }

    /**
     * 通过id查询订单
     *
     * @param id 所需查询的订单id
     * @return 需要查询的订单结果
     */
    @Override
    public Orders findById(String id) {
        return orderDao.findById(id);
    }
}
