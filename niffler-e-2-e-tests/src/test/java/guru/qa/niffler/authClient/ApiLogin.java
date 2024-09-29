package guru.qa.niffler.authClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiLogin {

    TestUser user() default @TestUser(fake = true);

    String username() default "";

    String password() default "";
}