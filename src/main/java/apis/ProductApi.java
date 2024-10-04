package apis;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductApi {

    private SHAFT.API api;

    private final String productsList = "/productsList";
    private final String searchProducts = "/searchProduct";

    public ProductApi(SHAFT.API api) {
        this.api = api;
    }

    @Step("Getting a list of all products in the shop")
    public ProductApi getProductsList() {
        api.get(productsList)
                .setTargetStatusCode(ApiBase.SUCCESS)
                .perform();
        return this;
    }

    @Step("Assert that the Products List is not empty")
    public ProductApi assertProductsListNotEmpty() {
        /**
         * To verify a specific value is not null
         * Won't work in our case as the JSON response for getResponseJSONValueAsList("$.products") is a list of objects not a single object
         */
        String productItem = api.getResponseJSONValue("$.products[1]");
        Validations.assertThat().object(productItem).isNotNull().perform();


        //First Validation: this is a more detailed Validation
        List<Object> productsList = api.getResponseJSONValueAsList("$.products");
        for (Object product : productsList) {
            Validations.assertThat().object(product).isNotNull().perform();
        }
        //Second Validation: Compact one, with less details in Report
        Validations.assertThat().response(api).extractedJsonValue("$.products").isNotNull().perform();
        return this;
    }

    @Step("Verify the ProductName at Location [{index}] equals to [{productName}]")
    public ProductApi asserProductNameAtSpecificIndex(int index, String productName) {
        String productItem = api.getResponseJSONValue("$.products[" + (index - 1) + "].name");
        Validations.assertThat().object(productItem).isEqualTo(productName).perform();
        return this;
    }

    @Step("Search for [{productName}] Product")
    public ProductApi searchForAPproduct(String productName) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList("search_product", productName));
        api.post(searchProducts)
                .setParameters(data, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(ApiBase.SUCCESS)
                .perform();
        return this;
    }

    @Step("Search for last product from the Products List")
    public ProductApi searchForLastProductFromTheProductsList() {
        List<Object> productsList = getListOfProducts();
        int productsListSize = productsList.size();
        String lastProductName = getProductNameAtSpecificIndex(productsListSize);
        searchForAPproduct(lastProductName);
        asserProductNameAtSpecificIndex(0, lastProductName);
        return this;
    }

    @Step("Search for last product from the Products List")
    public ProductApi searchForRandomProductFromTheProductsList() {
        List<Object> productsList = getListOfProducts();
        Random random = new Random();
        int productsListSize = productsList.size();
        int randomIndex = random.nextInt(productsListSize+1);
        String lastProductName = getProductNameAtSpecificIndex(randomIndex);
        searchForAPproduct(lastProductName);
        asserProductNameAtSpecificIndex(0, lastProductName);
        return this;
    }

    @Step("Get ist of products")
    public List<Object> getListOfProducts() {
        return api.getResponseJSONValueAsList("$.products");
    }

    @Step("Get the name of he product at [{index}] location")
    public String getProductNameAtSpecificIndex(int index) {
        return api.getResponseJSONValue("$.products[" + (index - 1) + "].name");
    }

}
