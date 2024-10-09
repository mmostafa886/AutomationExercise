import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;

public class DockerizedTest {
    SHAFT.GUI.WebDriver driver;

    By searchField = By.name("q");
    ////textarea[@title="Search"]
    By acceptAllButton = By.id("L2AGLb");

    //////////////TAU\\\\\\\\\\\\\\
    By certificates = By.linkText("Certificates");
    By top100Students = By.xpath("//nav[@class=\"nav-links can-hide\"]/div/a[@href=\"/tau100.html\"]");
    By rankStudents = By.className("tau100-item");

    @BeforeMethod
    public void startDriver() {
        driver = new SHAFT.GUI.WebDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(description = "Search Google For SHAFT_Engine")
    public void searchGoogleForShaftEngine() {
        driver.browser().navigateToURL("https://www.google.com/");
        driver.browser().assertThat().title().contains("Google").perform();
        driver.element().click(acceptAllButton);
        driver.element().type(searchField, "SHAFT_Engine").keyPress(searchField, Keys.ENTER);
        driver.browser().assertThat().title().contains("SHAFT_Engine");
    }

    @Test(description = "Test Automation University Navigation")
    public void testAutomationUniversityNavigation() {
        driver.browser().navigateToURL("https://testautomationu.applitools.com/");
        driver.browser().assertThat().title().contains("Test");
        driver.element().click(certificates);
        driver.element().click(top100Students);
        int studentsNumber = driver.element().getElementsCount(rankStudents);
        Validations.assertThat().object(studentsNumber).isNotNull();
    }


}
