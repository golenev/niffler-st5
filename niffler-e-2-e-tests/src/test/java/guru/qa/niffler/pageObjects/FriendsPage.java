package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class FriendsPage extends BasePage<FriendsPage> {

    private final String listPeople = "//table/tbody/tr";
    SelenideElement firstUser = $$x(listPeople).first();
    private final String listUserProperties = "td";
    private final int USER_NAME = 1;

    public FriendsPage checkInvitation() {
        $$x(listPeople)
                .filter(visible)
                .shouldHave(size(1)
                        .because("в списке invitations одно приглашение"));
        firstUser.$$(listUserProperties)
                .get(USER_NAME)
                .shouldHave(exactText("dima")
                        .because("имя пользователя, которая отправил нам заявку в друзья должно быть dima"));
        $("[data-tooltip-id='decline-invitation']").click();
        return this;
    }

}



