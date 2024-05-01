package guru.qa.niffler.test;


import guru.qa.niffler.extensions.BrowserCapabilities;
import guru.qa.niffler.jupiter.extension.UserQueueExtension;
import guru.qa.niffler.model.NifflerUser;
import guru.qa.niffler.pageObjects.FriendsPage;
import guru.qa.niffler.pageObjects.LoginPage;
import guru.qa.niffler.pageObjects.MainPage;
import guru.qa.niffler.pageObjects.PeoplePage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.commons.lang3.tuple.Pair;

import static com.codeborne.selenide.Selenide.open;
import static guru.qa.niffler.constants.Constants.AUTH_PAGE;

@Slf4j
@ExtendWith(BrowserCapabilities.class)
@ExtendWith(UserQueueExtension.class)
public class TestIncomeOutcomeFriendsInvitation {

    @Test
    void testIncomeOutcomeFriendsInvitation1(Pair<NifflerUser, NifflerUser> pair1) {
        log.info("{}{}", pair1.toString(), Thread.currentThread().getStackTrace()[1].getMethodName());
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

    @Test
    void testIncomeOutcomeFriendsInvitation2(Pair<NifflerUser, NifflerUser> pair1) {
        log.info("{}{}", pair1.toString(), Thread.currentThread().getStackTrace()[1].getMethodName());
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

    @Test
    void testIncomeOutcomeFriendsInvitation3(Pair<NifflerUser, NifflerUser> pair1) {
        log.info("{}{}", pair1.toString(), Thread.currentThread().getStackTrace()[1].getMethodName());
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

    @Test
    void testIncomeOutcomeFriendsInvitation4(Pair<NifflerUser, NifflerUser> pair1) {
        log.info("{}{}", pair1.toString(), Thread.currentThread().getStackTrace()[1].getMethodName());
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
