import com.shaft.driver.SHAFT;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class TestClass {
    SHAFT.GUI.WebDriver driver;
    HomePage homePage;
    ProductsPage productsPage;
    MenuBar menuBar;
    SignUpAndLogin signUpAndLogin;
    AccountCreatedPage accountCreatedPage;

    String randomName = RandomStringUtils.randomAlphanumeric(10).toLowerCase();

//    @BeforeClass
//    public void beforeClass() {
//        driver = new SHAFT.GUI.WebDriver();
//    }

    @BeforeTest
    public void beforeTest() {
        driver = new SHAFT.GUI.WebDriver();
        homePage = new HomePage(driver);
        homePage.openAutomationExerciseWebSite();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }


    @Test
    public void firstTestMehod() {
        homePage.assertHomePageOpened();
    }

    @Test
    public void secondTestMethod() {
        menuBar = new MenuBar(driver);
        menuBar.goToProductsPage().assertProductsPageOpened();
    }

    @Test
    public void thirdTestMethod() {
        menuBar = new MenuBar(driver);
        signUpAndLogin = menuBar.goToSinUpAndLoginPage();
        accountCreatedPage = signUpAndLogin.createAccount(randomName, randomName + "@email.com", randomName, "FirstName", "LastName"
                , "Street001, 12487", "Canada", "Ontario", "Small City", "00000", "123456789");
        accountCreatedPage.assertSuccessMessageVisibility();
        accountCreatedPage.pressConitueButton().assertAccountCreationSuccess();
    }
}
