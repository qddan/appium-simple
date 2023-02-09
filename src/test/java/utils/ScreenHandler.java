package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.awt.*;
import java.time.Duration;

public class ScreenHandler {

    public static void swipeUp(AppiumDriver<MobileElement> appiumDriver){
        // Get mobile window size
        Dimension windowSize = appiumDriver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        // Calculate touch point
        int xStarPoint = 50*screenWidth/100;
        int xEndPoint = xStarPoint;
        int yStarPoint = 30*screenHeight/100;
        int yEndPoint = 50*screenHeight/100;

        // convert to pointOptions - coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStarPoint, yStarPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        // Perform Touch actions
        TouchAction touchAction = new TouchAction(appiumDriver);

        touchAction.press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(startPoint)
                .release()
                .perform(); // without this, will be no action at all
    }
}
