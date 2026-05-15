package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * UserStory08Test - Testes JUnit para User Story 08 (Chat em Tempo Real)
 * 
 * Esta classe contém testes de caixa preta que verificam:
 * - Se a página de chat é acessível
 * - Se o campo de mensagem está disponível
 * - Se há utilizadores online visíveis
 * - Se o botão de envio de mensagem existe
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory08Test {
    private WebDriver driver;
    private UserStory08 userStory08;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://papergames.io/en/battleship");
        
        userStory08 = new UserStory08(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Testa se a página foi carregada com sucesso
     */
    @Test
    public void testPageLoads() {
        assertTrue(userStory08.isPageLoaded(), 
            "A página deve estar completamente carregada");
    }

    /**
     * Testa se o botão de chat está disponível
     */
    @Test
    public void testChatButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory08.getChatButton(), 
            "O botão de chat deve estar disponível");
    }

    /**
     * Testa se o campo de entrada de mensagem está disponível
     */
    @Test
    public void testChatInputFieldExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory08.getChatInputField(), 
            "O campo de entrada de mensagem deve estar disponível");
    }

    /**
     * Testa se o botão de envio de mensagem existe
     */
    @Test
    public void testSendButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory08.getSendButton(), 
            "O botão de envio de mensagem deve estar disponível");
    }
}

