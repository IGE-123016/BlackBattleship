package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe Page Object Model (POM) para o cenário de teste UserStory 8 (Acesso ao Chat).
 * Esta classe contém os localizadores (XPath/CSS) e métodos para interagir
 * com os elementos da página necessários à execução do teste.
 */
public class UserStory8 {

    /**
     * Localizador para o botão/link que redireciona para a aba "Messaging" (Chat).
     * Utiliza XPath para procurar qualquer elemento que contenha o texto 'Messaging'.
     */
    @FindBy(xpath = "//*[contains(text(), 'Messaging') or contains(@href, '/en/chat')]")
    private WebElement messagingTab;

    /**
     * Localizador genérico para o botão de "Consent" do banner de Termos e Condições.
     */
    @FindBy(xpath = "//*[normalize-space(text())='Consent']")
    private java.util.List<WebElement> consentButtons;

    /**
     * Construtor da classe Page Object.
     * Inicializa os elementos da página utilizando o PageFactory do Selenium.
     *
     * @param driver a instância ativa do WebDriver.
     */
    public UserStory8(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Método para clicar na aba "Messaging".
     * Utiliza JavascriptExecutor para contornar potenciais sobreposições (ex: banners de cookies).
     * @param driver a instância ativa do WebDriver para executar o JS.
     */
    public void clickMessagingTab(WebDriver driver) {
        org.openqa.selenium.JavascriptExecutor executor = (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", messagingTab);
    }

    /**
     * Lida com o banner de Termos e Condições e Cookies inicial.
     * Espera ativamente pelo botão 'Consent' e clica caso ele exista.
     * @param driver a instância ativa do WebDriver.
     */
    public void acceptConsentIfPresent(WebDriver driver) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5));
            WebElement btn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath("//*[normalize-space(text())='Consent' or normalize-space(text())='CONSENT']")
            ));
            btn.click();
        } catch (Exception e) {
            System.out.println("Banner de consentimento ignorado ou não encontrado.");
        }
    }
}
