package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * UserStory10 - Page Object Class para teste de Link de Convite para Amigos
 * 
 * Esta classe encapsula os localizadores e operações para a User Story 10:
 * "Como utilizador, eu quero gerar um link de convite para convidar um amigo 
 * específico para uma partida."
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory10 {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Construtor que inicializa o WebDriver e WebDriverWait
     * 
     * @param driver O WebDriver a ser utilizado
     */
    public UserStory10(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifica se a página foi carregada
     * 
     * @return true se a página está completamente carregada
     */
    public boolean isPageLoaded() {
        try {
            wait.until(driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o botão ou opção para criar um convite
     * 
     * @return WebElement do botão de convite
     */
    public WebElement getInviteButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Invite') or contains(text(), 'Convidar')] | //a[contains(text(), 'Invite')]")
        ));
    }

    /**
     * Verifica se o formulário ou campo de convite está visível
     * 
     * @return true se a interface de convite está visível
     */
    public boolean isInviteDialogOpen() {
        try {
            WebElement inviteDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'invite') or contains(@class, 'modal') or @id='invite-dialog']")
            ));
            return inviteDialog.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o campo de entrada para o nome ou email do amigo
     * 
     * @return WebElement do campo de entrada
     */
    public WebElement getFriendInputField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[contains(@placeholder, 'friend') or contains(@placeholder, 'email') or contains(@placeholder, 'name')]")
        ));
    }

    /**
     * Obtém o botão para gerar o link de convite
     * 
     * @return WebElement do botão de geração de link
     */
    public WebElement getGenerateLinkButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Generate') or contains(text(), 'Gerar') or contains(text(), 'Send')]")
        ));
    }

    /**
     * Verifica se o link de convite foi gerado e é exibido
     * 
     * @return true se o link está visível
     */
    public boolean isInviteLinkDisplayed() {
        try {
            WebElement inviteLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'link') or contains(@class, 'invite-link')] | //input[@readonly and contains(@value, 'http')]")
            ));
            return inviteLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o link de convite gerado
     * 
     * @return String com o link de convite
     */
    public String getInviteLink() {
        try {
            WebElement linkElement = driver.findElement(
                By.xpath("//input[@readonly and contains(@value, 'http')] | //div[contains(@class, 'link')]")
            );
            String link = linkElement.getAttribute("value");
            if (link == null || link.isEmpty()) {
                link = linkElement.getText();
            }
            return link;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Obtém o botão para copiar o link
     * 
     * @return WebElement do botão de cópia
     */
    public WebElement getCopyLinkButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Copy') or contains(@aria-label, 'Copy')]")
        ));
    }
}

