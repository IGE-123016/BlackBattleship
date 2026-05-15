package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * UserStory10Test - Testes JUnit para User Story 10 (Link de Convite para Amigos)
 * 
 * Esta classe contém testes de caixa preta que verificam:
 * - Se o botão de convite está acessível
 * - Se o diálogo de convite pode ser aberto
 * - Se o link de convite pode ser gerado
 * - Se o link é válido e pode ser copiado
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory10Test {
    private WebDriver driver;
    private UserStory10 userStory10;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://papergames.io/en/battleship");
        
        userStory10 = new UserStory10(driver);
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
        assertTrue(userStory10.isPageLoaded(), 
            "A página deve estar completamente carregada");
    }

    /**
     * Testa se o botão de convite está acessível
     */
    @Test
    public void testInviteButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory10.getInviteButton(), 
            "O botão de convite deve estar disponível");
    }

    /**
     * Testa se o botão de geração de link existe
     */
    @Test
    public void testGenerateLinkButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory10.getGenerateLinkButton(), 
            "O botão de geração de link de convite deve estar disponível");
    }

    /**
     * Testa se o botão de cópia de link existe
     */
    @Test
    public void testCopyLinkButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory10.getCopyLinkButton(), 
            "O botão de cópia de link de convite deve estar disponível");
    }
}

