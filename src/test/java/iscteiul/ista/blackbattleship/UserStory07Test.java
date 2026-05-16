package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * UserStory07Test - Testes JUnit para a User Story 07.
 *
 * User Story:
 * Como jogador, quero que o jogo disponibilize uma condição de fim de partida
 * e mecanismos de estado/reinício, para concluir ou reiniciar uma partida.
 *
 * Estes testes seguem o padrão Page Object Model: os localizadores e operações
 * Selenium estão na classe UserStory07, enquanto esta classe contém apenas
 * asserções JUnit.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory07Test {

    private WebDriver driver;
    private UserStory07 userStory07;

    /**
     * Inicializa o ChromeDriver antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://papergames.io/en/battleship");

        userStory07 = new UserStory07(driver);
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
     * Testa se a página de jogo foi carregada com sucesso.
     */
    @Test
    public void testGamePageLoads() {
        assertTrue(userStory07.isGamePageLoaded(),
                "A página do jogo deve estar completamente carregada.");
    }

    /**
     * Testa se existe uma área jogável onde a partida pode decorrer.
     */
    @Test
    public void testGameAreaIsAvailable() {
        assertTrue(userStory07.isGameAreaAvailable(),
                "A área/tabuleiro de jogo deve estar disponível.");
    }

    /**
     * Testa se existem controlos ou elementos interativos para jogar.
     */
    @Test
    public void testPlayableControlsAreAvailable() {
        assertTrue(userStory07.hasPlayableControls(),
                "A página deve disponibilizar controlos ou elementos interativos de jogo.");
    }

    /**
     * Testa se a página apresenta informação de estado, necessária para comunicar
     * progresso, resultado ou fim da partida.
     */
    @Test
    public void testGameStatusInformationIsAvailable() {
        assertTrue(userStory07.hasGameStatusInformation(),
                "A página deve apresentar informação de estado da partida.");
    }

    /**
     * Testa se a página contém indícios funcionais associados a jogar,
     * terminar ou reiniciar uma partida.
     */
    @Test
    public void testEndGameOrRestartCapabilityExists() {
        assertTrue(userStory07.hasEndGameOrRestartCapability(),
                "A página deve conter elementos/textos associados a jogar, terminar ou reiniciar uma partida.");
    }
}