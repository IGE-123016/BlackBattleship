package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Classe Page Object Model (POM) para o cenário de registo de convidado (Guest Login).
 * Conforme gravado no Selenium IDE, esta classe inicia uma partida contra o robô
 * e aborta imediatamente, o que cria um perfil de convidado e desbloqueia os menus de Chat e Amigos.
 */
public class LoginPage {

    // 1. Botão "Play vs robot"
    @FindBy(xpath = "//span[contains(.,'Play vs robot')] | //button[contains(.,'Play vs robot')]")
    private WebElement playVsRobotBtn;

    // 2. Campo de inserção de Nickname (Guest)
    @FindBy(css = ".input-xl")
    private WebElement nicknameInput;

    // 3. Botão "Continue" (Submeter nickname)
    @FindBy(xpath = "//button[contains(.,'Continue')]")
    private WebElement continueBtn;

    // 4. Botão "Abort game" (dentro da partida)
    @FindBy(xpath = "//button[contains(.,'Abort game')]")
    private WebElement abortGameBtn;

    // 5. Botão vermelho de confirmação de Abortar (Confirm leave)
    @FindBy(css = ".btn-danger")
    private WebElement confirmAbortBtn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Executa o fluxo de criação de sessão como Convidado (Guest).
     * @param driver Instância do WebDriver
     * @param nickname O nome de convidado a utilizar
     */
    public void createGuestSession(WebDriver driver, String nickname) {
        try {
            org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            
            // 1. Clicar em Play vs robot
            WebElement playBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(playVsRobotBtn));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", playBtn);
            
            // 2. Esperar pelo campo do nickname, limpar (se tiver algo) e escrever
            WebElement input = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(nicknameInput));
            input.clear();
            input.sendKeys(nickname);
            
            // 3. Clicar em Continue
            WebElement contBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(continueBtn));
            contBtn.click();
            
            // 4. Esperar que o jogo carregue e clicar em Abort game
            WebElement abortBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(abortGameBtn));
            abortBtn.click();
            
            // 5. Confirmar que queremos sair
            WebElement confirmBtn = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable(confirmAbortBtn));
            confirmBtn.click();
            
            // Esperar que o sistema regresse ao ecrã inicial já com a sessão criada
            Thread.sleep(3000); 
            
        } catch (Exception e) {
            System.out.println("Erro ao tentar criar a sessão de Guest.");
            e.printStackTrace();
        }
    }
}
