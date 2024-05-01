package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class PeoplePage extends BasePage<PeoplePage> {

    private final String listPeople = "//table/tbody/tr";
    private final String addFriendBtn = "[data-tooltip-id='add-friend']";
    private SelenideElement firstUser = $$x(listPeople).first();
    private SelenideElement secondUser = $$x(listPeople).last();
    private final String listUserProperties = "td";
    private final int USER_NAME = 1;
    private String btnsList = ".abstract-table__buttons";
    private final String pendingInvitationTitle = "Pending invitation";
    private final ElementsCollection listPeoples = $$x(listPeople);

    /**
     * 1. Проверяем, что в списке people две сущности
     * 2. Проверяем, что пока мы не отпрапвили ни одного приглашения, у нас не присутствует заголовок "Pending invitation"
     * 3. Проверяем имя первой сущности
     * 4. Проверяем имя второй сущности
     * 5. Нажимаем кнопку AddFriend для первой сущности с именем qwerty
     * 6. Проверяем, что на месте кнопки появился текст "Pending invitation"
     */
    public PeoplePage sendInvitation(String targetFriend) {
        log.info("Открыли раздел AllPeople и ищём {}", targetFriend);
        listPeoples
                .filter(visible)
                .shouldHave(size(2)
                        .because("Проверяем, что в списке people два захардкоженных пользователя"));
        listPeoples
                .filter(text(targetFriend)
                        .because("среди списка пользователей, находим %s и отправляем приглашение".formatted(targetFriend)))
                .first().$$(listUserProperties)
                .get(USER_NAME)
                .shouldHave(exactText(targetFriend)
                        .because("у первого должно быть имя %s".formatted(targetFriend)));
        listPeoples
                .filter(text(targetFriend)
                        .because("среди списка пользователей, находим %s и отправляем приглашение".formatted(targetFriend)))
                .first()
                .find(By.cssSelector(addFriendBtn))
                .click();
        listPeoples
                .filter(text(targetFriend)
                        .because("среди списка пользователей, находим %s и проверяем, что приглашение отправлено".formatted(targetFriend)))
                .first()
                .find(By.cssSelector(btnsList))
                .shouldHave(exactText(pendingInvitationTitle));
        log.info("Отправили приглашение для {}", targetFriend);
        return this;
    }

}
