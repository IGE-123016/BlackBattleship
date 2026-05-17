package iscteiul.ista.blackbattleship.selenide123023.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import iscteiul.ista.blackbattleship.selenide123023.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes Selenide para a US02 - Regra de Disparo Consecutivo.
 *
 * @author Rodrigo Sampaio
 */
@Epic("BlackBattleship")
@Feature("US02 - Regra de Disparo Consecutivo")
public class UserStory02SelenideTest {

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
    @Story("Suporte a interação de disparo")
    @Description("Valida se a página apresenta elementos compatíveis com interação de disparo/jogada.")
    public void testShootingInteractionSupportExists() {
        assertTrue(homePage.hasShootingInteractionSupport(),
                "A página deve permitir interação compatível com disparo ou jogada.");
    }

    @Test
    @Story("Elementos interativos da partida")
    @Description("Valida se existem elementos interativos necessários para realizar jogadas.")
    public void testInteractionElementsExist() {
        assertTrue(homePage.hasInteractionElements(),
                "A página deve apresentar elementos interativos para o jogador.");
    }
}