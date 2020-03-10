package cn.jiang.controller;


import cn.jiang.domain.Role;
import cn.jiang.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色操作（控制层）
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 查询所有
     *
     * @return 模型视图对象
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        List<Role> roles = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 保存角色信息
     *
     * @param role 需要保存的角色对象
     * @return 重定向，返回角色列表
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {

        roleService.save(role);

        return "redirect:findAll.do";
    }
}
