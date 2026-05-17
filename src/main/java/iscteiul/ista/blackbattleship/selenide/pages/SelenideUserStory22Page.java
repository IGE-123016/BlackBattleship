package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideUserStory22Page {

    private final SelenideElement privacyLink =
            $(By.partialLinkText("Privacy"));

    public void clickPrivacyPolicy() {

        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");

        privacyLink
                .shouldBe(com.codeborne.selenide.Condition.visible,
                        java.time.Duration.ofSeconds(10))
                .click(ClickOptions.usingJavaScript());
    }
}