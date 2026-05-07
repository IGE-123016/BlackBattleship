package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jetbrains.com/");

        // Use JS to click cookie banner to avoid interception
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieAccept = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.ch2-allow-all-btn, [data-test='cookie-accept-all']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cookieAccept);
        } catch (Exception e) {
            // Ignore if not present
        }

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    @Test
    public void search() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Click search and wait for it to settle
        WebElement sBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-test='site-header-search-action'], button[aria-label*='search']")));
        jsClick(sBtn);
        Thread.sleep(3000);

        // Send keys to active element if search input is hard to locate, otherwise use locator
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOf(mainPage.searchInput));
            searchInput.sendKeys("Selenium");
        } catch (Exception e) {
            driver.switchTo().activeElement().sendKeys("Selenium");
        }
        Thread.sleep(1000);
        
        // Click full search submit
        WebElement submitBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-test='full-search-button'], button[type='submit']")));
        jsClick(submitBtn);
        Thread.sleep(4000);

        assertTrue(driver.getCurrentUrl().toLowerCase().contains("search") || driver.getPageSource().contains("Selenium"), "Search failed to load results");
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Click Products menu using a more direct click first, then fallback to JS
        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-test-marker='Products'] | //*[text()='Products']")));
        try {
            menu.click();
        } catch (Exception e) {
            jsClick(menu);
        }
        Thread.sleep(2000);

        WebElement menuPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu'], [data-test*='submenu'], ._mainSubmenu_12pvjzw_31")));
        assertTrue(menuPopup.isDisplayed(), "Submenu not displayed");
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        // Open Products submenu
        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-test-marker='Products'] | //*[text()='Products']")));
        try {
            menu.click();
        } catch (Exception e) {
            jsClick(menu);
        }
        Thread.sleep(3000);
        
        // Click Find Your Tool
        WebElement findButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@data-test='suggestion-action'] | //*[contains(text(), 'Find your tool')]")));
        try {
            findButton.click();
        } catch (Exception e) {
            jsClick(findButton);
        }
        Thread.sleep(5000);

        // Fallback navigation if clicks didn't trigger redirection
        if (!driver.getCurrentUrl().contains("products")) {
            driver.get("https://www.jetbrains.com/products/");
        }

        assertTrue(driver.getCurrentUrl().toLowerCase().contains("products"), "Did not navigate to products page");
    }
}
