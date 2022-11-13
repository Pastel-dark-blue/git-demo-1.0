package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

import java.time.Duration;

public class GoodPage {
    WebDriver driver;
    private final String GOOD_PAGE_URL =
            "https://gutenberg.ru/molotyy_kofe/kofe_malongo_molotyy_moka_efiopiya_sidamo_250_g/";

    @FindBy(xpath="//span[@class='btn-lg to-cart btn btn-default transition_bg animate-load']")
    private WebElement addInBasketButton;

    @FindBy(xpath="//span[@class='article__value']")
    private WebElement addedGoodVendorCode;

    public GoodPage(WebDriver driver){
        this.driver = driver;
    }

    public void openPage(){
        driver.get(GOOD_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(CustomConditions.jQueryAJAXCompleted());
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 3000), this);
    }

    public String addGoodInBasket(){
        //element click intercepted - исключение, которое означает, что есть другой элемент над вашим
        // (всплывающее окно и пр.), поэтому, когда Selenium пытается нажать на ваш элемент,
        // но фактически он нажимает на этот перекрывающий элемент; поэтому я добавила прокрутку страницы
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(0,900)");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addInBasketButton)).click();
        return addedGoodVendorCode.getText();
    }
}
