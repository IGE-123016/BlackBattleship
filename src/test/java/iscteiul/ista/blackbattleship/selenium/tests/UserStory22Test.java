package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory22;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class for User Story 22.
 *
 * This class contains the JUnit tests related
 * to the Privacy Policy page navigation.
 */
public class UserStory22Test {

    private WebDriver driver;

    private UserStory22 userStory22;

    /**
     * Initializes the browser before each test.
     */
    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://papergames.io/en/battleship");

        userStory22 = new UserStory22(driver);
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void tearDown() {

        driver.quit();
    }

    /**
     * Tests the navigation to the Privacy Policy page.
     */
    @Test
    public void privacyPolicyNavigationTest() {

        userStory22.openPrivacyPolicy();

        assertTrue(driver.getCurrentUrl().contains("privacy"));
    }
}