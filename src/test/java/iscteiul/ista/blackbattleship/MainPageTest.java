package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testPageLoadedSuccessfully() {
        // Verifica que a página foi carregada
        assertTrue(mainPage.isPageLoaded(), "Página deve estar completamente carregada");
        assertNotNull(mainPage.getPageTitle(), "Título da página não deve ser nulo");
        assertTrue(mainPage.getPageTitle().contains("JetBrains"), 
            "Título deve conter 'JetBrains'");
    }

    @Test
    public void testSearchElementExists() {
        // Verifica que o elemento de pesquisa existe (com WebDriverWait)
        WebElement searchElement = mainPage.getSearchInput();
        assertNotNull(searchElement, "Elemento de pesquisa deve existir");
    }

    @Test
    public void testDeveloperToolsLinkExists() {
        // Verifica que o link de Developer Tools existe (com WebDriverWait)
        WebElement toolsLink = mainPage.getDeveloperToolsLink();
        assertNotNull(toolsLink, "Link de Developer Tools deve existir");
    }
}




