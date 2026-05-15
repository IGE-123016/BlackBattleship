package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * UserStory08 - Page Object Class para teste de Chat em Tempo Real
 * 
 * Esta classe encapsula os localizadores e operações para a User Story 08:
 * "Como utilizador, eu quero aceder à página de chat para interagir com a comunidade."
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory08 {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Construtor que inicializa o WebDriver e WebDriverWait
     * 
     * @param driver O WebDriver a ser utilizado
     */
    public UserStory08(WebDriver driver) {
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
     * Obtém o botão ou link para aceder ao chat
     * 
     * @return WebElement do botão/link de chat
     */
    public WebElement getChatButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Chat') or contains(@aria-label, 'Chat')] | //a[contains(text(), 'Chat')]")
        ));
    }

    /**
     * Verifica se a página de chat foi aberta
     * 
     * @return true se a área de chat está visível
     */
    public boolean isChatPageOpen() {
        try {
            WebElement chatArea = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'chat') or @id='chat-container']")
            ));
            return chatArea.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém a lista de mensagens do chat
     * 
     * @return WebElement contendo as mensagens
     */
    public WebElement getChatMessages() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'messages') or contains(@class, 'chat-messages')]")
        ));
    }

    /**
     * Obtém o campo de entrada de texto do chat
     * 
     * @return WebElement representando o input de mensagem
     */
    public WebElement getChatInputField() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[contains(@placeholder, 'Message') or contains(@placeholder, 'Chat')] | //textarea[contains(@placeholder, 'Message')]")
        ));
    }

    /**
     * Obtém o botão de envio de mensagem
     * 
     * @return WebElement do botão de envio
     */
    public WebElement getSendButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Send') or contains(@aria-label, 'Send')]")
        ));
    }

    /**
     * Verifica se há utilizadores online visíveis no chat
     * 
     * @return true se há uma lista de utilizadores online
     */
    public boolean areOnlineUsersVisible() {
        try {
            java.util.List<WebElement> users = driver.findElements(
                By.xpath("//div[contains(@class, 'online-users') or contains(@class, 'users-list')] //li | //div[contains(@class, 'user-item')]")
            );
            return users.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

