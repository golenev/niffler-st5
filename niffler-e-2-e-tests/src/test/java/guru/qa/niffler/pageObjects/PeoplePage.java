package guru.qa.niffler.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class PeoplePage extends BasePage<PeoplePage> {

    private final String listPeople = "//table/tbody/tr";
    private final String addFriendBtn = "[data-tooltip-id='add-friend']";


    public PeoplePage getPeople() {
        var firstPersonName = $$x(listPeople).first().$$("td").get(1).getText();
        var secondPersonName = $$x(listPeople).last().$$("td").get(1).getText();
        System.out.println(firstPersonName);
        $$x(listPeople).filter(Condition.exactText("qwerty")).first().$(addFriendBtn).click();
        //rows.forEach(System.out::println);
        return this;
    }

}
