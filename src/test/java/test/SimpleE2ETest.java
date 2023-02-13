package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenHandler;

import java.security.SecureRandom;
import java.util.List;

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

        // Wait to login successful
        WebDriverWait waitLogin = new WebDriverWait(appiumDriver, 15);
        waitLogin.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("signOutBtn")));

        // Go back to home
        appiumDriver.findElement(MobileBy.AccessibilityId("homeIcon")).click();

        //  randomly select a product item
        List<MobileElement> productItemElems = appiumDriver.findElements(MobileBy.AccessibilityId("productItem"));
        MobileElement randomProductItem = productItemElems.get(new SecureRandom().nextInt(productItemElems.size()));
        randomProductItem.click();

        WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId("addToCartBtn")));
        ScreenHandler.swipeUp(appiumDriver);

        // print out the item details
        MobileElement productBrandElem = appiumDriver.findElement(MobileBy.AccessibilityId("productBrand"));
        MobileElement productNameElem = appiumDriver.findElement(MobileBy.AccessibilityId("productName"));
        MobileElement productAvailabilityElem = appiumDriver.findElement(MobileBy.AccessibilityId("productAvailability"));
        MobileElement productPriceElem = appiumDriver.findElement(MobileBy.AccessibilityId("productPrice"));
        MobileElement addToCardBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("addToCartBtn"));

        System.out.println("Brand: "+ productBrandElem.getText());
        System.out.println("name: "+ productNameElem.getText());
        System.out.println(productAvailabilityElem.getText());
        System.out.println("Price: "+ Double.parseDouble(productPriceElem.getText().split(" ")[1].trim()));
        addToCardBtnElem.click();

        // Go to cart and click on checkout button
        appiumDriver.findElement(MobileBy.AccessibilityId("cartIcon")).click();
        appiumDriver.findElement(MobileBy.AccessibilityId("checkoutBtn")).click();

        // Input shipping address
        MobileElement phoneNumElem = appiumDriver.findElement(MobileBy.AccessibilityId("phoneNum"));
        MobileElement shippingAdd1Elem = appiumDriver.findElement(MobileBy.AccessibilityId("shippingAdd1"));
        MobileElement shippingAdd2Elem = appiumDriver.findElement(MobileBy.AccessibilityId("shippingAdd2"));
        phoneNumElem.sendKeys("123456789");
        shippingAdd1Elem.sendKeys("add 1");
        shippingAdd2Elem.sendKeys("add 2");
        ScreenHandler.swipeUp(appiumDriver);


        MobileElement shippingCityElem = appiumDriver.findElement(MobileBy.AccessibilityId("shippingCity"));
        MobileElement shippingZIPCodeElem = appiumDriver.findElement(MobileBy.AccessibilityId("shippingZIPCode"));
        shippingCityElem.sendKeys("city");
        shippingZIPCodeElem.sendKeys("7000");


        MobileElement selectCountryTriggerElem = appiumDriver.findElement(MobileBy.AccessibilityId("selectCountryTrigger"));
        selectCountryTriggerElem.click();

        MobileElement countryNameElem = appiumDriver.findElement(MobileBy.xpath("//*[@text=\"Angola\"]"));
        countryNameElem.click();

        MobileElement confirmShippingBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("confirmShippingFormBtn"));
        confirmShippingBtnElem.click();



        // Wait to see results, just for fun :D
        Thread.sleep(3000);

        //page object modal/ selenium gird
    }
}



