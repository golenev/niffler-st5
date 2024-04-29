package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PeoplePage extends BasePage<PeoplePage> {

    private final String listPeople = "//table/tbody/tr";
    private final String addFriendBtn = "[data-tooltip-id='add-friend']";
    private SelenideElement firstUser = $$x(listPeople).first();
    private SelenideElement secondUser = $$x(listPeople).last();
    private final String listUserProperties = "td";
    private final int USER_NAME = 1;

    /**
     * 1. Проверяем, что в списке people две сущности
     * 2. Проверяем, что пока мы не отпрапвили ни одного приглашения, у нас не присутствует заголовок "Pending invitation"
     * 3. Проверяем имя первой сущности
     * 4. Проверяем имя второй сущности
     * 5. Нажимаем кнопку AddFriend для первой сущности с именем qwerty
     * 6. Проверяем, что на месте кнопки появился текст "Pending invitation"
     */
    public PeoplePage sendInvitation(String targetFriend) {
        $$x(listPeople)
                .filter(visible)
                .shouldHave(size(2)
                        .because("Проверяем, что в списке people два пользователя, которых мы создали"));
        $$(".abstract-table__buttons")
                .forEach(a -> a.shouldNot(exactText("Pending invitation")
                        .because("ПОка не отправлено ни одно приглашение, кнопка Pending invitation не должна присутствовать")));
        firstUser.$$(listUserProperties).get(USER_NAME).shouldHave(exactText("qwerty").because("у первого должно быть имя qwerty"));
        secondUser.$$(listUserProperties).get(USER_NAME).shouldHave(exactText("chucknorris").because("у второго пользователя должно быть имя chucknorris"));
        $$x(listPeople).filter(text(targetFriend)).first().find(By.cssSelector(addFriendBtn)).click();
        firstUser.find(By.cssSelector(".abstract-table__buttons")).shouldHave(exactText("Pending invitation"));
        return this;
    }

}
