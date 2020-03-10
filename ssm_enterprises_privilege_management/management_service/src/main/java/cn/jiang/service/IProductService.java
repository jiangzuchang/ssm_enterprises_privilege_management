package cn.jiang.service;

import cn.jiang.domain.Product;

import java.util.List;


public interface IProductService {

    List<Product> findAll(int page,int pageSize) throws Exception;

    void save(Product product);
}
