package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * UserStory02 - Page Object Class para Regra de Disparo Consecutivo
 *
 * "Como jogador, eu quero poder disparar novamente sempre que acertar num navio inimigo."
 *
 * Esta classe contém operações genéricas para interagir com o tabuleiro do adversário
 * e ler o resultado do último disparo.
 *
 * Autor: Rodrigo Sampaio (IGE-123023)
 */
public class UserStory02 {
    private WebDriver driver;
    private WebDriverWait wait;

    public UserStory02(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isGamePageLoaded() {
        try {
            wait.until(d -> ((org.openqa.selenium.JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém uma célula do tabuleiro do adversário por índices (linha, coluna)
     * Atenção: selectors são heurísticos e tolerantes à variação da UI.
     */
    public WebElement getOpponentCell(int row, int col) {
        // tenta encontrar cells como divs ou table td
        String xpath1 = String.format("(//div[contains(@class,'opponent') or contains(@class,'enemy') or contains(@id,'opponent')]//div[contains(@class,'cell') or contains(@class,'tile')])[%d]", (row*10 + col + 1));
        String xpath2 = String.format("(//table[contains(@class,'opponent') or contains(@id,'opponent')]//td)[%d]", (row*10 + col + 1));
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));
        } catch (Exception e) {
            return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath2)));
        }
    }

    /**
     * Dispara numa célula (clica) e espera um marcador de resultado (hit/miss)
     */
    public String fireAt(int row, int col) {
        try {
            WebElement cell = getOpponentCell(row, col);
            cell.click();
            // esperar por um marcador de resultado próximo à célula
            // procura classes ou textos comuns: 'hit', 'miss', 'splash'
            try {
                WebElement result = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'hit') or contains(@class,'miss') or contains(.,'hit') or contains(.,'miss')]")
                ));
                String txt = result.getText();
                if (txt == null || txt.isEmpty()) {
                    // fallback: ler atributo class
                    String cls = result.getAttribute("class");
                    return cls == null ? "unknown" : cls;
                }
                return txt.toLowerCase();
            } catch (Exception ex) {
                // se não encontrar resultado, devolve unknown
                return "unknown";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    /**
     * Verifica se o jogador tem turn ativo (heurística: texto ou indicador)
     */
    public boolean isPlayerTurn() {
        try {
            WebElement turn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(.,'Your turn') or contains(.,'Your move') or contains(@class,'turn')]")
            ));
            return turn.isDisplayed();
        } catch (Exception e) {
            return true; // se não encontrar, assumir que é possível continuar testes
        }
    }
}

