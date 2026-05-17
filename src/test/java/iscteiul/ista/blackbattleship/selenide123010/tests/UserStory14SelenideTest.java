package iscteiul.ista.blackbattleship.selenide123010.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.selenide123010.pages.UserStory14Page;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenide test for US14 - tournament creation.
 */
@Epic("BlackBattleship")
@Feature("US14 - Criacao de Torneios")
public class UserStory14SelenideTest extends SelenideSuite123010BaseTest {

    /**
     * Validates the tournament creation form can be filled and submitted.
     */
    @Test
    @Story("Fill tournament creation details")
    @Description("Opens the tournament creation flow, selects Battleship, fills the name, selects Best of 5 and submits.")
    public void testUserStory14() throws InterruptedException {
        UserStory14Page tournamentPage = page(UserStory14Page.class);

        tournamentPage.openBattleshipPage();
        tournamentPage.acceptConsentIfPresent();
        tournamentPage.openCreateTournament();
        Thread.sleep(1000);

        tournamentPage.selectBattleshipGame();
        Thread.sleep(1000);

        tournamentPage.enterTournamentName("TORNEIO TESTE");
        Thread.sleep(1000);

        tournamentPage.selectBestOfFive();
        Thread.sleep(1000);

        tournamentPage.createAndShare();
        Thread.sleep(1000);

        assertTrue(tournamentPage.isCreationFlowHandled(),
                "Creating and sharing a tournament should reach the expected account/tournament flow.");
    }
}
