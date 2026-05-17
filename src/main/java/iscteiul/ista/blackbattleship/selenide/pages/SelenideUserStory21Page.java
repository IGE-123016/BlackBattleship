package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideUserStory21Page {

    private final SelenideElement changelogLink =
            $(By.linkText("Changelog"));

    public void clickChangelog() {

        executeJavaScript("window.scrollTo(0, document.body.scrollHeight)");

        changelogLink
                .shouldBe(com.codeborne.selenide.Condition.visible,
                        java.time.Duration.ofSeconds(10))
                .click(ClickOptions.usingJavaScript());
    }
}