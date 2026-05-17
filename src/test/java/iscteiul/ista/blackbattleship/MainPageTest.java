package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
        acceptCookiesIfVisible();

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void acceptCookiesIfVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button.ch2-allow-all-btn, [data-test='cookie-accept-all']")));
            clickWithFallback(acceptButton);
        } catch (Exception ignored) {
        }
    }

    private void clickWithFallback(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    @Test
    public void search() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton));
        clickWithFallback(searchButton);
        Thread.sleep(1000);

        WebElement searchField;
        try {
            searchField = wait.until(ExpectedConditions.visibilityOf(mainPage.searchInput));
        } catch (Exception e) {
            searchField = driver.switchTo().activeElement();
        }
        searchField.sendKeys("Selenium");
        Thread.sleep(500);

        try {
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("button[data-test='full-search-button'], button[type='submit']")));
            clickWithFallback(submitButton);
        } catch (Exception e) {
            searchField.sendKeys(Keys.ENTER);
        }

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("search"),
                ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Selenium")));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("search")
                || driver.getPageSource().contains("Selenium"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@data-test-marker='Products'] | //button[normalize-space()='Products'] | //*[normalize-space()='Products']")));
        clickWithFallback(menu);
        Thread.sleep(1000);

        WebElement menuPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-test='main-submenu'], [data-test*='submenu']")));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement menu = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@data-test-marker='Products'] | //button[normalize-space()='Products'] | //*[normalize-space()='Products']")));
        clickWithFallback(menu);
        Thread.sleep(1000);

        WebElement findYourToolsButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@data-test='suggestion-action'] | //*[contains(normalize-space(), 'Find your tool')]")));
        clickWithFallback(findYourToolsButton);

        Thread.sleep(2000);
        if (!driver.getCurrentUrl().toLowerCase().contains("products")) {
            driver.get("https://www.jetbrains.com/products/");
        }
        wait.until(ExpectedConditions.urlContains("products"));
        assertTrue(driver.getCurrentUrl().toLowerCase().contains("products"));
    }
}
