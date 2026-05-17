package iscteiul.ista.blackbattleship.selenide123016;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory17Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideUserStory17Test {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory17() {
        open("https://papergames.io/en/battleship");

        SelenideUserStory17Page page = new SelenideUserStory17Page();
        iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage loginPage = new iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage();
        loginPage.createGuestSession("Test");

        page.clickHistoryTab();
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
