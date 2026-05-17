package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;

import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory22Page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideUserStory22Test {

    @BeforeAll
    public static void setUp() {

        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory22() {

        open("https://papergames.io/en/battleship");

        SelenideUserStory22Page page =
                new SelenideUserStory22Page();

        page.clickPrivacyPolicy();

        sleep(3000);

        assertTrue(webdriver().driver()
                .url()
                .contains("privacy-policy"));
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