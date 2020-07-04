import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Base {

    public static Properties prop;
    public static AppiumDriverLocalService service;

    public Base() throws IOException {
        if (prop!=null) init();
    }

    public static void init() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("/Users/nishantsinghal/git/appium2/src/main/java/config.properties");
        prop.load(fis);
    }

    public static  AppiumDriver<MobileElement> capabilities(String apk, String emulator) throws MalformedURLException
    {
        AppiumDriver<MobileElement> driver;

        // TODO Auto-generated method stub
        File appDir = new File("src");
        File app = new File(appDir, apk);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, emulator);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        return driver;
    }

    public static AppiumDriverLocalService startServer() throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("kill -9 $(lsof -t -i:4723)");
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        return service;
    }

}
