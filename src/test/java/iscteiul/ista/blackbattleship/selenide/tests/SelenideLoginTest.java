package iscteiul.ista.blackbattleship.selenide.tests;

import com.codeborne.selenide.Configuration;
import iscteiul.ista.blackbattleship.selenide.pages.SelenideLoginPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SelenideLoginTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1088x766";
        Configuration.timeout = 15000;
        // Optionally disable notifications if necessary:
        // Configuration.browserCapabilities.setCapability("chromeOptions", Map.of("args", List.of("--disable-notifications", "--disable-popup-blocking")));
    }

    @Test
    public void testLogin() {
        open("https://papergames.io/en/battleship");

        SelenideLoginPage loginPage = new SelenideLoginPage();
        
        loginPage.acceptConsentIfPresent();
        loginPage.clickPlayVsRobot();
        loginPage.enterNickname("Test");
        loginPage.clickContinue();
        loginPage.abortGame();
        loginPage.confirmAbort();
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
        com.codeborne.selenide.Selenide.closeWebDriver();
        try { Thread.sleep(5000); } catch (InterruptedException e) {}
    }
}
