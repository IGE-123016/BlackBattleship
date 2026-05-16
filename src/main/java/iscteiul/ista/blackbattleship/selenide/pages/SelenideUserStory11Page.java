package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ClickOptions;

public class SelenideUserStory11Page {
    private final SelenideElement shopTab = $(By.xpath("//a[contains(text(), 'Shop') or contains(@href, '/en/shop')]"));
    private final SelenideElement monstersCategory = $(
            By.xpath("//img[@alt='Monsters'] | //div[contains(@class,'w-75')]//img[contains(@src,'monsters')]"));
    private final SelenideElement emojisCategory = $(
            By.xpath("//img[@alt='Emojis'] | //div[contains(@class,'w-75')]//img[contains(@src,'emojis')]"));

    public void clickShopTab() {
        shopTab.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }

    public void clickMonstersCategory() {
        monstersCategory.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }

    public void clickEmojisCategory() {
        emojisCategory.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15))
                .click(ClickOptions.usingJavaScript());
    }
}
