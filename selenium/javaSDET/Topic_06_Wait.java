package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Wait {
    WebDriver driver;

    WebDriverWait webDriverWait;

    FluentWait<WebDriver> fluentWait;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();

        //Explicit wait
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //Fluent wait
        fluentWait = new FluentWait<WebDriver>(driver);
    }

    @Test
    public void TC01(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

    }

    @Test
    public void TC02(){

        //Wait cho đến khi xuất hiện phần tử
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
    }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
