package javaSDET;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;

public class Topic_07_WebElement_Commands {
    WebDriver webDriver;
    WebElement webElement;

    @Test
    public void TCS_01(){
        webElement.click();
        webDriver.findElement(By.id(""));
        webElement.click();

        webElement.clear();//**
        webElement.sendKeys("a@gmail.com");//**
        webElement.sendKeys(Keys.ENTER);
        webElement.submit();

        webElement.isDisplayed();//**

        Assert.assertFalse(webElement.isDisplayed());
        Assert.assertTrue(webElement.isDisplayed());

        webElement.getAttribute("placeholder");
        webElement.getText();//**


        webElement.getCssValue("font-size");//**

        //Lấy chiều rộng/cao của element
        Dimension elementDimension = webElement.getSize();

        //lấy vị trí so với viewport
        webElement.getLocation();


        Rectangle rectangle = webElement.getRect();

        rectangle.getHeight();
        rectangle.getX();
        rectangle.getY();
        rectangle.getWidth();
        rectangle.getDimension();
        rectangle.getPoint();

        //Lấy ra tag name element A
        String tagName = webDriver.findElement(By.id("")).getTagName();

        //B cùng tagname với element A
        webDriver.findElement(By.xpath("//"+tagName+"[@id='LastName']"));

        //
        webElement.getAccessibleName();
        webElement.getAriaRole();
        webElement.getDomAttribute("");
        webElement.getDomProperty("");

        //pop up
        webElement.getShadowRoot(); //**

        //HTLM report Framework
        webElement.getScreenshotAs(OutputType.FILE);//**
        webElement.getScreenshotAs(OutputType.BYTES);
        webElement.getScreenshotAs(OutputType.BASE64);//**
    }
}
