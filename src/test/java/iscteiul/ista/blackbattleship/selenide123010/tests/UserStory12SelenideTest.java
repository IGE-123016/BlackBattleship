package iscteiul.ista.blackbattleship.selenide123010.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import iscteiul.ista.blackbattleship.selenide123010.pages.UserStory12Page;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.page;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Selenide test for US12 - shop category filtering.
 */
@Epic("BlackBattleship")
@Feature("US12 - Filtro de Categorias na Loja")
public class UserStory12SelenideTest extends SelenideSuite123010BaseTest {

    /**
     * Validates category filtering in the shop.
     */
    @Test
    @Story("Shop category filtering")
    @Description("Opens the shop, selects Monsters/avatars and Emojis categories.")
    public void testUserStory12() throws InterruptedException {
        UserStory12Page shopPage = page(UserStory12Page.class);

        shopPage.openBattleshipPage();
        shopPage.acceptConsentIfPresent();
        shopPage.openShop();
        Thread.sleep(1000);

        shopPage.selectMonstersCategory();
        Thread.sleep(1000);
        assertTrue(shopPage.isCategorySelected("avatars"),
                "Monsters/avatars category should be selected.");

        shopPage.openShop();
        Thread.sleep(1000);

        shopPage.selectEmojisCategory();
        Thread.sleep(1000);
        assertTrue(shopPage.isCategorySelected("emojis"),
                "Emojis category should be selected.");
    }
}
