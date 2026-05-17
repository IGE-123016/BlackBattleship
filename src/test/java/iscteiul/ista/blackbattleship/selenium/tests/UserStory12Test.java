package iscteiul.ista.blackbattleship.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory12;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Page Test Class for UserStoryTest12.
 *
 * <p>Validates the black-box scenario for US12 - filtering shop items by
 * category, using the operations exposed by {@link UserStory12}.</p>
 */
public class UserStory12Test {

    private WebDriver driver;
    private UserStory12 userStory12;

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

        userStory12 = new UserStory12(driver);
        userStory12.openBattleshipPage();
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
     * Tests that the user can filter shop items by category.
     *
     * @throws InterruptedException when the visual pause is interrupted.
     */
    @Test
    public void testUserStory12() throws InterruptedException {
        userStory12.openShop();
        Thread.sleep(1000);
        assertTrue(userStory12.isShopPageOpen(),
                "Shop page should be opened from the menu.");

        userStory12.selectMonstersCategory();
        Thread.sleep(1000);
        assertTrue(userStory12.isCategorySelected("avatars"),
                "Monsters/avatars category should be selected.");

        userStory12.openShop();
        Thread.sleep(1000);

        userStory12.selectEmojisCategory();
        Thread.sleep(1000);
        assertTrue(userStory12.isCategorySelected("emojis"),
                "Emojis category should be selected.");
    }
}
