package iscteiul.ista.blackbattleship.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import iscteiul.ista.blackbattleship.selenium.pages.UserStory14;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Page Test Class for UserStoryTest14.
 *
 * <p>Validates the black-box scenario for US14 - filling a new tournament
 * creation flow until the site requests account creation.</p>
 */
public class UserStory14Test {

    private WebDriver driver;
    private UserStory14 userStory14;

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

        userStory14 = new UserStory14(driver);
        userStory14.openBattleshipPage();
        userStory14.acceptConsentIfPresent();
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
     * Tests that an organizer can fill the tournament creation details.
     *
     * @throws InterruptedException when the visual pause is interrupted.
     */
    @Test
    public void testUserStory14() throws InterruptedException {
        userStory14.openCreateTournament();
        Thread.sleep(1000);

        userStory14.selectBattleshipGame();
        Thread.sleep(1000);

        userStory14.enterTournamentName("TORNEIO TESTE");
        Thread.sleep(1000);

        userStory14.selectBestOfFive();
        Thread.sleep(1000);

        userStory14.createAndShare();
        Thread.sleep(1000);

        assertTrue(userStory14.isAccountRequiredModalDisplayed(),
                "Creating and sharing a tournament should open the account creation flow.");
    }
}
