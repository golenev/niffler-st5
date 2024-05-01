package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class FriendsPage extends BasePage<FriendsPage> {

    private final String listPeople = "//table/tbody/tr";
    private final SelenideElement firstUser = $$x(listPeople).first();
    private final ElementsCollection listInvitersName = $$x("//table/tbody/tr/td[2]");
    private final String listUserProperties = "td";
    private final int USER_NAME = 1;
    private final SelenideElement declineInvitation = $("[data-tooltip-id='decline-invitation']");


    public FriendsPage checkInvitation(String invitingUser) {
        log.info("Открыли раздел управления друзьями и ищем заявку в друзья от {}", invitingUser);
        $$x(listPeople)
                .filter(visible)
                .shouldHave(sizeGreaterThanOrEqual(1)
                        .because("в списке invitations должно быть минимум одно приглашение"));
        listInvitersName
                .shouldHave(containExactTextsCaseSensitive(invitingUser)
                        .because("Среди пользователей, котоыре отправили нам приглашение, должно быть имя %s".formatted(invitingUser)));
        declineInvitation.click();
        return this;
    }

}



