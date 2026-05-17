package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class for User Story 22.
 *
 * This class contains the operations and locators
 * related to the Privacy Policy page navigation.
 */
public class UserStory22 {

    private WebDriver driver;

    /**
     * Privacy Policy link located in the footer.
     */
    @FindBy(partialLinkText = "Privacy")
    public WebElement privacyPolicyLink;

    /**
     * Constructor.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory22(WebDriver driver) {

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
     * Opens the Privacy Policy page.
     */
    public void openPrivacyPolicy() {

        scrollToFooter();

        privacyPolicyLink.click();
    }
}