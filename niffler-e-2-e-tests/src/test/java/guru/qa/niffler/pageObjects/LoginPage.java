package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class LoginPage extends BasePage<LoginPage> {

    private String userNameField = "input[name='username']";
    private String passwordField = "input[name='password']";
    private final SelenideElement loginBtn = $("a[href*='redirect']");
    private final SelenideElement usernameInput = $(userNameField);
    private final SelenideElement passwordInput = $(passwordField);
    private final SelenideElement submitBtn = $("button[type='submit']");

    public LoginPage doLogin(String userName, String password) {
        loginBtn.shouldBe(visible).click();
        usernameInput.setValue(userName);
        passwordInput.setValue(password);
        submitBtn.click();
        log.info("авторизовались под {}", userName);
        return this;
    }

}
