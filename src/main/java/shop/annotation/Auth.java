package shop.annotation;


import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 鉴权
 * 加在 类 或 方法 上面，指定最低的用户权限的用户组，低于此用户组不能调用
 * 方法上面的配置会覆盖类上面的配置，类已经配置可不必配置方法
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD,TYPE})
@Inherited
public @interface Auth {
    Group value();
}
