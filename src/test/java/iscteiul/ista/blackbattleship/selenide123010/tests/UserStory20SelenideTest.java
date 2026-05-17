package iscteiul.ista.blackbattleship.selenide123010.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.selenide123010.pages.UserStory20Page;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenide test for US20 - sound/interface settings.
 */
@Epic("BlackBattleship")
@Feature("US20 - Definicoes de Som e Interface")
public class UserStory20SelenideTest extends SelenideSuite123010BaseTest {

    /**
     * Validates the sound setting can be toggled and restored.
     */
    @Test
    @Story("Toggle sound setting")
    @Description("Opens settings, disables sound, then enables it again.")
    public void testUserStory20() throws InterruptedException {
        UserStory20Page settingsPage = page(UserStory20Page.class);

        settingsPage.openBattleshipPage();
        settingsPage.acceptConsentIfPresent();
        settingsPage.openSettings();
        Thread.sleep(1000);

        assertTrue(settingsPage.isSoundSettingVisible(),
                "The sound setting should be visible.");

        String initialState = settingsPage.getSoundState();
        settingsPage.toggleSound();
        Thread.sleep(1000);

        String changedState = settingsPage.getSoundState();
        assertNotEquals(initialState, changedState,
                "Toggling sound should change the sound setting state.");

        settingsPage.toggleSound();
        Thread.sleep(1000);
    }
}
