package iscteiul.ista.blackbattleship.selenium.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page Object Model for UserStoryTest24.
 *
 * <p>Represents the mobile app store redirection scenario recorded in
 * {@code TestSuite_123010.side}.</p>
 */
public class UserStory24 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Play Store badge recorded by Selenium IDE.
     */
    @FindBy(xpath = "//img[@alt='Get it on Playstore'] | //img[contains(@alt,'Playstore')]")
    private WebElement playStoreBadge;

    /**
     * Creates the Page Object.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory24(WebDriver driver) {
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
     * Clicks the Play Store badge and switches to the opened app-store window
     * when the site opens one.
     */
    public void openPlayStoreLink() {
        String currentWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();

        click(wait.until(ExpectedConditions.elementToBeClickable(playStoreBadge)));

        try {
            wait.until(ExpectedConditions.numberOfWindowsToBe(oldWindows.size() + 1));
            for (String window : driver.getWindowHandles()) {
                if (!oldWindows.contains(window)) {
                    driver.switchTo().window(window);
                    return;
                }
            }
        } catch (Exception ignored) {
            driver.switchTo().window(currentWindow);
        }
    }

    /**
     * Checks whether the user reached an app store page.
     *
     * @return true when the current URL points to a supported app store.
     */
    public boolean isAppStorePageOpen() {
        String currentUrl = driver.getCurrentUrl().toLowerCase();
        return currentUrl.contains("play.google.com")
                || currentUrl.contains("apps.apple.com")
                || currentUrl.contains("appgallery")
                || currentUrl.contains("store");
    }

    private void click(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
