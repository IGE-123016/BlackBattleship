package iscteiul.ista.blackbattleship.selenide123023.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import iscteiul.ista.blackbattleship.selenide123023.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes Selenide para a US07 - Condição de Vitória Total.
 *
 * @author Rodrigo Sampaio
 */
@Epic("BlackBattleship")
@Feature("US07 - Condição de Vitória Total")
public class UserStory07SelenideTest {

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
    @Story("Informação de estado da partida")
    @Description("Valida se a página apresenta informação de estado necessária para comunicar progresso ou fim de jogo.")
    public void testGameStatusInformationExists() {
        assertTrue(homePage.hasGameStatusInformation(),
                "A página deve apresentar informação de estado da partida.");
    }

    @Test
    @Story("Capacidade de fim ou reinício")
    @Description("Valida se existem indícios funcionais associados a jogar, terminar ou reiniciar uma partida.")
    public void testEndGameOrRestartCapabilityExists() {
        assertTrue(homePage.hasEndGameOrRestartCapability(),
                "A página deve conter elementos/textos associados a fim ou reinício de jogo.");
    }
}