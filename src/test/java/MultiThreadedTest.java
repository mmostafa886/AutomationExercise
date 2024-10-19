import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiThreadedTest {
    private static ThreadLocal<SHAFT.GUI.WebDriver> driverThreadLocal = new ThreadLocal<>();

    By searchField = By.name("q");
    ////textarea[@title="Search"]
    By acceptAllButton = By.id("L2AGLb");

    //////////////TAU\\\\\\\\\\\\\\
    By certificates = By.linkText("Certificates");
    By top100Students = By.xpath("//nav[@class=\"nav-links can-hide\"]/div/a[@href=\"/tau100.html\"]");
    By rankStudents = By.className("tau100-item");

    public void initializeDriver() {
        driverThreadLocal.set(new SHAFT.GUI.WebDriver());
    }

    public void quitDriver() {
        driverThreadLocal.get().quit();
        driverThreadLocal.remove();
    }

    public SHAFT.GUI.WebDriver getDriver() {
        return driverThreadLocal.get();
    }


    @BeforeMethod
    public void startDriver() {
        initializeDriver();
    }

    @AfterMethod
    public void removeThread() {
        quitDriver();
    }

    @Test(description = "Search Google For SHAFT_Engine")
    public void searchGoogleForShaftEngine() {
        getDriver().browser().navigateToURL("https://www.google.com/");
        getDriver().browser().assertThat().title().contains("Google").perform();
        if (getDriver().element().isElementDisplayed(acceptAllButton)) {
            getDriver().element().click(acceptAllButton);
        } else {
            System.out.println("The Pop-Up not displayed");
        }
        getDriver().element().type(searchField, "SHAFT_Engine").keyPress(searchField, Keys.ENTER);
        getDriver().browser().assertThat().title().contains("SHAFT_Engine");
    }

    @Test(description = "Test Automation University Navigation")
    public void testAutomationUniversityNavigation() {
        getDriver().browser().navigateToURL("https://testautomationu.applitools.com/");
        getDriver().browser().assertThat().title().contains("Test");
        getDriver().element().click(certificates);
        getDriver().element().click(top100Students);
        int studentsNumber = getDriver().element().getElementsCount(rankStudents);
        Validations.assertThat().object(studentsNumber).isNotNull();
    }

    @Test(description = "Search Google For SHAFT_Engine#2")
    public void searchGoogleForShaftEngine2() {
        getDriver().browser().navigateToURL("https://www.google.com/");
        getDriver().browser().assertThat().title().contains("Google").perform();
        if (getDriver().element().getElementsCount(acceptAllButton) > 0) {
            getDriver().element().click(acceptAllButton);
        }
        getDriver().element().type(searchField, "SHAFT_Engine").keyPress(searchField, Keys.ENTER);
        getDriver().browser().assertThat().title().contains("SHAFT_Engine");
    }

    @Test(description = "Test Automation University Navigation#2")
    public void testAutomationUniversityNavigation2() {
        getDriver().browser().navigateToURL("https://testautomationu.applitools.com/");
        getDriver().browser().assertThat().title().contains("Test");
        getDriver().element().click(certificates);
        getDriver().element().click(top100Students);
        int studentsNumber = getDriver().element().getElementsCount(rankStudents);
        Validations.assertThat().object(studentsNumber).isNotNull();
    }


}
