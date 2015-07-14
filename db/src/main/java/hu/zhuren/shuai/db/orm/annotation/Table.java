package hu.zhuren.shuai.db.orm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhuren.hu on 14/7/15.
 * 表
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    public static final String DEFAULT_PK_NAME = "id";

    public String name();

    public String pk() default DEFAULT_PK_NAME;
}
