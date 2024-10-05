package pages;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MenuBar {
    private SHAFT.GUI.WebDriver driver;

    private By categories = By.className("card_travel");
    private By signupAndLogin = By.className("fa-lock");
    protected By logout = By.xpath("//a[@href='/logout']");
    protected By deleteAccount = By.className("fa-trash-o");

    public MenuBar(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigate to Products page")
    public ProductsPage goToProductsPage() {
        driver.element().click(categories);
        return new ProductsPage(driver);
    }

    @Step("Navigate to Signup/Login page")
    public SignUpAndLogin goToSinUpAndLoginPage() {
        driver.element().click(signupAndLogin);
        return new SignUpAndLogin(driver);
    }

    @Step("Perform Logout")
    public SignUpAndLogin logOut() {
        driver.element().click(logout);
        return new SignUpAndLogin(driver);
    }

    public SignUpAndLogin isLogOutDisplayed() {
        driver.element().assertThat(logout).doesNotExist();
        return new SignUpAndLogin(driver);
    }

}
