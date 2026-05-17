package iscteiul.ista.blackbattleship.selenide123010.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.selenide123010.pages.UserStory24Page;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenide test for US24 - app store redirection.
 */
@Epic("BlackBattleship")
@Feature("US24 - Redirecionamento para App Stores")
public class UserStory24SelenideTest extends SelenideSuite123010BaseTest {

    /**
     * Validates that the mobile app badge opens an app store page.
     */
    @Test
    @Story("Open app store")
    @Description("Clicks the mobile app badge and validates the external app store URL.")
    public void testUserStory24() throws InterruptedException {
        UserStory24Page appStorePage = page(UserStory24Page.class);

        appStorePage.openBattleshipPage();
        appStorePage.acceptConsentIfPresent();
        appStorePage.openAppStoreLink();
        Thread.sleep(2000);

        assertTrue(appStorePage.isAppStorePageOpen(),
                "Clicking the mobile app badge should open an app store page.");
    }
}
