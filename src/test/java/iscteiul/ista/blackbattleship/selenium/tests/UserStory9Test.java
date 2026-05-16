package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.LoginPage;
import iscteiul.ista.blackbattleship.selenium.pages.UserStory9;
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
 * Classe Page Test para o cenário de teste UserStory 9 (Lista de Amigos).
 */
public class UserStory9Test {

    private WebDriver driver;
    private UserStory9 pageObject;

    @BeforeEach
    public void setUp() {
        // Bloquear avisos do navegador (ex: notificações) e popups abusivos
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(1088, 766));
        
        pageObject = new UserStory9(driver);
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
    public void testUserStory9() throws InterruptedException {
        Thread.sleep(1500); 

        // 1. Esperar que o botão "Friends" exista na página (agora que o login foi feito)
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.xpath("//*[contains(text(), 'Friends') or contains(@href, '/en/friends')]")));

        // 3. Clicar no botão usando JavascriptExecutor (ignora anúncios sobrepostos no ecrã)
        pageObject.clickFriendsTab(driver);
        Thread.sleep(2000); 

        // 4. Validação
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/friends") || currentUrl.contains("Friends"), 
                   "O clique na aba de Amigos não atualizou a janela corretamente.");
    }
}
