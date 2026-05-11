package iscteiul.ista.blackbattleship.selenium.tests;

import iscteiul.ista.blackbattleship.selenium.pages.LoginPage;
import iscteiul.ista.blackbattleship.selenium.pages.UserStory8;
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
 * Classe Page Test para o cenário de teste UserStory 8.
 * Utiliza JUnit 5 (Jupiter) e o padrão Page Object Model (via classe UserStory8)
 * para validar a abertura da página e a navegação correta para a aba "Messaging".
 */
public class UserStory8Test {

    private WebDriver driver;
    private UserStory8 pageObject;

    /**
     * Configuração pré-teste.
     * Inicializa o WebDriver do Chrome, define tempos de espera implícitos e maximiza/ajusta a janela.
     * Instancia o Page Object correspondente a este cenário.
     */
    @BeforeEach
    public void setUp() {
        // Bloquear popups do browser (mesma defesa do teste 9)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Abre a página
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(1088, 766));
        
        // Inicializar os Page Objects
        pageObject = new UserStory8(driver);
        LoginPage loginPage = new LoginPage(driver);
        
        // Fechar eventuais popups de cookies primeiro
        pageObject.acceptConsentIfPresent(driver);

        // Criar Sessão de Convidado (Guest) para desbloquear menus
        loginPage.createGuestSession(driver, "Test");
    }

    /**
     * Limpeza pós-teste.
     * Encerra a sessão do navegador fechando o WebDriver para libertar recursos.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Executa o cenário de teste da User Story 8.
     * O teste inclui comandos de espera (`Thread.sleep`) para simular o tempo de
     * visualização e interação do utilizador humano com a Interface Gráfica (GUI).
     *
     * @throws InterruptedException Exceção propagada pelas pausas na thread
     */
    @Test
    public void testUserStory8() throws InterruptedException {
        // Simular o tempo de leitura do ecrã por um utilizador real (1.5 segundos)
        Thread.sleep(1500); 

        // Adicionar uma espera explícita robusta de 15 segundos para dar tempo à aplicação
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(
            org.openqa.selenium.By.xpath("//*[contains(text(), 'Messaging') or contains(@href, '/en/chat')]")));

        // O utilizador repara na aba "Messaging" e clica (usando JS para evitar interceções)
        pageObject.clickMessagingTab(driver);

        // Aguardar o carregamento dos elementos ou a mudança de secção da Single Page Application
        Thread.sleep(2000); 

        // Validar que a ação surtiu o efeito desejado verificando se o URL atual reflete o chat
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("/chat") || currentUrl.contains("Messaging"), 
                   "A navegação para o chat falhou ou a janela não respondeu como esperado.");
    }
}
