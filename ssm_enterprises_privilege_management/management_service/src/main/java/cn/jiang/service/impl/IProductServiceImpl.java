package cn.jiang.service.impl;

import cn.jiang.dao.IProductDao;
import cn.jiang.domain.Product;
import cn.jiang.service.IProductService;

import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 产品业务处理
 */
@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 产品列表
     * @throws Exception
     */
    @Override
    public List<Product> findAll(int page, int pageSize) throws Exception {
        PageHelper.startPage(page, pageSize);
        return productDao.findAll();
    }

    /**
     * 保存产品
     *
     * @param product 需要保存的产品对象
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
