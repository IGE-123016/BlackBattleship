package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * UserStory10Test - Testes JUnit para a User Story 10.
 *
 * User Story:
 * Como utilizador, quero que a página disponibilize mecanismos de interação
 * compatíveis com convite, partilha ou criação de uma partida online com outro
 * jogador.
 *
 * Estes testes seguem o padrão Page Object Model: os localizadores e operações
 * Selenium estão na classe UserStory10, enquanto esta classe contém apenas
 * asserções JUnit.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory10Test {

    private WebDriver driver;
    private UserStory10 userStory10;

    /**
     * Inicializa o ChromeDriver antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://papergames.io/en/battleship");

        userStory10 = new UserStory10(driver);
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
     * Testa se a página foi carregada com sucesso.
     */
    @Test
    public void testPageLoads() {
        assertTrue(userStory10.isPageLoaded(),
                "A página deve estar completamente carregada.");
    }

    /**
     * Testa se a URL corresponde à página de batalha naval online.
     */
    @Test
    public void testBattleshipPageUrlIsCorrect() {
        assertTrue(userStory10.isBattleshipPageUrl(),
                "A página atual deve corresponder ao jogo Battleship online.");
    }

    /**
     * Testa se existem elementos interativos na página.
     */
    @Test
    public void testInteractionElementsExist() {
        assertTrue(userStory10.hasInteractionElements(),
                "A página deve apresentar elementos interativos.");
    }

    /**
     * Testa se a página contém texto ou contexto associado a jogo online,
     * convite, partilha ou interação entre jogadores.
     */
    @Test
    public void testInviteOrShareContextExists() {
        assertTrue(userStory10.hasInviteOrShareText(),
                "A página deve conter contexto textual associado a convite, partilha ou jogo online.");
    }

    /**
     * Testa se existem botões ou links de ação/navegação.
     */
    @Test
    public void testNavigationOrActionControlsExist() {
        assertTrue(userStory10.hasNavigationOrActionControls(),
                "A página deve apresentar botões ou links de ação.");
    }
}