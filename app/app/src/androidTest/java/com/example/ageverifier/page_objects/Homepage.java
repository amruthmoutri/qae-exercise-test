package com.example.ageverifier.page_objects;

import com.example.ageverifier.helpers.CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Homepage {
    AndroidDriver<AndroidElement>  androidDriver;

    public Homepage(AndroidDriver driver){
      this.androidDriver = driver;
      PageFactory.initElements(driver,this);
      CommonUtils.waitForElementToBeDisplayed(androidDriver, 10, enterAgeTextField);
    }

    /**
     * PageObjects for homepoge;
     */
    @FindBy(how = How.ID, using = "editTextNumber")
    WebElement enterAgeTextField;
    @FindBy(how = How.ID, using = "statusTextView")
    WebElement statusTextViewer;
    @FindBy(how = How.ID, using = "button")
    WebElement amIOldEnoughButton;

    /**
     * Methods
     */
    public boolean isAgeFieldTextAreaDisplayed(){
        return enterAgeTextField.isDisplayed();

    }

    public void enterAgeInTextField(int userAge){
        enterAgeTextField.clear();
        enterAgeTextField.click();
        enterAgeTextField.sendKeys(String.valueOf(userAge));

    }

    public String getDefaultInstructionsText(){
        return statusTextViewer.getText();
    }

    public boolean isButtonAndButtonTextDisplayed(String expectedText){
        return amIOldEnoughButton.isDisplayed() &&
                amIOldEnoughButton.getText().equalsIgnoreCase(expectedText);
    }

    public boolean enterAgeAndVerifyText() throws InterruptedException {
        int randomNumber = CommonUtils.generateRandomNumber(50, 0);
        String expectedText = randomNumber < 18 ? "You can't drink yet :(" : "You can drink \uD83C\uDF89";
            if(isAgeFieldTextAreaDisplayed()) {
                enterAgeInTextField(randomNumber);
                androidDriver.hideKeyboard();
            }else{
                String actualText = "randomText";
            }
        amIOldEnoughButton.click();
        new WebDriverWait(androidDriver ,20).until(ExpectedConditions.textToBePresentInElement(statusTextViewer, expectedText));
        return statusTextViewer.getText().equalsIgnoreCase(expectedText);
    }

    public boolean enterRandomStringInTextAreaAndValidateForAppCrash(String randomText){
        String appCrashed="";
        enterAgeTextField.clear();
        enterAgeTextField.click();
        enterAgeTextField.sendKeys(randomText);
        androidDriver.hideKeyboard();
        amIOldEnoughButton.click();
        String activity = androidDriver.currentActivity();
        if (!activity.equals(".MainActivity")) {
             appCrashed = "appCrashed";
        }

        return appCrashed.equalsIgnoreCase("appCrashed");
    }

    public boolean enterNegativeNumberInTheTextArea(int negativeNumber, String expectedTextField) throws InterruptedException {
        enterAgeTextField.clear();
        enterAgeTextField.click();
        enterAgeTextField.sendKeys(String.valueOf(negativeNumber));
        androidDriver.hideKeyboard();
        amIOldEnoughButton.click();
        new WebDriverWait(androidDriver ,10).until(ExpectedConditions.textToBePresentInElement(statusTextViewer, expectedTextField));
        return statusTextViewer.getText().equalsIgnoreCase(expectedTextField);
    }


    public boolean enterNumberInTheTextArea(int number){
        enterAgeTextField.clear();
        enterAgeTextField.click();
        enterAgeTextField.sendKeys(String.valueOf(number));
        androidDriver.hideKeyboard();
        androidDriver.runAppInBackground(Duration.ofSeconds(3));
        CommonUtils.waitForElementToBeDisplayed(androidDriver, 10, enterAgeTextField);
        String enteredNumber = enterAgeTextField.getText();
        return enteredNumber.equalsIgnoreCase(String.valueOf(number));
    }

    /**
     * This method can be removed once the bug fix is avaiabile
     * @return
     * @throws InterruptedException
     */
    public boolean enterRandomAboveFiftyAgeAndVerifyText() throws InterruptedException {
        int randomNumber = CommonUtils.generateRandomNumber(100, 51);
        String expectedText = randomNumber < 18 ? "You can drink \uD83C\uDF89" : "You can't drink yet :(";
        if(isAgeFieldTextAreaDisplayed()) {
            enterAgeInTextField(randomNumber);
            androidDriver.hideKeyboard();
            amIOldEnoughButton.click();
            Thread.sleep(1000);
        }else{
            String actualText = "randomText";
        }
        return statusTextViewer.getText().equalsIgnoreCase(expectedText);
    }


    public String enterNoValueAndCheck() throws InterruptedException {
        String appCrashed="";
        if(isAgeFieldTextAreaDisplayed()) {
            enterAgeTextField.clear();
            androidDriver.hideKeyboard();
            amIOldEnoughButton.click();
            Thread.sleep(1000);
        }
        String activity = androidDriver.currentActivity();
        if (!activity.equals(".MainActivity")) {
            appCrashed = "appCrashed";
        }
        return appCrashed;
    }

    public boolean verifyNumbersAreAllowed() throws InterruptedException {
        boolean areotherKeysAllowed = false;
        if(isAgeFieldTextAreaDisplayed()) {
            enterAgeTextField.clear();
            enterAgeTextField.click();
            androidDriver.pressKey(new KeyEvent (AndroidKey.APOSTROPHE));
        }
        return areotherKeysAllowed = enterAgeTextField.getText().equalsIgnoreCase("")? true : false;
    }





}
