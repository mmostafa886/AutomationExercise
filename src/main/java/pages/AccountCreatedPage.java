package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class AccountCreatedPage {

    SHAFT.GUI.WebDriver driver;
    private By successMessage = By.xpath("//h2[@data-qa='account-created']");
    private By continueButton = By.xpath("//a[@data-qa='continue-button']");

    public AccountCreatedPage(SHAFT.GUI.WebDriver driver) {
        this.driver=driver;
    }

    public AccountCreatedPage assertSuccessMessageVisibility() {
        driver.element().assertThat(successMessage).isVisible();
        return this;
    }

    public MenuBar pressConitueButton() {
        driver.element().click(continueButton);
        return new MenuBar(driver);
    }
}
