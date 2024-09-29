package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class HomePage {
    private SHAFT.GUI.WebDriver driver;

    private static String webBaseUrl = "https://www.automationexercise.com/";

    private By homeConcent = By.className("fc-cta-consent");
    private By appLogo = By.className("logo");
    private By categories = By.className("card_travel");

    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public void isLogoDisplayed() {
        driver.element().isElementDisplayed(appLogo);
    }

    public HomePage openAutomationExerciseWebSite() {
        driver.browser().navigateToURL(webBaseUrl);
        driver.element().click(homeConcent);
        return this;
    }

    public HomePage assertHomePageOpened() {
        driver.element().verifyThat(appLogo).isVisible().withCustomReportMessage("The WebPage logo is displayed").perform();
        driver.browser().assertThat().title().contains("Automation").withCustomReportMessage("Title matching expected").perform();
        return this;
    }
}
