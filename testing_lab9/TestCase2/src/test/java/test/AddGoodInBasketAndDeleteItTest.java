package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.BasketPage;
import page.GoodPage;

import java.util.Base64;

public class AddGoodInBasketAndDeleteItTest {
    private ChromeDriver driver;
    private GoodPage goodPage;
    private BasketPage basketPage;
    String addedGoodVendorCode; // артикул добавляемого товара

    @BeforeMethod (alwaysRun = true)
    public void browserSetupAndOpenGoodPage(){
        driver = new ChromeDriver();
        goodPage = new GoodPage(driver);
        goodPage.openPage();
    }

    @Test
    public void goodExistsInBasketAndDeleteItTest(){
        addedGoodVendorCode = goodPage.addGoodInBasket();
        basketPage = new BasketPage(driver);
        basketPage.openPage();

        Assert.assertTrue(basketPage.checkIfGoodExistsInBasket(addedGoodVendorCode));

        basketPage.deleteGoodFromBasket(addedGoodVendorCode);
        Assert.assertFalse(basketPage.checkIfGoodExistsInBasket(addedGoodVendorCode));
    }

    @AfterMethod (alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
        driver = null;
    }
}
