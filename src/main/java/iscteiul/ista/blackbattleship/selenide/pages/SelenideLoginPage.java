package iscteiul.ista.blackbattleship.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.ClickOptions;

public class SelenideLoginPage {

    private final SelenideElement playVsRobotBtn = $(By.xpath("//span[contains(text(), 'Play vs robot')] | //button[contains(., 'Play vs robot')]"));
    private final SelenideElement nicknameInput = $(By.cssSelector("input.input-xl, input[type='text']"));
    private final SelenideElement continueBtn = $(By.xpath("//button[contains(., 'Continue')]"));
    private final SelenideElement abortGameBtn = $(By.xpath("//button[contains(., 'Abort game')]"));
    private final SelenideElement confirmAbortBtn = $(By.cssSelector("button.btn-danger"));

    public void clickPlayVsRobot() {
        playVsRobotBtn.shouldBe(com.codeborne.selenide.Condition.interactable, java.time.Duration.ofSeconds(15)).click(ClickOptions.usingJavaScript());
    }

    public void enterNickname(String nickname) {
        try {
            nicknameInput.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(5));
        } catch (Throwable t) {
            clickPlayVsRobot();
            nicknameInput.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(10));
        }
        nicknameInput.setValue(nickname);
    }

    public void clickContinue() {
        continueBtn.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15)).click(ClickOptions.usingJavaScript());
    }

    public void abortGame() {
        abortGameBtn.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(30)).click(ClickOptions.usingJavaScript());
    }

    public void confirmAbort() {
        confirmAbortBtn.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(15)).click(ClickOptions.usingJavaScript());
    }

    public void acceptConsentIfPresent() {
        SelenideElement consentBtn = $(By.xpath("//*[normalize-space(text())='Consent' or normalize-space(text())='CONSENT']"));
        try {
            consentBtn.shouldBe(com.codeborne.selenide.Condition.visible, java.time.Duration.ofSeconds(5));
            consentBtn.click(ClickOptions.usingJavaScript());
            $("div.fc-dialog-overlay").should(com.codeborne.selenide.Condition.disappear, java.time.Duration.ofSeconds(10));
        } catch (Throwable e) {
            // ignore if banner is not shown
        }
    }

    public void createGuestSession(String nickname) {
        acceptConsentIfPresent();
        // Wait briefly for Angular event listeners to attach
        try { Thread.sleep(3000); } catch (InterruptedException e) {}
        clickPlayVsRobot();
        
        long startTime = System.currentTimeMillis();
        boolean needsNickname = false;
        boolean clickedAgain = false;
        
        while (System.currentTimeMillis() - startTime < 15000) {
            if (nicknameInput.exists() && nicknameInput.isDisplayed()) {
                needsNickname = true;
                break;
            }
            if (abortGameBtn.exists() && abortGameBtn.isDisplayed()) {
                needsNickname = false;
                break;
            }
            // Retry click if nothing happened after 5 seconds
            if (!clickedAgain && (System.currentTimeMillis() - startTime > 5000)) {
                clickPlayVsRobot();
                clickedAgain = true;
            }
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }

        if (needsNickname) {
            String uniqueNickname = nickname + (System.currentTimeMillis() % 10000);
            try {
                nicknameInput.shouldBe(com.codeborne.selenide.Condition.interactable, java.time.Duration.ofSeconds(10)).clear();
                nicknameInput.setValue(uniqueNickname);
            } catch (Throwable t) {
                // ignore
            }
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            try {
                clickContinue();
            } catch (Throwable t) {
                // ignore
            }
        }

        abortGame();
        confirmAbort();
        // Wait for redirect back to main page
        com.codeborne.selenide.Selenide.open("https://papergames.io/en/battleship"); try { Thread.sleep(3000); } catch (InterruptedException e) {}
        acceptConsentIfPresent();
    }
}
