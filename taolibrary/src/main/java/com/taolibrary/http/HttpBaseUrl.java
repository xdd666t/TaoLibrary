package com.taolibrary.http;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能描述:
 * </p>
 * 创建人: luoxinrun
 * 创建时间: 2019/10/26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpBaseUrl {
    String value() default "";
}
