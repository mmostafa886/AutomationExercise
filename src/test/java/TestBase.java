import apis.ApiBase;
import com.shaft.driver.SHAFT;
import pages.HomePage;
import pages.MenuBar;

public class TestBase {
    SHAFT.GUI.WebDriver driver;
    SHAFT.API api;

    HomePage homePage;
    MenuBar menuBar;

    public void startTheWebAppInstance() {
        driver = new SHAFT.GUI.WebDriver();
        homePage = new HomePage(driver);
        homePage.openAutomationExerciseWebSite();
        menuBar = new MenuBar(driver);
    }

    public void startAPIInstance() {
        api = new SHAFT.API(ApiBase.ApiBaseURL);
    }

}
