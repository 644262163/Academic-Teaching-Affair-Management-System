package com.niit.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
 
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取  
@Target({ ElementType.METHOD }) //目标是方法 
@Documented //文档生成时，该注解将被包含在javadoc中，可去掉 
public @interface Log {
    String module() default "";
    String method() default "";
}
