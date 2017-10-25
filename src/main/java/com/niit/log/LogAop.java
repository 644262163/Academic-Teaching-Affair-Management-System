package com.niit.log;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

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

@Aspect
public class LogAop {
    
    @Resource
    private LogService logService;
    
    //���ý����,�����֪����ô����,���԰ٶ�һ�¹���
    @Pointcut("execution(* com.niit.controller..*.*(..))") 
    private void log() {} //����һ�������
    
    //ǰ��֪ͨ���ڷ���ǰ֪ͨ
    @Before("log()")
    public void doAccessCheck(JoinPoint joinPoint) {
        //System.out.println("ǰ��֪ͨ-----------------------------------");
    }
    
    //����֪ͨ���ڷ���ִ�к�֪ͨ
    @After("log()")
    public void after(JoinPoint joinPoint) {
        //System.out.println("����֪ͨ-----------------------------------");
    }
    
    //����֪ͨ���ڷ������ؽ��֮��֪ͨ
    @AfterReturning("log()")  
    public void doAfter(JoinPoint joinPoint) {  
        //System.out.println("����֪ͨ-----------------------------------");  
    }
    
    //�쳣֪ͨ���ڷ����׳��쳣֮��֪ͨ
    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void doAfterThrow(JoinPoint joinPoint, Throwable e) {
        //System.out.println("�쳣֪ͨ-----------------------------------");
    }
    
    //����֪ͨ��Χ���ŷ���ִ��
    @Around("log()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        //System.out.println("���뻷��֪ͨ-----------------------------------");
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        //��ȡ��¼�û�id
        String userId = request.getRemoteUser();
        
        //��ȡϵͳip
        String ip = request.getLocalAddr();
        if("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        
        //��ȡϵͳurl
        String url = request.getServletPath();
        
        //��ȡϵͳ����
        String parameter = request.getParameterMap().toString();
        
        //��ȡϵͳʱ��
        Date time = new Date();
        
        //����֪ͨǰ��ȡʱ��
        long start = System.currentTimeMillis();
        
        // ���ص�ʵ���࣬���ǵ�ǰ����ִ�е�controller
        Object target = pjp.getTarget();
        // ���صķ������ơ���ǰ����ִ�еķ���
        String methodName = pjp.getSignature().getName();
        // ���صķ�������
        Object[] args = pjp.getArgs();
        // ���صķŲ�������
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();
        // ��ñ����صķ���
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
            // �ж��Ƿ�����Զ����ע�⣬˵��һ�������Log�������Լ��Զ����ע��
            if (method.isAnnotationPresent(Log.class)) {
                Log log = method.getAnnotation(Log.class);
                mod = log.module();
                met = log.method();
            }
        }
        
        //ִ�и÷���
        String result = null, message = null;
        Object object = null;
        try {
            object = pjp.proceed();
            result = "success";
        } catch (Exception e) {
            result = "fail";
            message = e.getMessage();
        }
        
        //����֪ͨǰ��ȡʱ��
        long end = System.currentTimeMillis();
        
        //������õ�ʱ��
        Long length = end - start;
        
        //������ʵ����
        com.niit.bean.Log log = new com.niit.bean.Log(userId, ip, url, parameter, time, length, mod, met, result, message);
        
        //��������ݿ�
        Integer i = logService.insertLog(log);
        
        //System.out.println("�˳�����-----------------------------------");
        return object;
    }
}