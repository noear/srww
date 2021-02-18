package org.noear.srww.uapi.validation;

import org.noear.solon.annotation.Note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author noear 2021/2/18 created
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Logined {
    @Note("logined by session user key")
    String value() default "";

    String message() default "";
}
