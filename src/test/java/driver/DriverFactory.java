package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static AppiumDriver<MobileElement> getAndroidDriver(String udid){
        AppiumDriver<MobileElement> appiumDriver;

        //Declare desiredCaps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("platform", "android");
        desiredCapabilities.setCapability("udid", udid);
        desiredCapabilities.setCapability("appPackage", "com.tuhuynh.sdetproecommerce");
        desiredCapabilities.setCapability("appActivity", "host.exp.exponent.MainActivity");

        // Send desiredCaps into appium server
        try {
            URL appiumServerPath = new URL("http://localhost:4723/wd/hub");
            appiumDriver = new AndroidDriver<>(appiumServerPath, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException("[ERR] Could not create appium session!");
        }

        return appiumDriver;
    }
}
