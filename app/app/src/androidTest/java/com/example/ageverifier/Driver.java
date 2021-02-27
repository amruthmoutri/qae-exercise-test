package com.example.ageverifier;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Driver {

    public static  AndroidDriver<AndroidElement> webDriver(String appName, String hostName, String portNumber) throws IOException {
        AndroidDriver<AndroidElement>  driver;

        File appDir = new File("app");
        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Rahulemulator");
        capabilities.setCapability(MobileCapabilityType.APP, appDir.getAbsolutePath() + "/src/"+appName);
        driver = new AndroidDriver<>(new URL(hostName+portNumber+"/wd/hub"), capabilities);

        return driver;
    }
}

