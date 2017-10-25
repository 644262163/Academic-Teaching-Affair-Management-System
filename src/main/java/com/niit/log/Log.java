package com.niit.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Retention(RetentionPolicy.RUNTIME) //ע�����class�д��ڣ�����ʱ��ͨ�������ȡ  
@Target({ ElementType.METHOD }) //Ŀ���Ƿ��� 
@Documented //�ĵ�����ʱ����ע�⽫��������javadoc�У���ȥ�� 
public @interface Log {
    String module() default "";
    String method() default "";
}
