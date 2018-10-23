package cn.com.bonc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于缓存中描述对应的对象没有注解
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
public @interface NotAnnotation {

    /**
     * 描述
     * @return 默认返回空字符串
     */
    String desc() default "";

    /**
     * 字段是否使用
     * @return 默认返回true
     */
    boolean use() default true;

}
