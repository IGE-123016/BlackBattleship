package iscteiul.ista.blackbattleship.selenide123010.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Base Page Object for the IGE-123010 Selenide test suite.
 *
 * <p>Common browser interactions are kept here so each user-story page object
 * only contains the locators and operations specific to its scenario.</p>
 */
public abstract class BaseSelenidePage {

    /**
     * Optional consent button displayed by Papergames.
     */
    protected final SelenideElement consentButton =
            $x("//*[normalize-space()='Consent' or normalize-space()='CONSENT']");

    /**
     * Accepts the consent banner when it is visible.
     */
    @Step("Accept consent banner if present")
    public void acceptConsentIfPresent() {
        if (consentButton.exists()) {
            consentButton.shouldBe(visible, Duration.ofSeconds(5))
                    .click(ClickOptions.usingJavaScript());
        }
    }
}
