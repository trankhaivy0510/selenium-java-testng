package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Alert_AuthentionAlert {
    WebDriver driver;
    WebDriverWait explicitWait ;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC02_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert =  driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC03_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        // Vừa wait vừa switch to , trả về null nếu không tìm thấy alert
        Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(),"I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You clicked: Cancel");

    }

    @Test
    public void TC04_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        // Vừa wait vừa switch to , trả về null nếu không tìm thấy alert
        Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());

        String value = "Automation testing";
        alert.sendKeys(value);

        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),"You entered: "+value);

    }


    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}
