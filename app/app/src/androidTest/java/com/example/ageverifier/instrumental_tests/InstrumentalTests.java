package com.example.ageverifier.instrumental_tests;

import com.example.ageverifier.Driver;
import com.example.ageverifier.page_objects.Homepage;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstrumentalTests {

    AndroidDriver<AndroidElement>  AndroidDriver;
    private String debug = "[Debug]";
    private String appName =  "";
    private String host = "";
    private String port = "";

    @BeforeMethod()
    public void beforeMethod(ITestContext context) throws IOException {
        appName = context.getCurrentXmlTest().getParameter("appname");
        host = context.getCurrentXmlTest().getParameter("host");
        port = context.getCurrentXmlTest().getParameter("port");
        AndroidDriver = new Driver().webDriver(appName, host , port);
    }

    @Test(description ="Verify the app is installed")
    public void TC_01(){
        boolean isAgeFieldDispayed = false;
        boolean isAppInstalled= false;
        try {
            System.out.println(debug + "01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    , isAppInstalled, equalTo(true));

        }catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }
    @Test(description ="Launch the app, and verify the age field is displayed")
     public void TC_02(){
        boolean isAgeFieldDispayed = false;
        boolean isAppInstalled= false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));

            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            assertThat("HomePage might not be loaded or change in locator"
                    ,isAgeFieldDispayed, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }


    @Test(description ="Launch the app, and verify the defaults text are displayed")
    public void TC_03(){
        String expectedText = "insert the age and press the button";
        String actualText =  "";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather default text is displayed before data ");
            actualText = homepage.getDefaultInstructionsText();
            System.out.println(debug +"03.Actual Text - " + actualText + "\n Expected Text - " + expectedText );

            assertThat("Check if the actual and expected text are displayed before entering " +
                    "any data ", expectedText, equalTo(actualText));
        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    @Test(description ="Launch the app, and verify the button is displayed along with button text")
    public void TC_04(){
        boolean isButtonAndButtonTextDisplayed = false;
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather default button text and button are displayed ");
            isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"03.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button not displayed"
                    ,isButtonAndButtonTextDisplayed, equalTo(true));
        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    @Test(description ="Launch the app, and enter random age and check the expected text ")
    public void TC_05(){
            String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Enter age and verify if user is allowed to drink text is displayed");
            boolean textFieldText = homepage.enterAgeAndVerifyText();
            System.out.println(debug +"07.text and age are displayed ?" + textFieldText);

            assertThat("Text field is displaying the right text for the age  ? "
                    ,textFieldText, equalTo(true));

        } catch(Exception e){
            Assert.fail();
            System.out.println(e);
        } finally {
            AndroidDriver.quit();
        }
    }

//    /**
//     * This test needs to be updated after the bug fix is there..
//     */
    @Test(description ="Launch the app, and enter a negative number in the text feild area" )
    public void TC_06(ITestContext context){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Entered negative number in the text area ");
            boolean textFieldText =  homepage.enterNegativeNumberInTheTextArea(-4,
                    "HTTP Exception 500 Internal Server Error");
            System.out.println(debug +"07.APP has crashed?" + textFieldText);

            assertThat("Text field is displaying the right text for the age  ? "
                    ,textFieldText, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }


    /**
     * This test needs to be updated after the bug fix is there..
     */
    @Test(description ="Launch the app, and enter long number in the text feild area" )
    public void TC_07(){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed = homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Enter random text in the fied area");
            boolean textFieldText =  homepage.enterRandomStringInTextAreaAndValidateForAppCrash("2342388798784");
            System.out.println(debug +"07.Entered text in the fied area and checking if app crash ? " + textFieldText);

            assertThat("App has crashed to long number? "
                    ,textFieldText, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    @Test(description ="Launch the app, enter a number in the text feild area and minimise the app and relaunch it to verify text is still there" )
    public void TC_08(){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Run app in the background and relaunch the app for the text to be displayed "
                    + isButtonAndButtonTextDisplayed);
            boolean actualAndExpectedText =  homepage.enterNumberInTheTextArea(23);
            System.out.println(debug +"07.App in the background has relaunced and text is displayed?"
                    + actualAndExpectedText);

            assertThat("Number not saving in memory after running in backgroud? "
                    ,actualAndExpectedText, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }


    /**
     * This test needs to be removed after the bug fix is there..
     */
    @Test(description ="Launch the app, and enter random age b/w 51 and 100 and check the expected text ")
    public void TC_09(){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"04.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Enter age and verify if user is allowed to drink text is displayed");
            boolean textFieldText = homepage.enterRandomAboveFiftyAgeAndVerifyText();
            System.out.println(debug +"07.text and age are displayed ?" + textFieldText);

            assertThat("Text field is displaying the right text for the age  ? "
                    ,textFieldText, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    /**
     * This test needs to be removed after the bug fix is there..
     */
    @Test(description ="Launch the app, and don't enter any value in the text field")
    public void TC_10(){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Don't enter any age and verify if user is allowed to drink text is displayed");
            String verifyAppCrashed = homepage.enterNoValueAndCheck();
            System.out.println(debug +"07.text and age are displayed ?" + verifyAppCrashed);

            assertThat("App has crashed  ? "
                    ,verifyAppCrashed, equalTo("appCrashed"));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    @Test(description ="Launch the app, and enter value through Key pad press")
    public void TC_11(){
        String expectedText = "Am I old enought to drink?";
        boolean isAppInstalled = false;
        try{
            System.out.println(debug +"01.Verify the app is installed");
            isAppInstalled = AndroidDriver.isAppInstalled("com.example.ageverifier");
            assertThat("App is not installed"
                    ,isAppInstalled, equalTo(true));
            Homepage homepage = new Homepage(AndroidDriver);
            System.out.println(debug +"02.Check weather age field is displayed ");
            boolean isAgeFieldDispayed =  homepage.isAgeFieldTextAreaDisplayed();
            System.out.println(debug +"03.Age field is displayed? "+ isAgeFieldDispayed);

            System.out.println(debug +"04.Check weather default button text and button are displayed ");
            boolean isButtonAndButtonTextDisplayed = homepage.isButtonAndButtonTextDisplayed(expectedText);
            System.out.println(debug +"05.Default button text and button are displayed? "+ isButtonAndButtonTextDisplayed);

            assertThat("Button and Age fields are displayed ? "
                    ,isAgeFieldDispayed&&isButtonAndButtonTextDisplayed, equalTo(true));

            System.out.println(debug +"06.Enter a random symobol through keypad");
            boolean verifyNumbersOnlyAllowed = homepage.verifyNumbersAreAllowed();
            System.out.println(debug +"07.Verify numbers are only allowed ?" + verifyNumbersOnlyAllowed);

            assertThat("Other things are also allowed "
                    ,verifyNumbersOnlyAllowed, equalTo(true));

        } catch(Exception e){
            System.out.println(e);
            Assert.fail();
        } finally {
            AndroidDriver.quit();
        }
    }

    /**
     * Bugs --
     * allowing negative number in the text field area shows server side crash message.
     * ideally we should be displaying an alert showing to enter valid number
     *
     * allowing long numbers in the text area. We usually have age with max of 3 number, so
     * its better if we add in the UI with tag attribute such as maxlength:3;
     *
     * Age b/w 51 to 100 gives an wrong value
     *
     * Enterting a no value in the text filed breaks the app.
     * Ideally we should can enable the button only value is entered, we can do by event listners
     *
     */


}


