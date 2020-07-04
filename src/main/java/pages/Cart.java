package pages;

import com.sun.xml.internal.rngom.parse.host.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Cart extends Base {

    private AppiumDriver<MobileElement> driver;
    WebDriverWait wait;
    Utils utils;

    public Cart(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
        utils = new Utils(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    private MobileElement totalPrice;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<MobileElement> priceList;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private MobileElement checkBox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    private MobileElement termsButton;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement closeTermsButton;

    private float getPrice(int index){
        String s = priceList.get(index).getAttribute("text");
        return convertStringPriceToFloat(s);
    }

    public float getPriceSum(){
        float sum = 0;
        for (int i = 0;i<priceList.size();i++){
            sum = sum + getPrice(i);
        }
        return sum;
    }

    public float getTotal(){
        return convertStringPriceToFloat(totalPrice.getText());
    }

    private float convertStringPriceToFloat(String s){
        s= s.substring(1);
        return Float.parseFloat(s);
    }

    public Cart clickCheckBox(){
        utils.tap(checkBox);
        return  this;
    }

    public Cart readTnC(){
        utils.longPress(termsButton);
        return this;
    }

    public Cart closeTermsPopup(){
        closeTermsButton.click();
        return this;
    }
}
