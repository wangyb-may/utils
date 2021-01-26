package com.wyb.util.annotation;


import java.lang.annotation.*;

/**
 * 创建时间：2021/1/21 12:01
 *
 * @author wyb
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TokenVerification {
}
