package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory9Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideUserStory9Test {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory9() {
        open("https://papergames.io/en/battleship");

        SelenideUserStory9Page page = new SelenideUserStory9Page();
        iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage loginPage = new iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage();
        loginPage.createGuestSession("Test");

        page.clickFriendsTab();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
    }
}
