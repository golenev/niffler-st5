package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.constants.Constants.CENTER;

public class MainPage extends BasePage<MainPage> {

    private final String trSet = "tr";
    private final String tdSet = "td";
    private final String deleteBtn = ".spendings__bulk-actions button";
    private final String tableBody = ".spendings-table tbody";
    private final String allPeopleHeaderBtn = "[href='/people']";
    private final String allFriendsHeaderBtn = "[href='/friends']";
    private final String logoutBtn = "[data-tooltip-id='logout']";
    private final String headerSigh = ".header__sign";

    public MainPage selectCategory(String description) {
        SelenideElement rowWithSpending = $(tableBody)
                .$$(trSet)
                .find(text(description));
        rowWithSpending.$$(tdSet)
                .first()
                .scrollIntoView(CENTER)
                .click();
        return this;
    }

    public MainPage deleteCategory() {
        $(deleteBtn).click();
        $(tableBody).$$(trSet)
                .shouldHave(size(0));
        return this;
    }

    public MainPage clickAllPeopleSection() {
        $(allPeopleHeaderBtn).shouldBe(visible).click();
        return this;
    }

    public MainPage doLogout () {
        $(logoutBtn).click();
        return this;
    }

    public MainPage clickAllFriendsSection () {
        $(headerSigh).shouldBe(visible);
        $(allFriendsHeaderBtn).click();
        return this;
    }

}
