package iscteiul.ista.blackbattleship.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for UserStoryTest12.
 *
 * <p>Represents the shop category filtering scenario recorded in
 * {@code TestSuite_123010.side}: open the shop, select Monsters, return to
 * the shop and select Emojis.</p>
 */
public class UserStory12 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Shop menu link recorded as {@code linkText=Shop}.
     */
    @FindBy(linkText = "Shop")
    private WebElement shopLink;

    /**
     * Monsters category image recorded by Selenium IDE.
     */
    @FindBy(xpath = "//img[@alt='Monsters'] | //a[contains(@href, '/shop/avatars')]")
    private WebElement monstersCategory;

    /**
     * Emojis category image recorded by Selenium IDE.
     */
    @FindBy(xpath = "//img[@alt='Emojis'] | //a[contains(@href, '/shop/emojis')]")
    private WebElement emojisCategory;

    /**
     * Creates the Page Object.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory12(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the base Battleship page used in the Selenium IDE recording.
     */
    public void openBattleshipPage() {
        driver.get("https://papergames.io/en/battleship");
    }

    /**
     * Opens the shop page through the navigation menu.
     */
    public void openShop() {
        click(wait.until(ExpectedConditions.elementToBeClickable(shopLink)));
        wait.until(ExpectedConditions.urlContains("/shop"));
    }

    /**
     * Selects the Monsters category in the shop.
     */
    public void selectMonstersCategory() {
        click(wait.until(ExpectedConditions.elementToBeClickable(monstersCategory)));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("avatars"),
                ExpectedConditions.attributeContains(monstersCategory, "alt", "Monsters")));
    }

    /**
     * Selects the Emojis category in the shop.
     */
    public void selectEmojisCategory() {
        click(wait.until(ExpectedConditions.elementToBeClickable(emojisCategory)));
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("emojis"),
                ExpectedConditions.attributeContains(emojisCategory, "alt", "Emojis")));
    }

    /**
     * Checks if the current page is related to the shop.
     *
     * @return true when the current URL contains the shop path.
     */
    public boolean isShopPageOpen() {
        return driver.getCurrentUrl().contains("/shop");
    }

    /**
     * Checks if the selected category can be identified in the current URL.
     *
     * @param category category fragment expected in the URL.
     * @return true if the current URL contains the category fragment.
     */
    public boolean isCategorySelected(String category) {
        return driver.getCurrentUrl().toLowerCase().contains(category.toLowerCase());
    }

    private void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
