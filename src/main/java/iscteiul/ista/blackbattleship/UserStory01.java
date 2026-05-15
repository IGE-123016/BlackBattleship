package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

/**
 * UserStory01 - Page Object Class para teste de Geração Aleatória de Frota
 * 
 * Esta classe encapsula os localizadores e operações para a User Story 01:
 * "Como jogador, eu quero que o jogo posicione os meus navios aleatoriamente 
 * no tabuleiro 10x10 ao iniciar, para que a partida comece rapidamente."
 * 
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.0
 */
public class UserStory01 {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Construtor que inicializa o WebDriver e WebDriverWait
     * 
     * @param driver O WebDriver a ser utilizado
     */
    public UserStory01(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifica se a página principal do jogo foi carregada
     * 
     * @return true se a página foi carregada com sucesso
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
     * Clica no botão para iniciar um novo jogo (New Game)
     * 
     * @return WebElement do botão de novo jogo
     */
    public WebElement getNewGameButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'New Game') or contains(text(), 'Novo Jogo')]")
        ));
    }

    /**
     * Verifica se o tabuleiro 10x10 foi gerado
     * 
     * @return true se o tabuleiro está visível
     */
    public boolean isBoardVisible() {
        try {
            WebElement board = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'board') or @id='board']")
            ));
            return board.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se os navios foram colocados aleatoriamente no tabuleiro
     * 
     * @return true se há elementos representando navios no tabuleiro
     */
    public boolean areShipsPlaced() {
        try {
            java.util.List<WebElement> ships = driver.findElements(
                By.xpath("//div[contains(@class, 'ship') or contains(@class, 'vessel')]")
            );
            return ships.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clica no botão para gerar novamente os navios aleatoriamente
     * 
     * @return WebElement do botão de random placement
     */
    public WebElement getRandomizeButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(), 'Random') or contains(text(), 'Aleatório')]")
        ));
    }

    /**
     * Obtém o texto da mensagem de estado do jogo
     * 
     * @return String com a mensagem de estado
     */
    public String getGameStatusMessage() {
        try {
            WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'status') or contains(@class, 'message')]")
            ));
            return statusElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
}

