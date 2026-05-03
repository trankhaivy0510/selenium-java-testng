package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Driver;

public class Topic_03_XPath_CSS {
    WebDriver driver;

    @BeforeTest
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void Register_01_Invalid_Email(){
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement emailField = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        emailField.click();
        emailField.sendKeys("vy@@");
        WebElement errorEmailLabel = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        Assert.assertEquals(errorEmailLabel.getText(),"Vui lòng nhập email hợp lệ");
    }

    @Test
    public void Register_02_Incorrect_Confirm_Email(){
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement emailField = driver.findElement(By.xpath("//input[@id='txtEmail']"));
        emailField.click();
        emailField.sendKeys("vy@gmail.com");

        WebElement confirmEmailField = driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        confirmEmailField.click();
        confirmEmailField.sendKeys("vy@gmail.com.vn");
        WebElement errorEmailLabel = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        Assert.assertEquals(errorEmailLabel.getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_03_Invalid_Password(){
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='txtPassword']"));
        passwordField.click();
        passwordField.sendKeys("123");

        WebElement errorPassewordLabel = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        Assert.assertEquals(errorPassewordLabel.getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_04_Invalid_PhoneNumber(){
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebElement phoneField = driver.findElement(By.xpath("//input[@id='txtPhone']"));
        phoneField.click();
        phoneField.sendKeys("093812312");

        WebElement errorPhoneLabel = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        Assert.assertEquals(errorPhoneLabel.getText(),"Số điện thoại phải từ 10-11 số.");
    }
}
