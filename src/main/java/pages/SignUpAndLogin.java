package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignUpAndLogin extends MenuBar{

    private SHAFT.GUI.WebDriver driver;

    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By loginEmail = By.xpath("//input[@data-qa='login-email']");
    private By loginPassword = By.xpath("//input[@data-qa='login-password']");
    private By signUpButton = By.xpath("//button[@data-qa='signup-button']");
    private By signupName = By.xpath("//input[@data-qa='signup-name']");
    private By signupEmail = By.xpath("//input[@data-qa='signup-email']");
    private By mrGender = By.id("id_gender1");
    private By mrsGender = By.id("id_gender2");
    private By signupPassword = By.xpath("//input[@data-qa='password']");
    private By birthdateDay = By.id("days");
    private By birthdateMonth = By.id("months");
    private By birthdateYear = By.id("years");
    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");
    private By addresFirstName = By.id("first_name");
    private By addresLastName = By.id("last_name");
    private By addresDetailedAdress = By.id("address1");
    private By addresCountry = By.id("country");
    private By addresState = By.id("state");
    private By addresCity = By.id("city");
    private By addresZipCode = By.id("zipcode");
    private By addresMobileNumber = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");


    public SignUpAndLogin(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private boolean isSignUpAndLoginPageOpened() {
        boolean isOpened;
        if (driver.element().isElementDisplayed(loginButton)) {
            isOpened = driver.element().isElementDisplayed(signUpButton);
        } else {
            isOpened = false;
        }
        return isOpened;
    }

    public SignUpAndLogin signUpFirstStep(String name, String emailAddress) {
        driver.element().clear(signupName).and().type(signupName, name);
        driver.element().clear(signupEmail).and().type(signupEmail, emailAddress);
        driver.element().click(signUpButton);
        return this;
    }

   @Step("Create New user account from Web")
    public AccountCreatedPage createAccount(String name, String emailAddress, String password, String firstName, String lastName, String address
            , String country, String state, String city, String zipCode, String mobileNumber) {
        signUpFirstStep(name, emailAddress);
        driver.element().click(mrGender);
        driver.element().clear(signupPassword).and().type(signupPassword, password);
        driver.element().select(birthdateDay, "1");
        driver.element().select(birthdateMonth, "3");
        driver.element().select(birthdateYear, "1998");
        driver.element().clear(addresFirstName).and().type(addresFirstName, firstName);
        driver.element().clear(addresLastName).and().type(addresLastName, lastName);
        driver.element().clear(addresDetailedAdress).and().type(addresDetailedAdress, address);
        driver.element().select(addresCountry, country);
        driver.element().clear(addresState).and().type(addresState, state);
        driver.element().clear(addresCity).and().type(addresCity, city);
        driver.element().clear(addresZipCode).and().type(addresZipCode, zipCode);
        driver.element().clear(addresMobileNumber).and().type(addresMobileNumber, mobileNumber);
        driver.element().click(createAccountButton);

        return new AccountCreatedPage(driver);
    }

    @Step("Perform Login")
    public HomePage userLogin(String emailAddress, String password) {
        driver.element().type(loginEmail, emailAddress);
        driver.element().type(loginPassword, password);
        driver.element().click(loginButton);
        return new HomePage(driver);
    }


}
