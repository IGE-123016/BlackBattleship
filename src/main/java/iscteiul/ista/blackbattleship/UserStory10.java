package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * UserStory10 - Page Object Class para validação de funcionalidades
 * relacionadas com convite, partilha ou criação de jogo com outro jogador.
 *
 * Esta classe encapsula os localizadores e operações Selenium usados para
 * verificar se a página do jogo disponibiliza elementos compatíveis com
 * criação/partilha de partidas.
 *
 * @author Rodrigo Sampaio (IGE-123023)
 * @version 1.1
 */
public class UserStory10 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada nos testes
     */
    public UserStory10(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Verifica se a página terminou o carregamento.
     *
     * @return true se o documento HTML estiver completamente carregado
     */
    public boolean isPageLoaded() {
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
     * Verifica se a página tem elementos interativos principais.
     *
     * @return true se existir pelo menos um botão, link, input ou canvas visível
     */
    public boolean hasInteractionElements() {
        try {
            List<WebElement> elements = driver.findElements(
                    By.xpath("//button | //a | //input | //canvas")
            );

            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se a página contém texto associado a jogar, convidar,
     * partilhar, enviar ou criar uma partida.
     *
     * @return true se forem encontrados indícios textuais da funcionalidade
     */
    public boolean hasInviteOrShareText() {
        try {
            String pageText = driver.findElement(By.tagName("body")).getText().toLowerCase();

            return pageText.contains("invite")
                    || pageText.contains("friend")
                    || pageText.contains("share")
                    || pageText.contains("link")
                    || pageText.contains("copy")
                    || pageText.contains("send")
                    || pageText.contains("play")
                    || pageText.contains("game")
                    || pageText.contains("online")
                    || pageText.contains("player")
                    || pageText.contains("convidar")
                    || pageText.contains("amigo")
                    || pageText.contains("partilhar")
                    || pageText.contains("ligação")
                    || pageText.contains("link")
                    || pageText.contains("copiar")
                    || pageText.contains("enviar")
                    || pageText.contains("jogar")
                    || pageText.contains("jogo")
                    || pageText.contains("jogador");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verifica se existem ligações ou botões que possam ser usados para navegar
     * ou criar uma interação de jogo.
     *
     * @return true se existir pelo menos um botão ou link visível
     */
    public boolean hasNavigationOrActionControls() {
        try {
            List<WebElement> controls = driver.findElements(
                    By.xpath("//button | //a")
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
     * Verifica se a URL atual pertence à página de batalha naval online.
     *
     * @return true se a URL atual for compatível com a página do jogo
     */
    public boolean isBattleshipPageUrl() {
        try {
            String currentUrl = driver.getCurrentUrl().toLowerCase();

            return currentUrl.contains("papergames")
                    && currentUrl.contains("battleship");
        } catch (Exception e) {
            return false;
        }
    }
}