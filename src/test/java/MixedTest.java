import apis.ProductApi;
import apis.UserApi;
import com.shaft.driver.SHAFT;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

public class MixedTest extends TestBase {
    SHAFT.TestData.JSON testData;
    UserApi userApi;
    ProductApi productApi;
    SignUpAndLogin signUpAndLogin;
    ProductsPage productsPage;

    String randomName = RandomStringUtils.randomAlphanumeric(10).toLowerCase();

    @BeforeTest
    public void beforeTest() {
        startAPIInstance();
        startTheWebAppInstance();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
    /////////////////////////////////////////////

    @Test(description = "Create New user account through API then Login from Web and delete account through API Using Request Parameters")
    public void registerNewUserAPIAndLoginTest() {
        userApi = new UserApi(api);
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AccountData.json");
        userApi.createAccount(randomName, randomName + "@email.com", randomName
                        , testData.getTestData("FirstName"), testData.getTestData("LastName")
                        , testData.getTestData("Address"), testData.getTestData("Country")
                        , testData.getTestData("State"), testData.getTestData("City")
                        , testData.getTestData("Zipcode"), testData.getTestData("Mobile"))
                .confirmUserCreationSuccess();
        signUpAndLogin = menuBar.goToSinUpAndLoginPage();
        signUpAndLogin.userLogin(randomName + "@email.com", randomName).assertLoginSuccess().logOut();
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }


    @Test(description = "Search for a Product")
    public void searchForProduct() {
        productApi = new ProductApi(api);
        String randomProductName = productApi.getProductsList().getRandomProductName();
        productsPage = homePage.goToProductsPage();
        productsPage.searchForProducts(randomProductName).compareResultsToSearchText(randomProductName);
    }

    /**
     * This test won't work as the API ContentType is not JSON but URLENC
     */
//    @Test(enabled = false
//            , description = "Create New user account through API then Login from Web and delete account through API using JSON Request body")
    public void registerNewUserAPIAndLoginTestWithJSONBody() {
        userApi = new UserApi(api);
        testData = new SHAFT.TestData.JSON("src/test/resources/testDataFiles/AccountData.json");
        userApi.createAccountJson(randomName, randomName + "@email.com", randomName
                , testData.getTestData("FirstName"), testData.getTestData("LastName")
                , testData.getTestData("Address"), testData.getTestData("Country")
                , testData.getTestData("State"), testData.getTestData("City")
                , testData.getTestData("Zipcode"), testData.getTestData("Mobile"));
        signUpAndLogin = menuBar.goToSinUpAndLoginPage();
        signUpAndLogin.userLogin(randomName + "@email.com", randomName).assertLoginSuccess();
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }


}
