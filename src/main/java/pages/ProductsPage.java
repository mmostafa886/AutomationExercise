package pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class ProductsPage extends MenuBar{
    SHAFT.GUI.WebDriver driver;

    private By advertiseArea = By.id("advertisement");
    private By allProductsArea = By.className("features_items");
    private By allProductsAreaTitle = By.className("title");
    private By serachProductField = By.id("search_product");

    public ProductsPage(SHAFT.GUI.WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isProductsPageOpened() {
        return driver.element().isElementDisplayed(allProductsArea);
    }

    public String getAllProductsAreaTitle() {
        return driver.element().getText(allProductsAreaTitle);
    }

    public ProductsPage assertProductsPageOpened() {
        driver.element().assertThat(allProductsAreaTitle)
                .text().equals("ALL PRODUCTS");
        driver.browser().assertThat().title().contains("All Products");
        return this;
    }
}
