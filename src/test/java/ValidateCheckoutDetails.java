import global.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.retail.Cart;
import pages.retail.Login;
import pages.retail.ProductList;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class ValidateCheckoutDetails extends Base {
    private static AppiumDriver<MobileElement> driver;
    private static AppiumDriverLocalService service;

    @BeforeMethod
    private static void beforeMethod() throws IOException {
        String emulator = properties.getProperty("emulator");
        String apk = properties.getProperty("retailApp");
        service = startServer();
        driver = capabilities(apk, emulator);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public static void main1() throws InterruptedException {
        Login login = new Login(driver);
        String name = "sadsdwfsf";

        ProductList pl = login.enterName(name)
                .selectRadio()
                .clickLetsShopBtn();
        Cart cart = pl.clickAddToCart()
                .clickAddToCart()
                .clickCartBtn();

        sleep(2000);

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
