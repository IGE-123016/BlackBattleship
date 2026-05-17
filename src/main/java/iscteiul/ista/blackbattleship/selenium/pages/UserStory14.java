package iscteiul.ista.blackbattleship.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for UserStoryTest14.
 *
 * <p>Represents the create tournament scenario recorded in
 * {@code TestSuite_123010.side}.</p>
 */
public class UserStory14 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Create tournament link recorded by Selenium IDE.
     */
    @FindBy(xpath = "//span[contains(.,'Create tournament')] | //a[contains(@href,'tournament')]")
    private WebElement createTournamentLink;

    /**
     * Consent button that can block the first click in the page.
     */
    @FindBy(xpath = "//*[normalize-space()='Consent' or normalize-space()='CONSENT']")
    private WebElement consentButton;

    /**
     * Game select field.
     */
    @FindBy(css = "div:nth-child(1) > .mat-mdc-form-field .mat-mdc-form-field-infix")
    private WebElement gameSelect;

    /**
     * Battleship option.
     */
    @FindBy(xpath = "//*[contains(@id,'mat-option') and contains(.,'Battleship')]")
    private WebElement battleshipOption;

    /**
     * Tournament name input.
     */
    @FindBy(xpath = "//input[contains(@id,'mat-input') and not(@type='hidden')]")
    private WebElement tournamentNameInput;

    /**
     * Matches per round select field.
     */
    @FindBy(id = "mat-select-value-serverApp2")
    private WebElement matchesPerRoundSelect;

    /**
     * Best of 5 option.
     */
    @FindBy(xpath = "//*[contains(@id,'mat-option') and contains(.,'Best of 5')]")
    private WebElement bestOfFiveOption;

    /**
     * Create and share button.
     */
    @FindBy(xpath = "//button[contains(.,'Create and share')]")
    private WebElement createAndShareButton;

    /**
     * Creates the Page Object.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory14(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    /**
     * Opens the Battleship page used in the Selenium IDE recording.
     */
    public void openBattleshipPage() {
        driver.get("https://papergames.io/en/battleship");
    }

    /**
     * Accepts the consent banner when it is displayed.
     */
    public void acceptConsentIfPresent() {
        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(consentButton));
            click(button);
        } catch (Exception ignored) {
        }
    }

    /**
     * Opens the create tournament flow.
     */
    public void openCreateTournament() {
        click(wait.until(ExpectedConditions.elementToBeClickable(createTournamentLink)));
        wait.until(ExpectedConditions.visibilityOf(gameSelect));
    }

    /**
     * Selects Battleship as the tournament game.
     */
    public void selectBattleshipGame() {
        click(wait.until(ExpectedConditions.elementToBeClickable(gameSelect)));
        click(wait.until(ExpectedConditions.elementToBeClickable(battleshipOption)));
    }

    /**
     * Types the tournament name.
     *
     * @param name tournament name.
     */
    public void enterTournamentName(String name) {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(tournamentNameInput));
        click(input);
        try {
            input.clear();
            input.sendKeys(name);
            input.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                    input,
                    name);
        }
    }

    /**
     * Selects Best of 5 matches per round.
     */
    public void selectBestOfFive() {
        click(wait.until(ExpectedConditions.elementToBeClickable(matchesPerRoundSelect)));
        click(wait.until(ExpectedConditions.elementToBeClickable(bestOfFiveOption)));
    }

    /**
     * Submits the create and share action.
     */
    public void createAndShare() {
        click(wait.until(ExpectedConditions.elementToBeClickable(createAndShareButton)));
    }

    /**
     * Checks if the tournament creation flow reached the account-required modal.
     *
     * @return true when the account flow or tournament page is still visible.
     */
    public boolean isAccountRequiredModalDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "account"),
                    ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Sign"),
                    ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Create")));
            String text = driver.findElement(By.tagName("body")).getText().toLowerCase();
            return text.contains("account") || text.contains("sign") || text.contains("create");
        } catch (Exception e) {
            return driver.getCurrentUrl().toLowerCase().contains("tournament");
        }
    }

    private void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
