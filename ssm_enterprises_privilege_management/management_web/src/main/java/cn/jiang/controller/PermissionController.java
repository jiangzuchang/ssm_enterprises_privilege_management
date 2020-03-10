package cn.jiang.controller;


import cn.jiang.domain.Permission;
import cn.jiang.service.IPermissionService;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限操作（控制层）
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    /**
     * 查询所有
     * @param page 页码
     * @param pageSize 单页大小
     * @return 模型视图对象
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {

        List<Permission> permissions = permissionService.findAll(page, pageSize);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissions);
        ModelAndView mv = new ModelAndView();
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("permission-list");

        return mv;
    }

    /**
     * 添加权限信息
     * @param permission 需要添加的权限对象
     * @return 重定向，返回权限列表
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) {

        permissionService.save(permission);
        return "redirect:findAll.do";
    }

}
