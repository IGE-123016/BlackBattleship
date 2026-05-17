package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class SelenideUserStory18Page {

    private final SelenideElement strategiesSection =
            $(By.xpath("//*[contains(text(),'Strategies and Tactics')]"));

    public boolean isStrategiesSectionVisible() {

        strategiesSection.scrollIntoView(true);

        return strategiesSection
                .shouldBe(com.codeborne.selenide.Condition.visible,
                        java.time.Duration.ofSeconds(10))
                .isDisplayed();
    }
}