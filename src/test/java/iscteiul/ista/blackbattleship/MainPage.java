package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {
    // Robust locators using text and common attributes
    @FindBy(xpath = "//*[contains(@data-test, 'main-menu-item') and contains(., 'Products')] | //button[contains(., 'Products')]")
    public WebElement productsMenu;

    @FindBy(xpath = "//*[contains(@data-test, 'suggestion-action')] | //*[contains(text(), 'Find your tool')]")
    public WebElement findYourToolsButton;

    @FindBy(css = "[data-test='site-header-search-action'], button[aria-label*='search']")
    public WebElement searchButton;

    @FindBy(css = "input[type='search'], input[data-test='search-input'], .js-search-input")
    public WebElement searchInput;

    @FindBy(css = "button[data-test='full-search-button'], button[type='submit']")
    public WebElement fullSearchButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
