package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe Page Object Model para o cenário UserStory 17 (Histórico de Partidas).
 */
public class UserStory17 {

    @FindBy(xpath = "//*[contains(text(), 'History') or contains(@href, '/en/match-history')]")
    private WebElement historyTab;

    @FindBy(xpath = "//*[normalize-space(text())='Consent' or normalize-space(text())='CONSENT']")
    private WebElement consentButton;

    public UserStory17(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickHistoryTab(WebDriver driver) {
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", historyTab);
    }

    public void acceptConsentIfPresent(WebDriver driver) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();
        } catch (Exception e) {
            // Ignorar
        }
    }
}
