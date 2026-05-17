package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object for US12 - filtering shop items by category.
 */
public class UserStory12Page extends BaseSelenidePage {

    private final SelenideElement shopLink = $x("//a[contains(@href, '/en/shop') or contains(.,'Shop')]");
    private final SelenideElement monstersCategory = $x("//img[@alt='Monsters'] | //a[contains(@href, '/shop/avatars')]");
    private final SelenideElement emojisCategory = $x("//img[@alt='Emojis'] | //a[contains(@href, '/shop/emojis')]");

    /**
     * Opens the shop through the site navigation.
     */
    @Step("Open shop page")
    public void openShop() {
        clickAfterConsent(shopLink);
        webdriver().driver().url().contains("/shop");
    }

    /**
     * Selects the Monsters/avatars category.
     */
    @Step("Filter shop by Monsters category")
    public void selectMonstersCategory() {
        clickAfterConsent(monstersCategory);
    }

    /**
     * Selects the Emojis category.
     */
    @Step("Filter shop by Emojis category")
    public void selectEmojisCategory() {
        clickAfterConsent(emojisCategory);
    }

    /**
     * Checks if the current URL identifies the expected category.
     *
     * @param category expected URL fragment.
     * @return true if the current URL contains the category.
     */
    @Step("Validate selected shop category")
    public boolean isCategorySelected(String category) {
        return webdriver().driver().url().toLowerCase().contains(category.toLowerCase());
    }
}
