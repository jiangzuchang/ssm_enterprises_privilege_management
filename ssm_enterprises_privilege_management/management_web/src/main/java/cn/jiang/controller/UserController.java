package cn.jiang.controller;


import cn.jiang.domain.UserInfo;
import cn.jiang.service.IUserService;

import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户操作（控制层）
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 查询所有
     *
     * @param page     页码
     * @param pageSize 单页大小
     * @return 模型视图对象（用户信息列表）
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) throws Exception {

        List<UserInfo> users = userService.findAll(page, pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(users);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 用户添加
     *
     * @param user 需要添加的用户对象
     * @return 重定向，用户列表
     * @throws Exception
     */
    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception {

        userService.save(user);
        return "redirect:findAll.do";
    }

    /**
     * 通过用户id查询用户信息
     *
     * @param id 用户id
     * @return 模型视图对象（用户信息）
     * @throws Exception
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {

        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 通过用户id查询用户信息（包含角色列表）
     *
     * @param id 用户id
     * @return 模型视图对象（用户信息）
     * @throws Exception
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception {
        UserInfo userInfo = userService.findByIdAndRoleNotIn(id);
        System.out.println(userInfo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("userInfo", userInfo);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 为指定的用户添加指定的角色
     *
     * @param uid 需要添加角色信息的用户id
     * @param rid 需要添加的角色id
     * @return 重定向，返回到用户列表
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam("userId") String uid, @RequestParam("ids") String rid) throws Exception {
        String[] rids = rid.split(",");

        userService.addRoleToUser(uid, rids);
        return "redirect:findAll.do";
    }

}
