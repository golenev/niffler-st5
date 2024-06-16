package guru.qa.niffler.test;

import guru.qa.niffler.extensions.BrowserCapabilities;
import guru.qa.niffler.jupiter.annotation.Category;
import guru.qa.niffler.jupiter.annotation.Spend;
import guru.qa.niffler.model.CurrencyValues;
import guru.qa.niffler.model.SpendJson;
import guru.qa.niffler.pageObjects.LoginPage;
import guru.qa.niffler.pageObjects.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.niffler.constants.Constants.AUTH_PAGE;


@ExtendWith(BrowserCapabilities.class)
public class SpendingTest {

    @Category(category = "обучение6", username = "dima")
    @Spend(currency = CurrencyValues.RUB, amount = 65000.0, description = "курсы99")
    @Test
    void spendingShouldBeDeletedAfterTableAction(SpendJson spendJson) {
        open(AUTH_PAGE, LoginPage.class)
                .doLogin("dima", "12345")
                .at(MainPage.class)
                .selectCategory(spendJson.category())
                .deleteCategory();
    }
}
