package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.Configuration;
import waits.CustomConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasketPage {
    WebDriver driver;
    private final String BASKET_PAGE_URL = "https://gutenberg.ru/basket/";

    @FindBy(xpath="//div[@data-column-property-code='PROPERTY_CML2_ARTICLE_VALUE']")
    private List<WebElement> listOfGoodsInBasket;

    @FindBy(xpath = "//span[@class='basket-item-actions-remove']")
    private List<WebElement> deleteGoodFromBasketBtn;

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(BASKET_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(CustomConditions.jQueryAJAXCompleted());
        PageFactory.initElements(driver, this);
    }

    public Boolean checkIfGoodExistsInBasket(String goodVendorCode){
        for (WebElement good : listOfGoodsInBasket) {
            if (good.getText().trim().equals(goodVendorCode.trim())) {
                return true;
            }
        }
        return false;
    }

    public void deleteGoodFromBasket(String goodVendorCode){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,900)");

        WebElement deleteGoodFromBasketBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@data-column-property-code='PROPERTY_CML2_ARTICLE_VALUE' " +
                                "and contains(string(), '" + goodVendorCode + "')]/ancestor::td/ancestor::tr" +
                                "/descendant::span[@class='basket-item-actions-remove'][1]")));
        deleteGoodFromBasketBtn.click();
    }
}
