package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class for User Story 18.
 *
 * This class contains the operations and locators
 * related to the strategies and tactics section
 * of the Battleship website.
 */
public class UserStory18 {

    private WebDriver driver;

    /**
     * Strategies section title.
     */
    @FindBy(xpath = "//*[contains(text(),'Strategies and Tactics')]")
    public WebElement strategiesTitle;

    /**
     * Constructor.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory18(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    /**
     * Scrolls the page to the strategies section.
     */
    public void scrollToStrategiesSection() {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);",
                        strategiesTitle);
    }

    /**
     * Verifies if the strategies section is displayed.
     *
     * @return true if displayed, false otherwise.
     */
    public boolean isStrategiesSectionDisplayed() {

        return strategiesTitle.isDisplayed();
    }
}