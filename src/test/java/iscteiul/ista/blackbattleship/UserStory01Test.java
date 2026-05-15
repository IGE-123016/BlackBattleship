package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * UserStory01Test - Testes JUnit para User Story 01 (Geração Aleatória de Frota)
 * 
 * Esta classe contém testes de caixa preta que verificam:
 * - Se a página do jogo carrega corretamente
 * - Se o tabuleiro 10x10 é gerado
 * - Se os navios são colocados aleatoriamente
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory01Test {
    private WebDriver driver;
    private UserStory01 userStory01;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://papergames.io/en/battleship");
        
        userStory01 = new UserStory01(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Testa se a página de jogo foi carregada com sucesso
     */
    @Test
    public void testGamePageLoads() {
        assertTrue(userStory01.isGamePageLoaded(), 
            "A página do jogo deve estar completamente carregada");
    }

    /**
     * Testa se o tabuleiro 10x10 é visível após iniciar o jogo
     */
    @Test
    public void testBoardIsVisibleAfterGameStart() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(userStory01.isBoardVisible(), 
            "O tabuleiro 10x10 deve estar visível após iniciar o jogo");
    }

    /**
     * Testa se os navios são colocados aleatoriamente no tabuleiro
     */
    @Test
    public void testShipsAreRandomlyPlaced() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(userStory01.isBoardVisible(), 
            "O tabuleiro deve estar visível");
        
        assertTrue(userStory01.areShipsPlaced(), 
            "Os navios devem estar colocados aleatoriamente no tabuleiro");
    }

    /**
     * Testa se a geração randômica pode ser repetida (botão Randomize)
     */
    @Test
    public void testRandomizeButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory01.getRandomizeButton(), 
            "Deve existir um botão para gerar aleatoriamente os navios novamente");
    }
}

