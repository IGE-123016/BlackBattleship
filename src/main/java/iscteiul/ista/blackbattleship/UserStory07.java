package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * UserStory07 - Page Object Class para teste de Condição de Vitória Total
 * 
 * Esta classe encapsula os localizadores e operações para a User Story 07:
 * "Como jogador, eu quero ser declarado vencedor imediatamente após destruir 
 * a totalidade da frota adversária, para concluir a partida."
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory07 {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Construtor que inicializa o WebDriver e WebDriverWait
     * 
     * @param driver O WebDriver a ser utilizado
     */
    public UserStory07(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifica se a página de jogo foi carregada
     * 
     * @return true se a página está completamente carregada
     */
    public boolean isGamePageLoaded() {
        try {
            wait.until(driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o tabuleiro do adversário onde disparar
     * 
     * @return WebElement representando o tabuleiro adversário
     */
    public WebElement getOpponentBoard() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'opponent-board') or contains(@id, 'opponent')]")
        ));
    }

    /**
     * Verifica se a mensagem de vitória está visível
     * 
     * @return true se a mensagem "You Won" ou similar está exibida
     */
    public boolean isVictoryMessageDisplayed() {
        try {
            WebElement victoryMessage = driver.findElement(
                By.xpath("//div[contains(text(), 'Won') or contains(text(), 'Victory') or contains(text(), 'Ganhou')]")
            );
            return victoryMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o texto da mensagem de vitória
     * 
     * @return String com o texto da mensagem de vitória
     */
    public String getVictoryMessageText() {
        try {
            WebElement victoryMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Won') or contains(text(), 'Victory') or contains(text(), 'Ganhou')]")
            ));
            return victoryMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Verifica se existe um botão para jogar novamente
     * 
     * @return WebElement do botão de nova partida
     */
    public WebElement getPlayAgainButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Play Again') or contains(text(), 'Nova Partida') or contains(text(), 'Jogar Novamente')]")
        ));
    }

    /**
     * Verifica se o jogo foi finalizado (modal de fim de jogo visível)
     * 
     * @return true se a tela de fim de jogo está visível
     */
    public boolean isGameEndScreenVisible() {
        try {
            WebElement gameEndScreen = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'modal') or contains(@class, 'game-end') or contains(@class, 'end-game')]")
            ));
            return gameEndScreen.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica o placar (score) final
     * 
     * @return String com o texto do placar final
     */
    public String getFinalScore() {
        try {
            WebElement scoreElement = driver.findElement(
                By.xpath("//div[contains(@class, 'score') or contains(@class, 'final-score')]")
            );
            return scoreElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
}

