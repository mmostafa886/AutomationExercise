import apis.UserApi;
import com.shaft.driver.SHAFT;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class WebTest extends TestBase{

    SHAFT.TestData.JSON testData;
    UserApi userApi;
    SignUpAndLogin signUpAndLogin;
    AccountCreatedPage accountCreatedPage;

    String randomName = RandomStringUtils.randomAlphanumeric(10).toLowerCase();

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        startTheWebAppInstance();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }


    @Test(groups = {"Smoke", "Regression"},description = "Open the App HomePage")
    public void firstTestMehod() {
        homePage.assertHomePageOpened();
    }

    @Test(groups = {"Smoke", "Regression"},description = "Open the Products Page")
    public void secondTestMethod() {
        menuBar.goToProductsPage().assertProductsPageOpened();
    }

    @Test(groups = {"Regression"},description = "Register a new user, login then delete it")
    public void registerNewUserAndLoginTest() {
        startAPIInstance();
        userApi = new UserApi(api);
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AccountData.json");
        signUpAndLogin = menuBar.goToSignUpAndLoginPage();
        accountCreatedPage = signUpAndLogin.createAccount(randomName, randomName + "@email.com", randomName
                , testData.getTestData("FirstName"), testData.getTestData("LastName")
                , testData.getTestData("Address"), testData.getTestData("Country")
                , testData.getTestData("State"), testData.getTestData("City")
                , testData.getTestData("Zipcode"), testData.getTestData("Mobile"));
        accountCreatedPage.assertSuccessMessageVisibility();
        homePage = accountCreatedPage.pressConitueButton().assertLoginSuccess();
        signUpAndLogin = homePage.getMenuBar().logOut().getMenuBar().isLogOutDisplayed();
        signUpAndLogin.userLogin(randomName + "@email.com", randomName).assertLoginSuccess();
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }



}
