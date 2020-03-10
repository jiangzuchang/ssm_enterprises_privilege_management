package cn.jiang.service;


import cn.jiang.domain.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAll(int page,int pageSize) throws Exception;

    Orders findById(String id);
}
