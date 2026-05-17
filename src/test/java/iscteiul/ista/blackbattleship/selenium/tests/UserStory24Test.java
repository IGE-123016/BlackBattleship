package iscteiul.ista.blackbattleship.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory24;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Page Test Class for UserStoryTest24.
 *
 * <p>Validates the black-box scenario for US24 - redirecting the user from
 * Papergames to a mobile app store download page.</p>
 */
public class UserStory24Test {

    private WebDriver driver;
    private UserStory24 userStory24;

    /**
     * Starts the browser and opens the Battleship page before each test.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        userStory24 = new UserStory24(driver);
        userStory24.openBattleshipPage();
    }

    /**
     * Closes the browser after each test.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Tests that the mobile app badge redirects to an app store page.
     *
     * @throws InterruptedException when the visual pause is interrupted.
     */
    @Test
    public void testUserStory24() throws InterruptedException {
        userStory24.openPlayStoreLink();
        Thread.sleep(2000);

        assertTrue(userStory24.isAppStorePageOpen(),
                "Clicking the mobile app badge should open an app store page.");
    }
}
