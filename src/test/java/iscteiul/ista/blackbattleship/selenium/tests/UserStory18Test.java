package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory18;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class for User Story 18.
 *
 * This class contains the JUnit tests related
 * to the strategies and tactics section.
 */
public class UserStory18Test {

    private WebDriver driver;

    private UserStory18 userStory18;

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

        userStory18 = new UserStory18(driver);
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void tearDown() {

        driver.quit();
    }

    /**
     * Tests if the strategies section is visible.
     */
    @Test
    public void strategiesSectionTest() {

        userStory18.scrollToStrategiesSection();

        assertTrue(userStory18.isStrategiesSectionDisplayed());
    }
}