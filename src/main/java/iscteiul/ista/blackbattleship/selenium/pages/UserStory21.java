package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class for User Story 21.
 *
 * This class contains the operations and locators
 * related to the Changelog page navigation.
 */
public class UserStory21 {

    private WebDriver driver;

    /**
     * Changelog link located in the footer.
     */
    @FindBy(linkText = "Changelog")
    public WebElement changelogLink;

    /**
     * Constructor.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory21(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToFooter() {

        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Opens the Changelog page.
     */
    public void openChangelog() {

        scrollToFooter();

        changelogLink.click();
    }
}