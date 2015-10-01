package com.gm.trade.debug.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;



@Target({ElementType.PARAMETER})
@Retention(RUNTIME)
public @interface APIParam {
    String value() default "";

}
