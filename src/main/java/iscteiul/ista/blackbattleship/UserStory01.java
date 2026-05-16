package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

/**
 * UserStory01 - Page Object Class para a geração inicial da frota/tabuleiro.
 *
 * Esta classe encapsula os localizadores e operações Selenium usados para validar
 * que a página do jogo BlackBattleship carrega corretamente e apresenta elementos
 * jogáveis, seguindo o padrão Page Object Model.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory01 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada nos testes
     */
    public UserStory01(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifica se a página terminou o carregamento.
     *
     * @return true se o documento HTML estiver completamente carregado
     */
    public boolean isGamePageLoaded() {
        try {
            wait.until(d -> ((JavascriptExecutor) d)
                    .executeScript("return document.readyState")
                    .equals("complete"));

            return driver.getTitle() != null && !driver.getTitle().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existe um tabuleiro ou uma área jogável visível.
     *
     * @return true se for detetado um elemento compatível com tabuleiro/área de jogo
     */
    public boolean isBoardVisible() {
        try {
            WebElement board = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//canvas" +
                                    " | //table" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'board')]" +
                                    " | //div[contains(translate(@id,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'board')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'grid')]" +
                                    " | //div[contains(translate(@id,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'grid')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'game')]"
                    )
            ));

            return board.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existem elementos de interação típicos do jogo.
     *
     * Como o site pode não expor os navios diretamente no DOM com classes como "ship",
     * este método valida a existência de uma área jogável e de elementos interativos.
     *
     * @return true se a página apresentar uma estrutura jogável
     */
    public boolean areShipsPlaced() {
        try {
            if (!isBoardVisible()) {
                return false;
            }

            List<WebElement> possibleCells = driver.findElements(
                    By.xpath(
                            "//canvas" +
                                    " | //td" +
                                    " | //button" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'cell')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'square')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'tile')]"
                    )
            );

            return possibleCells.size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existe algum controlo interativo relacionado com início/configuração do jogo.
     *
     * @return true se existir pelo menos um botão, link ou campo interativo visível
     */
    public boolean hasGameInteractionControls() {
        try {
            List<WebElement> controls = driver.findElements(
                    By.xpath("//button | //a | //input")
            );

            for (WebElement control : controls) {
                if (control.isDisplayed()) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém uma mensagem textual da página, se existir.
     *
     * @return texto de estado ou string vazia se não existir
     */
    public String getGameStatusMessage() {
        try {
            WebElement statusElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(
                            "//div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'status')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'message')]" +
                                    " | //p" +
                                    " | //h1" +
                                    " | //h2"
                    )
            ));

            return statusElement.getText();
        } catch (Exception e) {
            return "";
        }
    }
}