package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class MenuBar {
    private SHAFT.GUI.WebDriver driver;

    private By categories = By.className("card_travel");
    private By signupAndLogin = By.className("fa-lock");
    private By logout = By.xpath("//a[@href='/logout']");
    private By deleteAccount = By.className("fa-trash-o");

    public MenuBar(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    public ProductsPage goToProductsPage() {
        driver.element().click(categories);
        return new ProductsPage(driver);
    }

    public SignUpAndLogin goToSinUpAndLoginPage() {
        driver.element().click(signupAndLogin);
        return new SignUpAndLogin(driver);
    }

    public HomePage assertAccountCreationSuccess() {
        driver.element().assertThat(logout).isVisible();
        driver.element().assertThat(deleteAccount).isVisible();
        return new HomePage(driver);
    }
}
