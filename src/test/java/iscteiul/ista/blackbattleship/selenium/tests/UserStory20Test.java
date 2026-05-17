package iscteiul.ista.blackbattleship.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory20;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Page Test Class for UserStoryTest20.
 *
 * <p>Validates the black-box scenario for US20 - changing sound/interface
 * settings through the game settings panel.</p>
 */
public class UserStory20Test {

    private WebDriver driver;
    private UserStory20 userStory20;

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

        userStory20 = new UserStory20(driver);
        userStory20.openBattleshipPage();
        userStory20.acceptConsentIfPresent();
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
     * Tests that the user can toggle the sound setting and restore it.
     *
     * @throws InterruptedException when the visual pause is interrupted.
     */
    @Test
    public void testUserStory20() throws InterruptedException {
        userStory20.openSettings();
        Thread.sleep(1000);

        assertTrue(userStory20.isSoundSettingVisible(),
                "The sound setting should be visible in the settings panel.");

        String initialState = userStory20.getSoundState();
        userStory20.toggleSound();
        Thread.sleep(1000);

        String changedState = userStory20.getSoundState();
        assertNotEquals(initialState, changedState,
                "Toggling sound should change the sound setting state.");

        userStory20.toggleSound();
        Thread.sleep(1000);
    }
}
