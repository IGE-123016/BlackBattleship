package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

/**
 * Base Page Object for the IGE-123010 Selenide test suite.
 *
 * <p>Common browser interactions are kept here so each user-story page object
 * only contains the locators and operations specific to its scenario.</p>
 */
public abstract class BaseSelenidePage {

    /**
     * Opens the Papergames Battleship page and handles the consent banner.
     */
    @Step("Open Battleship page")
    public void openBattleshipPage() {
        open("https://papergames.io/en/battleship");
        acceptConsentIfPresent();
    }

    /**
     * Accepts the consent banner when it is visible.
     */
    @Step("Accept consent banner if present")
    public void acceptConsentIfPresent() {
        long limit = System.currentTimeMillis() + 4000;
        while (System.currentTimeMillis() < limit) {
            if (tryAcceptConsent()) {
                return;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private boolean tryAcceptConsent() {
        Boolean closed = executeJavaScript(
                "const isVisible = el => {" +
                        "const style = window.getComputedStyle(el);" +
                        "const rect = el.getBoundingClientRect();" +
                        "return style.display !== 'none' && style.visibility !== 'hidden' && rect.width > 0 && rect.height > 0;" +
                        "};" +
                        "const matches = el => {" +
                        "const text = (el.innerText || el.textContent || '').trim().toLowerCase();" +
                        "const aria = (el.getAttribute('aria-label') || '').trim().toLowerCase();" +
                        "return el.classList.contains('fc-cta-consent')" +
                        " || el.classList.contains('fc-vendor-preferences-accept-all')" +
                        " || el.classList.contains('fc-confirm-choices')" +
                        " || text === 'consent'" +
                        " || text === 'accept all'" +
                        " || text === 'confirm choices'" +
                        " || text === 'i agree'" +
                        " || text === 'ok'" +
                        " || aria === 'consent'" +
                        " || aria === 'accept all'" +
                        " || aria === 'confirm choices';" +
                        "};" +
                        "const button = Array.from(document.querySelectorAll('button,[role=\"button\"]')).find(el => isVisible(el) && matches(el));" +
                        "if (button) { button.click(); return true; }" +
                        "return false;");
        return Boolean.TRUE.equals(closed);
    }

    /**
     * Clicks an element after removing any consent overlay.
     *
     * @param element element to click.
     */
    protected void clickAfterConsent(SelenideElement element) {
        tryAcceptConsent();
        element.shouldBe(visible, Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
        tryAcceptConsent();
    }
}
