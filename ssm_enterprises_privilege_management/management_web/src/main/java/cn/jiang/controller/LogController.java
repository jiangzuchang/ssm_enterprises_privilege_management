package cn.jiang.controller;

import cn.jiang.domain.SysLog;
import cn.jiang.service.ISysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 日志信息处理（控制层）
 */
@Controller
@RequestMapping("/sysLog")
public class LogController {
    @Autowired
    private ISysLogService sysLogService;

    /**
     * 查询所有
     * @param page 页码
     * @param pageSize 单页大小
     * @return 模型视图对象
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) throws Exception {
        List<SysLog> sysLogs = sysLogService.findAll(page, pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(sysLogs);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("syslog-list");
        return mv;
    }

}
