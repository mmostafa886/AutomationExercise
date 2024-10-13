package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class HomePage{
    private SHAFT.GUI.WebDriver driver ;

    private static String webBaseUrl = System.getProperty("baseUrl");
    @Getter
    private MenuBar menuBar;

    private By homeConcent = By.className("fc-cta-consent");
    private By appLogo = By.className("logo");
    private By categories = By.className("card_travel");

    public HomePage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
        this.menuBar = new MenuBar(driver);
    }


    public void isLogoDisplayed() {
        driver.element().isElementDisplayed(appLogo);
    }

    @Step("Navigate to the App homepage")
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

    @Step("Login process completed successfully")
    public HomePage assertLoginSuccess() {
        driver.element().assertThat(menuBar.logout).isVisible();
//        driver.element().assertThat(deleteAccount).isVisible();
        return this;
    }
}
