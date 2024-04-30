package guru.qa.niffler.pageObjects;

import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.ThreadLocalLogger.getLogger;

@Slf4j
public class LoginPage extends BasePage<LoginPage> {

    private String loginBtn = "a[href*='redirect']";
    private String userNameField = "input[name='username']";
    private String passwordField = "input[name='password']";
    private String submitBtn = "button[type='submit']";

    public LoginPage doLogin(String userName, String password) {
        $(loginBtn).shouldBe(visible).click();
        $(userNameField).setValue(userName);
        $(passwordField).setValue(password);
        $(submitBtn).click();
        getLogger().info("авторизовались под {}", userName);
        return this;
    }

}
