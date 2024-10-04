import apis.ProductApi;
import apis.UserApi;
import com.shaft.driver.SHAFT;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ApiTest extends TestBase{

    SHAFT.TestData.JSON testData;
    UserApi userApi;
    ProductApi productApi;

    String randomName = RandomStringUtils.randomAlphanumeric(10).toLowerCase();

    @BeforeTest
    public void beforeTest() {
        startAPIInstance();
    }
    ///////////////////////////////////////////////////

    @Test(description = "Get a list of all Products")
    public void getAllProductsList() {
        productApi = new ProductApi(api);
        productApi.getProductsList().assertProductsListNotEmpty();
        productApi.asserProductNameAtSpecificIndex(2, "Men Tshirt");
    }

    @Test(description = "Search for the last product in the products list")
    public void getTheLastProductInTheProductsList() {
        productApi = new ProductApi(api);
        productApi.getProductsList().searchForLastProductFromTheProductsList();
    }

    @Test(description = "Search for Random product in the products list")
    public void getRandomProductFrmTheProductsList() {
        productApi = new ProductApi(api);
        productApi.getProductsList().searchForRandomProductFromTheProductsList();
    }

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
        userApi.deleteAccount(randomName + "@email.com", randomName);
    }

}
