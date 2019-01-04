package com.niit.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.niit.service.LogService;
import com.niit.util.IPUtil;

@Aspect
public class LogAop {
    
    @Resource
    private LogService logService;
    
    //配置接入点,如果不知道怎么配置,可以百度一下规则
    @Pointcut("execution(* com.niit.controller..*.*(..))") 
    private void log() {} //定义一个切入点
    
    //前置通知，在方法前通知
    @Before("log()")
    public void doAccessCheck(JoinPoint joinPoint) {
        //System.out.println("前置通知-----------------------------------");
    }
    
    //后置通知，在方法执行后通知
    @After("log()")
    public void after(JoinPoint joinPoint) {
        //System.out.println("后置通知-----------------------------------");
    }
    
    //返回通知，在方法返回结果之后通知
    @AfterReturning("log()")  
    public void doAfter(JoinPoint joinPoint) {  
        //System.out.println("返回通知-----------------------------------");  
    }
    
    //异常通知，在方法抛出异常之后通知
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        //System.out.println("异常通知-----------------------------------");
    }
    
    //环绕通知，围绕着方法执行
    @Around("log()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println("进入环绕通知-----------------------------------");
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        //获取登录用户id
        String userId = request.getRemoteUser();
        
        //获取系统ip
        String ip = IPUtil.getIpAddr(request);
        if("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        
        //获取系统url
        String url = request.getServletPath();
        
        //获取系统参数
        String parameter = "{";
        for(Map.Entry<String, String[]> entry: request.getParameterMap().entrySet())
            parameter += entry.getKey() + ": " + entry.getValue()[0] + ", ";
        parameter += "}";
        
        //获取系统时间
        Date time = new Date();
        
        //方法通知前获取时间
        long start = System.currentTimeMillis();
        
        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        // 获得被拦截的方法
        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        String mod = null, met = null;
        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的Log就是我自己自定义的注解
            if (method.isAnnotationPresent(Log.class)) {
                Log log = method.getAnnotation(Log.class);
                mod = log.module();
                met = log.method();
            }
        }
        
        //执行该方法
        String result = null, message = null;
        Object object = null;
        try {
            object = pjp.proceed();
            result = "success";
        } catch (Exception e) {
            result = "fail";
            message = e.getMessage();
        }
        
        //方法通知前获取时间
        long end = System.currentTimeMillis();
        
        //将计算好的时间
        Long length = end - start;
        
        //保存在实体中
        com.niit.bean.Log log = new com.niit.bean.Log(userId, ip, url, parameter, time, length, mod, met, result, message);
        
        //保存进数据库
        Integer i = logService.insertLog(log);
        
        //System.out.println("退出方法-----------------------------------");
        return object;
    }
}