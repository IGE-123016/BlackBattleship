package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory23;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Test Class for User Story 23.
 *
 * This class contains the JUnit tests related
 * to the Terms & Conditions page navigation.
 */
public class UserStory23Test {

    private WebDriver driver;

    private UserStory23 userStory23;

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

        userStory23 = new UserStory23(driver);
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void tearDown() {

        driver.quit();
    }

    /**
     * Tests the navigation to the Terms & Conditions page.
     */
    @Test
    public void termsAndConditionsNavigationTest() {

        userStory23.openTermsAndConditions();

        System.out.println(driver.getCurrentUrl());

        assertTrue(driver.getCurrentUrl().toLowerCase().contains("terms-conditions"));
    }
}