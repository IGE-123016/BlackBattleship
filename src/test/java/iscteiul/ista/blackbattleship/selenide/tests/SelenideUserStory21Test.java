package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;

import iscteiul.ista.blackbattleship.selenide.pages.SelenideUserStory21Page;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideUserStory21Test {

    @BeforeAll
    public static void setUp() {

        Configuration.browserSize = "1088x766";
    }

    @Test
    public void testUserStory21() {

        open("https://papergames.io/en/battleship");

        SelenideUserStory21Page page =
                new SelenideUserStory21Page();

        page.clickChangelog();

        sleep(3000);

        assertTrue(webdriver().driver()
                .url()
                .contains("changelog"));
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