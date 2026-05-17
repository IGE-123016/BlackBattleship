package iscteiul.ista.blackbattleship.selenium.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object Class for User Story 23.
 *
 * This class contains the operations and locators
 * related to the Terms & Conditions page navigation.
 */
public class UserStory23 {

    private WebDriver driver;

    /**
     * Terms & Conditions link located in the footer.
     */
    @FindBy(partialLinkText = "Terms")
    public WebElement termsLink;

    /**
     * Constructor.
     *
     * @param driver Selenium WebDriver instance.
     */
    public UserStory23(WebDriver driver) {

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
     * Opens the Terms & Conditions page.
     */
    public void openTermsAndConditions() {

        scrollToFooter();

        termsLink.click();
    }
}