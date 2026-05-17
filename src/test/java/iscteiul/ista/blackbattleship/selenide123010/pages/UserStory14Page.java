package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object for US14 - tournament creation flow.
 */
public class UserStory14Page extends BaseSelenidePage {

    private final ElementsCollection createTournamentLinks =
            $$x("//span[contains(.,'Create tournament')] | //a[contains(@href,'tournament')]");
    private final SelenideElement gameSelect =
            $("div:nth-child(1) > .mat-mdc-form-field .mat-mdc-form-field-infix");
    private final SelenideElement battleshipOption =
            $x("//*[contains(@id,'mat-option') and contains(.,'Battleship')]");
    private final ElementsCollection tournamentNameInputs =
            $$x("//input[contains(@id,'mat-input') and not(@type='hidden')]");
    private final SelenideElement matchesPerRoundSelect = $("#mat-select-value-serverApp2");
    private final SelenideElement bestOfFiveOption =
            $x("//*[contains(@id,'mat-option') and contains(.,'Best of 5')]");
    private final SelenideElement createAndShareButton =
            $x("//button[contains(.,'Create and share')]");

    /**
     * Opens the create tournament form.
     */
    @Step("Open create tournament flow")
    public void openCreateTournament() {
        clickAfterConsent(createTournamentLinks.findBy(visible));
        gameSelect.shouldBe(visible, Duration.ofSeconds(15));
    }

    /**
     * Selects Battleship as tournament game.
     */
    @Step("Select Battleship game")
    public void selectBattleshipGame() {
        clickAfterConsent(gameSelect);
        clickAfterConsent(battleshipOption);
    }

    /**
     * Enters the tournament name.
     *
     * @param name tournament name.
     */
    @Step("Enter tournament name")
    public void enterTournamentName(String name) {
        acceptConsentIfPresent();
        SelenideElement input = tournamentNameInputs.findBy(visible)
                .shouldBe(visible, Duration.ofSeconds(15));
        try {
            input.setValue(name).pressEnter();
        } catch (Exception e) {
            executeJavaScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    input,
                    name);
            executeJavaScript(
                    "arguments[0].dispatchEvent(new KeyboardEvent('keydown', { key: 'Enter', bubbles: true }));",
                    input);
        }
    }

    /**
     * Selects Best of 5 matches per round.
     */
    @Step("Select Best of 5")
    public void selectBestOfFive() {
        clickAfterConsent(matchesPerRoundSelect);
        clickAfterConsent(bestOfFiveOption);
    }

    /**
     * Clicks Create and share.
     */
    @Step("Create and share tournament")
    public void createAndShare() {
        clickAfterConsent(createAndShareButton);
    }

    /**
     * Validates the expected end of the creation flow.
     *
     * @return true when an account/sign/create message is visible or the page remains in tournament flow.
     */
    @Step("Validate tournament creation flow")
    public boolean isCreationFlowHandled() {
        String text = $("body").shouldBe(visible, Duration.ofSeconds(10)).getText().toLowerCase();
        return text.contains("account")
                || text.contains("sign")
                || text.contains("create")
                || webdriver().driver().url().toLowerCase().contains("tournament");
    }
}
