package pages.retail;

import com.sun.xml.internal.rngom.parse.host.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends Base {
    private AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    public Login(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 30);
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.androidsample.generalstore:id/nameField']")
    private MobileElement nameField;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@resource-id='com.androidsample.generalstore:id/radioMale']")
    private MobileElement radio;

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")
    private MobileElement letsShopBtn;

    public Login enterName(String name){
        nameField.sendKeys(name);
        return this;
    }

    public Login selectRadio(){
        radio.click();
        return this;
    }

    public ProductList clickLetsShopBtn(){
        letsShopBtn.click();
        return new ProductList(driver);
    }

}
