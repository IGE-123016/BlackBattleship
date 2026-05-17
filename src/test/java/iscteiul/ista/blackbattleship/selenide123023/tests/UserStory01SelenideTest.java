package iscteiul.ista.blackbattleship.selenide123023.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import iscteiul.ista.blackbattleship.selenide123023.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes Selenide para a US01 - Geração Aleatória de Frota.
 *
 * @author Rodrigo Sampaio
 */
@Epic("BlackBattleship")
@Feature("US01 - Geração Aleatória de Frota")
public class UserStory01SelenideTest {

    private BattleshipHomePage homePage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";

        homePage = page(BattleshipHomePage.class);
        homePage.openPage();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    @Test
    @Story("Carregamento da página do jogo")
    @Description("Valida se a página do Battleship carrega corretamente.")
    public void testGamePageLoads() {
        assertTrue(homePage.isLoaded(), "A página do jogo deve carregar corretamente.");
    }

    @Test
    @Story("Área inicial de jogo")
    @Description("Valida se existe uma área de jogo ou tabuleiro visível.")
    public void testGameAreaIsVisible() {
        assertTrue(homePage.hasVisibleGameArea(), "A área/tabuleiro de jogo deve estar visível.");
    }

    @Test
    @Story("Estrutura inicial da frota")
    @Description("Valida se a página apresenta estrutura compatível com geração inicial da frota.")
    public void testInitialFleetStructureExists() {
        assertTrue(homePage.hasInitialFleetStructure(),
                "A página deve apresentar estrutura compatível com frota inicial.");
    }
}