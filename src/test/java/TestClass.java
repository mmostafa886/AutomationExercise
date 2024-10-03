import apis.ApiBase;
import apis.UserApi;
import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class TestClass {
    SHAFT.GUI.WebDriver driver;
    SHAFT.API api;
    SHAFT.TestData.JSON testData;
    UserApi userApi;
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
        api = new SHAFT.API(ApiBase.ApiBaseURL);
        userApi = new UserApi(api);
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
    public void registerNewUserAndLoginTest() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AccountData.json");
        menuBar = new MenuBar(driver);
        signUpAndLogin = menuBar.goToSinUpAndLoginPage();
        accountCreatedPage = signUpAndLogin.createAccount(randomName, randomName + "@email.com", randomName
                , testData.getTestData("FirstName"), testData.getTestData("LastName")
                , testData.getTestData("Address"), testData.getTestData("Country")
                , testData.getTestData("State"), testData.getTestData("City")
                , testData.getTestData("Zipcode"), testData.getTestData("Mobile"));
        accountCreatedPage.assertSuccessMessageVisibility();
        homePage = accountCreatedPage.pressConitueButton().assertLoginSuccess();
        signUpAndLogin = homePage.logOut().isLogOutDisplayed();
        signUpAndLogin.userLogin(randomName + "@email.com", randomName).assertLoginSuccess();
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }

    @Test(description = "Create New user account through API then Login from Web and delete accountthrough API")
    public void registerNewUserAPIAndLoginTest() {
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AccountData.json");
        userApi.createAccount(randomName, randomName + "@email.com", randomName
                , testData.getTestData("FirstName"), testData.getTestData("LastName")
                , testData.getTestData("Address"), testData.getTestData("Country")
                , testData.getTestData("State"), testData.getTestData("City")
                , testData.getTestData("Zipcode"), testData.getTestData("Mobile"));
        menuBar = new MenuBar(driver);
        signUpAndLogin = menuBar.goToSinUpAndLoginPage();
        signUpAndLogin.userLogin(randomName + "@email.com", randomName).assertLoginSuccess();
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }


}
