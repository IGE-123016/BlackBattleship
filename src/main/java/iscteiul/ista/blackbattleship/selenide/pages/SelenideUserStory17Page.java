package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ClickOptions;

public class SelenideUserStory17Page {
    private final SelenideElement historyTab = $(
            By.xpath("//a[contains(text(), 'History') or contains(@href, '/en/match-history')]"));

    public void clickHistoryTab() {
        historyTab.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }
}
