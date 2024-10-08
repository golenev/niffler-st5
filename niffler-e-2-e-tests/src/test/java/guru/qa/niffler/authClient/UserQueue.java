package guru.qa.niffler.authClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserQueue {

    UserType value() default UserType.COMMON;

    enum UserType {
        WITH_FRIENDS, COMMON
    }
}
