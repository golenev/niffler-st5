package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static guru.qa.niffler.constants.Constants.CENTER;

@Slf4j
public class MainPage extends BasePage<MainPage> {

    private final String trSet = "tr";
    private final String tdSet = "td";
    private final String deleteBtn = ".spendings__bulk-actions button";
    private final String tableBody = ".spendings-table tbody";

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
        $(logoutBtn).shouldBe(visible).click();
        return this;
    }

    public MainPage clickAllFriendsSection () {
        $(headerSigh).shouldBe(visible);
        $(allFriendsHeaderBtn).click();
        return this;
    }

}
