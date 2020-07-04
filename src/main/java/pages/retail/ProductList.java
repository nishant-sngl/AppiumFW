package pages.retail;

import com.sun.xml.internal.rngom.parse.host.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductList extends Base {

    private AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    public ProductList(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }

    @AndroidFindBy(xpath = "//*[@text='ADD TO CART']")
    private List<MobileElement> addToCartBtn;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    private MobileElement cartBtn;

    public ProductList clickAddToCart(){
        addToCartBtn.get(0).click();
        return this;
    }

    public Cart clickCartBtn(){
        cartBtn.click();
        return new Cart(driver);
    }
}
