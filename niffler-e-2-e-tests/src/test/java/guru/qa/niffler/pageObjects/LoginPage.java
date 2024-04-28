package guru.qa.niffler.pageObjects;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage<LoginPage> {

    private String loginBtn = "a[href*='redirect']";
    private String userNameField = "input[name='username']";
    private String passwordField = "input[name='password']";
    private String submitBtn = "button[type='submit']";

    public LoginPage doLogin(String userName, String password) {
        $(loginBtn).click();
        $(userNameField).setValue(userName);
        $(passwordField).setValue(password);
        $(submitBtn).click();
        return this;
    }

}
