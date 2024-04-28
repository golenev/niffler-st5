package guru.qa.niffler.test;


import guru.qa.niffler.extensions.DesktopCapabilities;
import guru.qa.niffler.pageObjects.LoginPage;
import guru.qa.niffler.pageObjects.MainPage;
import guru.qa.niffler.pageObjects.PeoplePage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static guru.qa.niffler.constants.Constants.AUTH_PAGE;

@ExtendWith(DesktopCapabilities.class)
public class TestIncomeOutcomeFriendsInvitation {

    private static Object[][] provideArguments() {
        return new Object[][]{
                {"dima", "12345"},
               // {"qwerty", "12345"}
        };
    }

    @ParameterizedTest(name = "Тест создания и получения заявки в друзья")
    @MethodSource("provideArguments")
    void testIncomeOutcomeFriendsInvitation() {
        open(AUTH_PAGE, LoginPage.class)
                .doLogin("dima", "12345")
                .at(MainPage.class)
                .clickAlPeopleSection()
                .at(PeoplePage.class)
                .getPeople();
        sleep(999999999);
    }
}
