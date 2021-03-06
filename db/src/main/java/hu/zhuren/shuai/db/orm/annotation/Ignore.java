package hu.zhuren.shuai.db.orm.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhuren.hu on 15/7/15.
 * 忽略 Ignore 的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface Ignore {
}
