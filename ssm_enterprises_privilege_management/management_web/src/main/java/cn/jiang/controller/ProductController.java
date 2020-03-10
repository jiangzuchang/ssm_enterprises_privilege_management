package cn.jiang.controller;

import cn.jiang.domain.Product;
import cn.jiang.service.IProductService;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 产品操作（控制层）
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 模型视图对象
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_USER"})
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page, pageSize);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * 添加产品
     *
     * @param product 需要添加的产品对象
     * @return 重定向，返回产品列表
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {

        productService.save(product);

        return "redirect:findAll.do";
    }
}
