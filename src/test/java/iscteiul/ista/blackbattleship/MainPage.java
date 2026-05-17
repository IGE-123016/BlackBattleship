package iscteiul.ista.blackbattleship;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {
    @FindBy(xpath = "//*[@data-test-marker='Products'] | //button[normalize-space()='Products'] | //*[normalize-space()='Products']")
    public WebElement productsMenu;

    @FindBy(xpath = "//*[@data-test='suggestion-action'] | //*[contains(normalize-space(), 'Find your tool')]")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//*[@data-test-marker='Products'] | //button[normalize-space()='Products'] | //*[normalize-space()='Products']")
    public WebElement toolsMenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    @FindBy(css = "[data-test='search-input'], input[type='search'], input[name='q']")
    public WebElement searchInput;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
