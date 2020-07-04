import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Cart;
import pages.Login;
import pages.ProductList;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ValidateCheckoutDetails extends Base{
    private static AppiumDriver<MobileElement> driver;
    private static AppiumDriverLocalService service;
    public ValidateCheckoutDetails() throws IOException {
    }

    @BeforeMethod
    private static void beforeMethod() throws IOException {
        service = startServer();
        driver = capabilities("General-Store.apk", "emulator-5554");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public static void main1() throws InterruptedException {
        Login login = new Login(driver);
        ProductList pl = login.enterName("sssss")
                .selectRadio()
                .clickLetsShopBtn();
        Cart cart = pl.clickAddToCart()
                .clickAddToCart()
                .clickCartBtn();

        sleep(2000); // to overcome we need to handle this by wait or sleep.

        float sum = cart.getPriceSum();
        System.out.println("total: " + sum);
        float ff = cart.getTotal();
        Assert.assertEquals(sum , ff);

        cart.clickCheckBox()
                .readTnC()
                .closeTermsPopup();
    }

    @AfterMethod
    private static void afterMethod(){
        service.stop();
    }
}
