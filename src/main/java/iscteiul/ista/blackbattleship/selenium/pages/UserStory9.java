package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe Page Object Model (POM) para o cenário de teste UserStory 9 (Lista de Amigos).
 */
public class UserStory9 {

    /**
     * Localizador para o botão que redireciona para a aba "Friends".
     */
    @FindBy(xpath = "//*[contains(text(), 'Friends') or contains(@href, '/en/friends')]")
    private WebElement friendsTab;

    /**
     * Localizador genérico para o botão de "Consent" do banner de Termos e Condições.
     */
    @FindBy(xpath = "//*[normalize-space(text())='Consent' or normalize-space(text())='CONSENT']")
    private WebElement consentButton;

    public UserStory9(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Método para clicar na aba "Friends".
     * O JavascriptExecutor garante que o clique acontece mesmo que exista um anúncio sobreposto.
     */
    public void clickFriendsTab(WebDriver driver) {
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", friendsTab);
    }

    /**
     * Lida com o banner de Termos e Condições.
     */
    public void acceptConsentIfPresent(WebDriver driver) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(consentButton));
            btn.click();
        } catch (Exception e) {
            // Ignorar se o banner já foi fechado ou não apareceu
        }
    }
}
