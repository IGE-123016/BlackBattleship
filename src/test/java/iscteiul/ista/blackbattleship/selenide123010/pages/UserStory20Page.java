package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object for US20 - sound and interface settings.
 */
public class UserStory20Page extends BaseSelenidePage {

    private final ElementsCollection settingsButtons =
            $$x("//button[.//*[name()='svg' and @data-icon='gear']]");
    private final SelenideElement soundButton = $("#settings-sound-button");
    private final SelenideElement soundSwitch = $("#settings-sound-button .mdc-switch__ripple");

    /**
     * Opens the Battleship page.
     */
    @Step("Open Battleship page")
    public void openBattleshipPage() {
        open("https://papergames.io/en/battleship");
    }

    /**
     * Opens the settings panel.
     */
    @Step("Open settings panel")
    public void openSettings() {
        settingsButtons.findBy(visible)
                .click(ClickOptions.usingJavaScript());
        soundButton.shouldBe(visible, Duration.ofSeconds(15));
    }

    /**
     * Toggles the sound setting.
     */
    @Step("Toggle sound setting")
    public void toggleSound() {
        soundSwitch.shouldBe(visible, Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }

    /**
     * Reads the current sound state from accessibility attributes.
     *
     * @return current sound state.
     */
    @Step("Read sound setting state")
    public String getSoundState() {
        String checked = soundButton.getAttribute("aria-checked");
        if (checked == null) {
            checked = soundButton.getAttribute("aria-pressed");
        }
        return checked;
    }

    /**
     * Checks whether the sound control is displayed.
     *
     * @return true if the control is visible.
     */
    @Step("Validate sound setting visibility")
    public boolean isSoundSettingVisible() {
        return soundButton.shouldBe(visible, Duration.ofSeconds(15)).isDisplayed();
    }
}
