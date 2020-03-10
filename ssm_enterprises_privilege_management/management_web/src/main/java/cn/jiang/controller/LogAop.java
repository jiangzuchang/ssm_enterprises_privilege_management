package cn.jiang.controller;


import cn.jiang.domain.SysLog;
import cn.jiang.service.ISysLogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * 日志生成类（切面）
 * 代理、反射与注解
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;//Request对象(已在web.xml中配置RequestContextListener监听）

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//开始时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    /**
     * 前置通知，记录开始访问时间、
     *
     * @param jp JoinPoint接口，可获取需要执行的类名和方法名等信息
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    //前置通知 获取开始时间，类名，方法名
    @Before("execution(* cn.jiang.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException, SecurityException {
        //开始访问的时间
        visitTime = new Date();
        //获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
        Signature signature = jp.getSignature();
        //获取访问类名
        clazz = jp.getTarget().getClass();
        //获取访问方法对象
        //获取访问方法名
        String methodName = signature.getName();
        //获取方法执行的参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {//空参方法
            method = clazz.getMethod(methodName);
        } else {//有参方法
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知，计算访问时长，获取访问路径，访问者ip，访问者用户名等信息
     * 将所有日志信息封装到日志实体类中，调用service层保存日志数据
     *
     * @throws ParseException
     */
    //后置通知
    @After("execution(* cn.jiang.controller.*.*(..))")
    public void doAfter() throws ParseException {
        //计算访问时间
        Date endTime = new Date();
        long executionTime = endTime.getTime() - visitTime.getTime();
        String url = "";
        //获取访问路径（通过反射获取注解内的值）
        if (clazz != null && method != null && clazz != LogAop.class) {
            //获取注解
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            //获取类上的注解值
            if (classAnnotation != null) {
                String[] value1 = classAnnotation.value();
                //获取方法上的注解值
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] value2 = methodAnnotation.value();
                    url = value1[0] + value2[0];
                }
            }

        }
        //获取访问ip
        String remoteAddr = request.getRemoteAddr();
        //获取访问者用户名
        SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登录的用户
        User user = (User) context.getAuthentication().getPrincipal();//这个User对象是spring自己的
        String username = user.getUsername();

//        从request中获取用户名的方法
//        request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

        //日志数据封装
        SysLog sysLog = new SysLog();
        System.out.println("[类名]:" + clazz.getName() + "[方法名]:" + method.getName());
        sysLog.setMethod("[类名]:" + clazz.getName() + "[方法名]:" + method.getName());
        sysLog.setVisitTime(visitTime);
        sysLog.setUrl(url);
        sysLog.setIp(remoteAddr);
        sysLog.setUsername(username);
        sysLog.setExecutionTime(executionTime);

        //调用service完成数据的插入
        sysLogService.save(sysLog);

    }
}
