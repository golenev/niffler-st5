package guru.qa.niffler.test;


import guru.qa.niffler.extensions.BrowserCapabilities;
import guru.qa.niffler.jupiter.extension.UserQueueExtension;
import guru.qa.niffler.model.NifflerUser;
import guru.qa.niffler.model.UserPairs;
import guru.qa.niffler.pageObjects.FriendsPage;
import guru.qa.niffler.pageObjects.LoginPage;
import guru.qa.niffler.pageObjects.MainPage;
import guru.qa.niffler.pageObjects.PeoplePage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.niffler.constants.Constants.AUTH_PAGE;

@ExtendWith(BrowserCapabilities.class)
//@ExtendWith(UserQueueExtension.class)

public class TestIncomeOutcomeFriendsInvitation {

    private static Object[][] provideArguments() {
        return new Object[][]{
                {Pair.of(NifflerUser.NIFFLER_DIMA, NifflerUser.NIFFLER_QWERTY)},
                {Pair.of(NifflerUser.NIFFLER_CHUCKNORRIS, NifflerUser.NIFFLER_DIMA)},
        };
    }

    @ParameterizedTest(name = "Тест создания и получения заявки в друзья")
    @MethodSource("provideArguments")
    void testIncomeOutcomeFriendsInvitation(Pair<NifflerUser, NifflerUser> pair1) throws InterruptedException {

        NifflerUser invitingUser = pair1.getLeft();
        NifflerUser receivingUser = pair1.getRight();

        open(AUTH_PAGE, LoginPage.class)
                .doLogin(invitingUser.username(), invitingUser.password())
                .at(MainPage.class)
                .clickAllPeopleSection()
                .at(PeoplePage.class)
                .sendInvitation(receivingUser.username())
                .at(MainPage.class)
                .doLogout()
                .at(LoginPage.class)
                .doLogin(receivingUser.username(), receivingUser.password())
                .at(MainPage.class)
                .clickAllFriendsSection()
                .at(FriendsPage.class)
                .checkInvitation(invitingUser.username());
    }
}
