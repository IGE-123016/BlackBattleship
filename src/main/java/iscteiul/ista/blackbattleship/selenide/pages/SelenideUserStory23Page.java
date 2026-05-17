package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideUserStory23Page {

    private final SelenideElement termsLink =
            $(By.partialLinkText("Terms"));

    public void clickTermsAndConditions() {

        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");

        termsLink
                .shouldBe(com.codeborne.selenide.Condition.visible,
                        java.time.Duration.ofSeconds(10))
                .click(ClickOptions.usingJavaScript());
    }
}