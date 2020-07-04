package global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class Utils {
    AppiumDriver<MobileElement> driver;

    public Utils(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    public void tap(MobileElement element){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element)))
                .perform();
    }

    public void longPress(MobileElement element){
        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
                .release().perform();
    }
}
