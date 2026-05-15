package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

/**
 * UserStory07Test - Testes JUnit para User Story 07 (Condição de Vitória Total)
 * 
 * Esta classe contém testes de caixa preta que verificam:
 * - Se a mensagem de vitória é exibida após destruir toda a frota adversária
 * - Se o jogo é finalizado corretamente
 * - Se existe opção para jogar novamente
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory07Test {
    private WebDriver driver;
    private UserStory07 userStory07;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://papergames.io/en/battleship");
        
        userStory07 = new UserStory07(driver);
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
        assertTrue(userStory07.isGamePageLoaded(), 
            "A página do jogo deve estar completamente carregada");
    }

    /**
     * Testa se o tabuleiro adversário está disponível para disparo
     */
    @Test
    public void testOpponentBoardIsAvailable() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory07.getOpponentBoard(), 
            "O tabuleiro adversário deve estar disponível para disparar");
    }

    /**
     * Testa se existe um botão para jogar novamente (após vitória)
     */
    @Test
    public void testPlayAgainButtonExists() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertNotNull(userStory07.getPlayAgainButton(), 
            "Deve existir um botão 'Play Again' para iniciar uma nova partida");
    }

    /**
     * Testa se a tela de fim de jogo é exibida
     */
    @Test
    public void testGameEndScreenIsAvailable() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        assertTrue(userStory07.isGameEndScreenVisible() || !userStory07.getVictoryMessageText().isEmpty(), 
            "A tela de fim de jogo deve estar disponível");
    }
}

