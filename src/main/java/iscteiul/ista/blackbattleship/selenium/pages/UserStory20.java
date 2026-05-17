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
 * Page Object Model for UserStoryTest20.
 *
 * <p>Represents the sound/interface settings scenario recorded in
 * {@code TestSuite_123010.side}.</p>
 */
public class UserStory20 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Settings button recorded by Selenium IDE.
     */
    @FindBy(xpath = "//button[.//mat-icon[normalize-space()='settings']] | //button[contains(@aria-label,'Settings')] | //button[contains(@class,'mat-mdc-icon-button')][.//span[contains(@class,'mat-mdc-button-touch-target')]]")
    private WebElement recordedSettingsButton;

    /**
     * Consent button that can block the first click in the page.
     */
    @FindBy(xpath = "//*[normalize-space()='Consent' or normalize-space()='CONSENT']")
    private WebElement consentButton;

    /**
     * Sound switch ripple recorded by Selenium IDE.
     */
    @FindBy(css = "#settings-sound-button .mdc-switch__ripple")
    private WebElement soundSwitch;

    /**
     * Sound switch button.
     */
    @FindBy(id = "settings-sound-button")
    private WebElement soundButton;

    /**
     * Creates the Page Object.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory20(WebDriver driver) {
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
     * Opens the settings menu.
     */
    public void openSettings() {
        click(wait.until(ExpectedConditions.elementToBeClickable(recordedSettingsButton)));
        wait.until(ExpectedConditions.visibilityOf(soundButton));
    }

    /**
     * Toggles the sound setting.
     */
    public void toggleSound() {
        click(wait.until(ExpectedConditions.elementToBeClickable(soundSwitch)));
    }

    /**
     * Checks if the sound setting control is visible.
     *
     * @return true if the sound button is displayed.
     */
    public boolean isSoundSettingVisible() {
        return wait.until(ExpectedConditions.visibilityOf(soundButton)).isDisplayed();
    }

    /**
     * Reads the sound setting state exposed by the ARIA attributes.
     *
     * @return current ARIA checked value.
     */
    public String getSoundState() {
        String checked = soundButton.getAttribute("aria-checked");
        if (checked == null) {
            checked = soundButton.getAttribute("aria-pressed");
        }
        return checked;
    }

    private void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
