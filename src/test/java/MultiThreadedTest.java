import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiThreadedTest {
    private static ThreadLocal<SHAFT.GUI.WebDriver> driverThreadLocal = ThreadLocal.withInitial(SHAFT.GUI.WebDriver::new);

    By searchField = By.name("q");
    By acceptAllButton = By.id("L2AGLb");
    By certificates = By.xpath("//nav[@class=\"nav-links can-hide\"]/div/a[@href=\"/certificate/index.html\"]");
    By top100Students = By.xpath("//nav[@class=\"nav-links can-hide\"]/div/a[@href=\"/tau100.html\"]");
    By rankStudents = By.className("tau100-item");

    public SHAFT.GUI.WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod
    public void startDriver() {
        // Initialize driver only if it's not already initialized
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(new SHAFT.GUI.WebDriver());
        }
    }

    @AfterMethod
    public void removeThread() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThreadLocal.remove();
        }
    }

    @Test(groups = {"Smoke", "Regression"}, description = "Search Google For SHAFT_Engine")
    public void searchGoogleForShaftEngine() {
        getDriver().browser().navigateToURL("https://www.google.com/");
        getDriver().browser().assertThat().title().contains("Google").perform();
        try {
            getDriver().element().click(acceptAllButton);
        } catch (Exception e) {
            SHAFT.Report.log("The \"Accept All\" button is not displayed");
        }
        getDriver().element().type(searchField, "SHAFT_Engine").keyPress(searchField, Keys.ENTER);
        getDriver().browser().assertThat().title().contains("SHAFT_Engine");
    }

    @Test(groups = {"Regression"}, description = "Test Automation University Navigation")
    public void testAutomationUniversityNavigation() {
        getDriver().browser().navigateToURL("https://testautomationu.applitools.com/");
        getDriver().browser().assertThat().title().contains("Test");
        getDriver().element().click(certificates);
        getDriver().element().click(top100Students);
        int studentsNumber = getDriver().element().getElementsCount(rankStudents);
        Validations.assertThat().object(studentsNumber).isNotNull();
    }

    @Test(groups = {"Smoke", "Regression"}, description = "Search Google For SHAFT_Engine#2")
    public void searchGoogleForShaftEngine2() {
        getDriver().browser().navigateToURL("https://www.google.com/");
        getDriver().browser().assertThat().title().contains("Google").perform();
        try {
            getDriver().element().click(acceptAllButton);
        } catch (Exception e) {
            SHAFT.Report.log("The \"Accept All\" button is not displayed");
        }
        getDriver().element().type(searchField, "SHAFT_Engine").keyPress(searchField, Keys.ENTER);
        getDriver().browser().assertThat().title().contains("SHAFT_Engine");
    }

    @Test(groups = {"Smoke"}, description = "Test Automation University Navigation#2")
    public void testAutomationUniversityNavigation2() {
        getDriver().browser().navigateToURL("https://testautomationu.applitools.com/");
        getDriver().browser().assertThat().title().contains("Test");
        getDriver().element().click(certificates);
        getDriver().element().click(top100Students);
        int studentsNumber = getDriver().element().getElementsCount(rankStudents);
        Validations.assertThat().object(studentsNumber).isNotNull();
    }
}
