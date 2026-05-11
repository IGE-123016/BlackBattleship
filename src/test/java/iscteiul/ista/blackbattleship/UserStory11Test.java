package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.Dimension;
import java.time.Duration;

/**
 * Classe Page Test para o cenário UserStory 11.
 * Testa a navegação completa pelas opções da Loja.
 */
public class UserStory11Test {

    private WebDriver driver;
    private UserStory11 pageObject;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().maximize(); // Maximizar para garantir visibilidade do menu lateral
        
        pageObject = new UserStory11(driver);
        LoginPage loginPage = new LoginPage(driver);

        pageObject.acceptConsentIfPresent(driver);
        
        // Mantemos o Guest Login por consistência com o resto dos testes
        loginPage.createGuestSession(driver, "Test");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUserStory11() throws InterruptedException {
        Thread.sleep(1500);

        // 1. Clicar em Shop
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.xpath("//*[contains(text(), 'Shop') or contains(@href, '/en/shop')]")));
        pageObject.clickShopTab(driver);
        Thread.sleep(1500);

        // 2. Clicar em Monsters
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.xpath("//img[@alt='Monsters']")));
        pageObject.clickMonstersOption(driver);
        Thread.sleep(4000); // Esperar mais tempo dentro da sub-categoria conforme sugerido

        // 3. Voltar a clicar em Shop (conforme gravado)
        // Forçar scroll para o topo para garantir que o menu está visível
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        Thread.sleep(1000);

        WebElement shopLink = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
            org.openqa.selenium.By.linkText("Shop")));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", shopLink);
        
        // Esperamos que o URL mude ou que a página de categorias carregue
        Thread.sleep(2000); 

        // 4. Clicar em Emojis
        // Usamos um seletor CSS que abrange tanto o alt como a posição, conforme o Selenium IDE sugeriu
        WebElement emojis = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
            org.openqa.selenium.By.cssSelector("img[alt*='Emoji'], .box-shadow-1:nth-child(3) img")));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", emojis);
        Thread.sleep(1500);

        // Validar que navegou com sucesso na loja
        assertTrue(driver.getCurrentUrl().contains("shop"), "Deveria estar numa página relacionada com a Loja.");
    }
}
