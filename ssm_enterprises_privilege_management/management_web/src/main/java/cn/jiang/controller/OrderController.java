package cn.jiang.controller;

import cn.jiang.domain.Orders;
import cn.jiang.service.IOrderService;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单操作（控制层）
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    /**
     * 查询所有
     * @param page 页码
     * @param pageSize 单页大小
     * @return 模型视图对象
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) throws Exception {
        List<Orders> orders = orderService.findAll(page, pageSize);
        PageInfo<Orders> pageInfo = new PageInfo<>(orders);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * 通过订单id查询traveller
     * @param id 订单id
     * @return 模型视图对象
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        Orders orders = orderService.findById(id);
        try {
            System.out.println(orders.getTravellers().get(0));
        } catch (Exception e) {
            System.out.println("traveller查询结果为零");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
