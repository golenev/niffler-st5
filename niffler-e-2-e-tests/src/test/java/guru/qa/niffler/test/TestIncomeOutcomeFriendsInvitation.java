package guru.qa.niffler.test;


import guru.qa.niffler.extensions.DesktopCapabilities;
import guru.qa.niffler.model.NifflerUser;
import guru.qa.niffler.pageObjects.FriendsPage;
import guru.qa.niffler.pageObjects.LoginPage;
import guru.qa.niffler.pageObjects.MainPage;
import guru.qa.niffler.pageObjects.PeoplePage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.niffler.constants.Constants.AUTH_PAGE;

@ExtendWith(DesktopCapabilities.class)
public class TestIncomeOutcomeFriendsInvitation {

    private static Object[][] provideArguments() {
        return new Object[][]{
                {NifflerUser.NIFFLER_DIMA, NifflerUser.NIFFLER_QWERTY},
        };
    }

    @ParameterizedTest(name = "Тест создания и получения заявки в друзья")
    @MethodSource("provideArguments")
    void testIncomeOutcomeFriendsInvitation(NifflerUser dima, NifflerUser qwerty) {
        open(AUTH_PAGE, LoginPage.class)
                .doLogin(dima.username(), dima.password())
                .at(MainPage.class)
                .clickAllPeopleSection()
                .at(PeoplePage.class)
                .sendInvitation(qwerty.username())
                .at(MainPage.class)
                .doLogout()
                .at(LoginPage.class)
                .doLogin(qwerty.username(), qwerty.password())
                .at(MainPage.class)
                .clickAllFriendsSection()
                .at(FriendsPage.class)
                .checkInvitation();
    }
}
