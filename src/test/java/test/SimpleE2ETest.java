package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenHandler;

import java.net.URL;
import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SimpleE2ETest {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getAndroidDriver("emulator-5554");

        // login into the app
        MobileElement userIconElem = appiumDriver.findElement(MobileBy.AccessibilityId("userIcon"));
        userIconElem.click();

        MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("emailInput"));
        MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("passwordInput"));
        MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("loginBtn"));
        emailInputElem.sendKeys("teo@sth.com");
        passwordInputElem.sendKeys("12345678");
        loginBtnElem.click();

        // Wait to see results, just for fun :D
        Thread.sleep(2000);

        // Go back to home
        appiumDriver.findElement(MobileBy.AccessibilityId("homeIcon")).click();

        //  randomly select a product item
        List<MobileElement> productItemElems = appiumDriver.findElements(MobileBy.AccessibilityId("productItem"));
        MobileElement randomProductItem = productItemElems.get(new SecureRandom().nextInt(productItemElems.size()));
        randomProductItem.click();

        // print out the item details
        WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("addToCardBtn")));
        ScreenHandler.swipeUp(appiumDriver);

        MobileElement productBrandElem = appiumDriver.findElement(MobileBy.AccessibilityId("productBrand"));
        MobileElement productNameElem = appiumDriver.findElement(MobileBy.AccessibilityId("productName"));
        MobileElement productAvailabilityElem = appiumDriver.findElement(MobileBy.AccessibilityId("productAvailability"));
        MobileElement productPriceElem = appiumDriver.findElement(MobileBy.AccessibilityId("productPrice"));
        MobileElement addToCardBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("addToCardBtn"));

        System.out.println("Brand: "+ productBrandElem.getText());
        System.out.println("name: "+ productNameElem.getText());
        System.out.println(productAvailabilityElem.getText());
        System.out.println("Price: "+ Double.parseDouble(productPriceElem.getText().split(" ")[1].trim()));
        addToCardBtnElem.click();


        // Wait to see results, just for fun :D
        Thread.sleep(3000);
    }
}



