package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ClickOptions;

public class SelenideUserStory9Page {
    private final SelenideElement friendsTab = $(
            By.xpath("//a[contains(text(), 'Friends') or contains(@href, '/en/friends')]"));

    public void clickFriendsTab() {
        friendsTab.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }
}
