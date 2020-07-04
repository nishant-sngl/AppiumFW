package global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Base {

    public static AppiumDriverLocalService service;
    public static Properties properties;

    public Base(){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/configs/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties = new Properties();
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static  AppiumDriver<MobileElement> capabilities(String apk, String emulator) throws IOException
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
