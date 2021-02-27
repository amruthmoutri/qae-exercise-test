package com.example.ageverifier.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class CommonUtils {
    public static void waitForElementToBeDisplayed(AndroidDriver androidDriver, int timeUnit, WebElement element){
        new WebDriverWait(androidDriver, timeUnit).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForTextToBeDisplayed(AndroidDriver androidDriver, int timeUnit, WebElement element, String expectedText){
        new WebDriverWait(androidDriver, 10).until(ExpectedConditions.textToBePresentInElement(element, expectedText));
    }

    public static int generateRandomNumber(int max, int min ){
        int range = max - min + 1;
        int randomNumber = (int)(Math.random() * range) + min;
        return randomNumber;
    }

}
