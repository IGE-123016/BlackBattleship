package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * UserStory01Test - Testes JUnit para a User Story 01.
 *
 * User Story:
 * Como jogador, quero que o jogo disponibilize uma área/tabuleiro de jogo
 * e uma configuração inicial da partida, para poder iniciar rapidamente uma
 * partida de Batalha Naval.
 *
 * Estes testes seguem o padrão Page Object Model: os localizadores e ações
 * Selenium estão na classe UserStory01, enquanto esta classe contém apenas
 * os testes JUnit.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory01Test {

    private WebDriver driver;
    private UserStory01 userStory01;

    /**
     * Inicializa o ChromeDriver antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://papergames.io/en/battleship");

        userStory01 = new UserStory01(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Testa se a página do jogo carrega corretamente.
     */
    @Test
    public void testGamePageLoads() {
        assertTrue(userStory01.isGamePageLoaded(),
                "A página do jogo deve estar completamente carregada.");
    }

    /**
     * Testa se existe uma área/tabuleiro de jogo visível.
     */
    @Test
    public void testBoardIsVisibleAfterGameStart() {
        assertTrue(userStory01.isBoardVisible(),
                "O tabuleiro ou área de jogo deve estar visível.");
    }

    /**
     * Testa se a página apresenta uma estrutura jogável compatível com a colocação inicial da frota.
     */
    @Test
    public void testShipsAreRandomlyPlaced() {
        assertTrue(userStory01.areShipsPlaced(),
                "A página deve apresentar uma estrutura jogável compatível com a frota inicial.");
    }

    /**
     * Testa se existem controlos interativos para iniciar/configurar a partida.
     */
    @Test
    public void testGameInteractionControlsExist() {
        assertTrue(userStory01.hasGameInteractionControls(),
                "A página deve apresentar controlos interativos do jogo.");
    }
}