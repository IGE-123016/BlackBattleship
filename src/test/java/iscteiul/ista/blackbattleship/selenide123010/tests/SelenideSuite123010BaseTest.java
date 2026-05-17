package iscteiul.ista.blackbattleship.selenide123010.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

/**
 * Base test configuration for the IGE-123010 Selenide suite.
 */
public abstract class SelenideSuite123010BaseTest {

    /**
     * Configures Selenide before each test.
     */
    @BeforeEach
    public void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
        Configuration.headless = false;
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
