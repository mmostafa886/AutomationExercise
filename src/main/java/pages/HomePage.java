package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class HomePage extends MenuBar {
    private SHAFT.GUI.WebDriver driver ;

    private static String webBaseUrl = System.getProperty("baseUrl");

    private By homeConcent = By.className("fc-cta-consent");
    private By appLogo = By.className("logo");
    private By categories = By.className("card_travel");

    public HomePage(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


    public void isLogoDisplayed() {
        driver.element().isElementDisplayed(appLogo);
    }

    public HomePage openAutomationExerciseWebSite() {
        driver.browser().navigateToURL(webBaseUrl);
        if (driver.element().isElementDisplayed(homeConcent)) {
            driver.element().click(homeConcent);
        }
        return this;
    }

    public HomePage assertHomePageOpened() {
        driver.element().verifyThat(appLogo).isVisible().withCustomReportMessage("The WebPage logo is displayed").perform();
//        driver.browser().assertThat().title().contains("Automation").withCustomReportMessage("Title matching expected").perform();
        return this;
    }

    public HomePage assertLoginSuccess() {
        driver.element().assertThat(logout).isVisible();
//        driver.element().assertThat(deleteAccount).isVisible();
        return new HomePage(driver);
    }
}
