package cn.com.bonc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于csv文件输出的时候定义对应的字段的描述
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldDesc {

    /**
     * 字段描述
     * @return
     */
    String desc() default "";

    /**
     * 是否使用该字段
     * @return
     */
    boolean use() default true;

}
