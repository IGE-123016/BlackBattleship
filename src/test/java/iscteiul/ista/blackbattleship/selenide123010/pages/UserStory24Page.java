package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object for US24 - redirection to app stores.
 */
public class UserStory24Page extends BaseSelenidePage {

    private final SelenideElement playStoreBadge =
            $x("//img[@alt='Get it on Playstore'] | //img[contains(@alt,'Playstore')]");

    /**
     * Opens the Battleship page.
     */
    @Step("Open Battleship page")
    public void openBattleshipPage() {
        open("https://papergames.io/en/battleship");
    }

    /**
     * Opens the mobile app store link and switches to the new window.
     */
    @Step("Open app store link")
    public void openAppStoreLink() {
        Set<String> previousWindows = WebDriverRunner.getWebDriver().getWindowHandles();
        playStoreBadge.shouldBe(visible, Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());

        Wait().until(driver -> driver.getWindowHandles().size() > previousWindows.size());
        for (String window : WebDriverRunner.getWebDriver().getWindowHandles()) {
            if (!previousWindows.contains(window)) {
                switchTo().window(window);
                return;
            }
        }
    }

    /**
     * Checks whether the current URL is an app store URL.
     *
     * @return true if a store page is open.
     */
    @Step("Validate app store redirection")
    public boolean isAppStorePageOpen() {
        String url = webdriver().driver().url().toLowerCase();
        return url.contains("play.google.com")
                || url.contains("apps.apple.com")
                || url.contains("appgallery")
                || url.contains("store");
    }
}
