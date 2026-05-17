package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory8Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideUserStory8Test {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory8() {
        open("https://papergames.io/en/battleship");

        SelenideUserStory8Page page = new SelenideUserStory8Page();
        iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage loginPage = new iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage();
        loginPage.createGuestSession("Test");

        page.clickMessagingTab();
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
