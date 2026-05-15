package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

// page_url = https://www.jetbrains.com/
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageLoaded() {
        try {
            // Aguarda carregamento completo da página
            wait.until(driver1 -> ((org.openqa.selenium.JavascriptExecutor) driver1)
                .executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebElement getSearchInput() {
        // Aguarda o campo de pesquisa estar presente
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("[data-test='site-header-search-action']")
        ));
    }

    public WebElement getDeveloperToolsLink() {
        // Asynchona espera por um link que contenha "Tools" ou similar
        return wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[contains(text(), 'Developer Tools') or contains(., 'Tools')]")
        ));
    }
}
