package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

/**
 * UserStory07 - Page Object Class para validação de elementos relacionados
 * com a condição de vitória/fim de jogo.
 *
 * Esta classe encapsula os localizadores e operações usados para verificar
 * se a página do jogo disponibiliza uma área jogável, mensagens de estado
 * e controlos que permitam iniciar/reiniciar uma partida.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory07 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada nos testes
     */
    public UserStory07(WebDriver driver) {
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
     * Verifica se existe uma área jogável ou tabuleiro onde a partida decorre.
     *
     * @return true se existir uma área de jogo visível
     */
    public boolean isGameAreaAvailable() {
        try {
            WebElement gameArea = wait.until(ExpectedConditions.presenceOfElementLocated(
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

            return gameArea.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existem elementos clicáveis compatíveis com ações de jogo.
     *
     * @return true se existir pelo menos um elemento interativo visível
     */
    public boolean hasPlayableControls() {
        try {
            List<WebElement> controls = driver.findElements(
                    By.xpath("//button | //a | //input | //canvas")
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
     * Verifica se a página tem textos ou contentores usados para comunicar
     * o estado da partida ao jogador.
     *
     * @return true se existir algum elemento textual relevante
     */
    public boolean hasGameStatusInformation() {
        try {
            List<WebElement> statusElements = driver.findElements(
                    By.xpath(
                            "//h1 | //h2 | //h3 | //p | //span | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'status')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'message')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'score')]" +
                                    " | //div[contains(translate(@class,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'result')]"
                    )
            );

            for (WebElement element : statusElements) {
                if (element.isDisplayed() && !element.getText().trim().isEmpty()) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existe alguma referência textual a vitória, derrota,
     * fim de jogo ou reinício de partida, caso já esteja disponível.
     *
     * @return true se a página apresentar textos compatíveis com fim/reinício de jogo
     */
    public boolean hasEndGameOrRestartCapability() {
        try {
            String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();

            return pageText.contains("win")
                    || pageText.contains("won")
                    || pageText.contains("victory")
                    || pageText.contains("lose")
                    || pageText.contains("lost")
                    || pageText.contains("again")
                    || pageText.contains("new game")
                    || pageText.contains("restart")
                    || pageText.contains("play")
                    || pageText.contains("jogar")
                    || pageText.contains("nova")
                    || pageText.contains("ganhou")
                    || pageText.contains("perdeu");
        } catch (Exception e) {
            return false;
        }
    }
}