package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe Page Object Model para o cenário UserStory 11 (Loja).
 * Engloba a navegação para a aba "Shop" e a interação com os pacotes de itens "Monsters" e "Emojis".
 */
public class UserStory11 {

    // Aba da Loja
    @FindBy(xpath = "//*[contains(text(), 'Shop') or contains(@href, '/en/shop')]")
    private WebElement shopTab;

    // Imagem do pacote "Monsters" (usando CSS se o XPath falhar)
    @FindBy(css = "img[alt*='Monster'], .box-shadow-1:nth-child(2) img")
    private WebElement monstersOption;

    // Imagem do pacote "Emojis" (usando CSS se o XPath falhar)
    @FindBy(css = "img[alt*='Emoji'], .box-shadow-1:nth-child(3) img")
    private WebElement emojisOption;

    // Botão Consent (Termos e Condições)
    @FindBy(xpath = "//*[normalize-space(text())='Consent' or normalize-space(text())='CONSENT']")
    private WebElement consentButton;

    public UserStory11(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickShopTab(WebDriver driver) {
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", shopTab);
    }

    public void clickMonstersOption(WebDriver driver) {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(monstersOption));
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", monstersOption);
    }

    public void clickEmojisOption(WebDriver driver) {
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(emojisOption));
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", emojisOption);
    }

    public void acceptConsentIfPresent(WebDriver driver) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();
        } catch (Exception e) {
            // Banner ignorado
        }
    }
}
