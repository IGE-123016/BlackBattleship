package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory11Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class SelenideUserStory11Test {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory11() {
        open("https://papergames.io/en/battleship");

        SelenideUserStory11Page page = new SelenideUserStory11Page();
        iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage loginPage = new iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage();
        loginPage.createGuestSession("Test");

        page.clickShopTab();
        page.clickMonstersCategory();
        page.clickShopTab();
        page.clickEmojisCategory();
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
