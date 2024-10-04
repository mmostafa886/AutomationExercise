package apis;

import com.shaft.api.RestActions;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import pojo.bodies.users.RegisterUserBody;

import java.util.Arrays;
import java.util.List;

public class UserApi {
    private SHAFT.API api;

    public UserApi(SHAFT.API api) {
        this.api = api;
    }

    private final String createAccount = "/createAccount";
    private final String deleteAccount = "/deleteAccount";
    private final String updateAccount = "/updateAccount";
    private final String accountDetails = "/getUserDetailByEmail";

    RegisterUserBody registerUserBody;

    @Step("Create New user Account through API")
    public UserApi createAccount(String name, String emailAddress, String password, String firstName, String lastName, String address
            , String country, String state, String city, String zipCode, String mobileNumber) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList("name", name),
                Arrays.asList("email", emailAddress),
                Arrays.asList("password", password),
                Arrays.asList("title", "Mr."),
                Arrays.asList("birth_date", "1"),
                Arrays.asList("birth_month", "7"),
                Arrays.asList("birth_year", "1980"),
                Arrays.asList("firstname", firstName),
                Arrays.asList("lastname", lastName),
                Arrays.asList("company", "company"),
                Arrays.asList("address1", "cairo"),
                Arrays.asList("address2", ""),
                Arrays.asList("country", country),
                Arrays.asList("zipcode", zipCode),
                Arrays.asList("state", state),
                Arrays.asList("city", city),
                Arrays.asList("mobile_number", mobileNumber));
        api.post(createAccount)
                .setParameters(data, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(ApiBase.SUCCESS)
                .perform();
        Validations.assertThat().response(api).extractedJsonValue("message").isNotNull().withCustomReportMessage("User Not Created");
        return this;
    }

    @Step("Confirm User Creation Success")
    public UserApi confirmUserCreationSuccess(){
        api.assertThatResponse().extractedJsonValue("message").doesNotContain("Error")
                .withCustomReportMessage("Error, User Not Created");
        return this;
    }

    /**
     * This method won't work as the API ContentType is not JSON but URLENC
     */
    @Step("Create New user Account through API")
    public void createAccountJson(String name, String emailAddress, String password, String firstName, String lastName, String address
            , String country, String state, String city, String zipCode, String mobileNumber) {
        registerUserBody = RegisterUserBody.builder()
                .userName(name)
                .emailAddress(emailAddress)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .country(country)
                .state(state)
                .city(city)
                .zipCode(zipCode)
                .mobileNumber(mobileNumber)
                .build();
        api.post(createAccount)
                .setRequestBody(List.of(registerUserBody))
                .setContentType(ContentType.URLENC)
                .perform();
    }

    @Step("Delete user account through API")
    public UserApi deleteAccount(String email, String password) {
        List<List<Object>> data = Arrays.asList(
                Arrays.asList("email", email),
                Arrays.asList("password", password));
        api.delete(deleteAccount)
                .setParameters(data, RestActions.ParametersType.FORM)
                .setContentType(ContentType.URLENC)
                .setTargetStatusCode(ApiBase.SUCCESS)
                .perform();
        return this;
    }
}
