package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.LoginPage;
import iscteiul.ista.blackbattleship.selenium.pages.UserStory17;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import java.time.Duration;

/**
 * Classe Page Test para o cenário UserStory 17.
 * Testa a abertura correta da secção "History".
 */
public class UserStory17Test {

    private WebDriver driver;
    private UserStory17 pageObject;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(1088, 766));
        
        pageObject = new UserStory17(driver);
        LoginPage loginPage = new LoginPage(driver);

        pageObject.acceptConsentIfPresent(driver);
        loginPage.createGuestSession(driver, "Test");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUserStory17() throws InterruptedException {
        Thread.sleep(1500);

        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.xpath("//*[contains(text(), 'History') or contains(@href, '/en/match-history')]")));

        pageObject.clickHistoryTab(driver);
        Thread.sleep(2000);

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("history"), "Deveria estar na aba de Histórico de Partidas.");
    }
}
