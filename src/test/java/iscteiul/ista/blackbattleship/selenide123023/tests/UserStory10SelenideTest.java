package iscteiul.ista.blackbattleship.selenide123023.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import iscteiul.ista.blackbattleship.selenide123023.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes Selenide para a US10 - Link de Convite para Amigos.
 *
 * @author Rodrigo Sampaio
 */
@Epic("BlackBattleship")
@Feature("US10 - Link de Convite para Amigos")
public class UserStory10SelenideTest {

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
    @Story("Contexto de convite ou partilha")
    @Description("Valida se a página contém contexto textual associado a convite, partilha ou jogo online.")
    public void testInviteOrShareContextExists() {
        assertTrue(homePage.hasInviteOrShareContext(),
                "A página deve conter contexto associado a convite, partilha ou jogo online.");
    }

    @Test
    @Story("Controlos de navegação ou ação")
    @Description("Valida se existem botões, links ou elementos interativos para criar/interagir com uma partida.")
    public void testActionControlsExist() {
        assertTrue(homePage.hasInteractionElements(),
                "A página deve apresentar controlos de ação ou navegação.");
    }
}