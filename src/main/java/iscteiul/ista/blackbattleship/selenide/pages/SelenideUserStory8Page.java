package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import com.codeborne.selenide.ClickOptions;

import static com.codeborne.selenide.Selenide.$;

public class SelenideUserStory8Page {
    private final SelenideElement messagingTab = $(By.xpath("//*[contains(text(), 'Messaging') or contains(@href, '/en/chat')]"));

    public void clickMessagingTab() {
        messagingTab.shouldBe(com.codeborne.selenide.Condition.interactable, java.time.Duration.ofSeconds(15)).click(ClickOptions.usingJavaScript());
    }
}
