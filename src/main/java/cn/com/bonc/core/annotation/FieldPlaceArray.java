package cn.com.bonc.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将数组的数据拷贝到对象中的时候，定义在数组的位置
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FieldPlaceArray {

    /**
     * 现实字段对应的在数组中的位置
     * @return
     */
    int place() default 0;

    /**
     * 是否使用该字段
     * @return
     */
    boolean use() default true;

    /**
     * 描述字段的大的分类
     * @return
     */
    String type() default "AOI";

    /**
     * 字段描述
     * @return
     */
    String desc() default "";
}
