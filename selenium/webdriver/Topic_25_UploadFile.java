package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class Topic_25_UploadFile {
    WebDriver driver;
    String firstImage = "bear.gif";
    String secondImage = "flower original.jpg";
    String thirdImage = "city.jpg";

    String uploadFilePath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;
    String firstImagePath = uploadFilePath + firstImage;
    String secondImagePath = uploadFilePath + secondImage;
    String thirdImagePath = uploadFilePath + thirdImage;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_SingleFile() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
//        driver.findElement(By.cssSelector("input[type='file']"))
//                .sendKeys("/home/vytran/Documents/selenium-testng/selenium-java-testng/uploadFiles/bear.gif");
//        driver.findElement(By.cssSelector("input[type='file']"))
//                .sendKeys("/home/vytran/Documents/selenium-testng/selenium-java-testng/uploadFiles/flower original.jpg");

        // Chuyển máy Window/Mac/Linux khác vẫn chạy được
        // Chạy với browser nào trên OS nào cũng được

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(secondImagePath);
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thirdImagePath);


        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+firstImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+secondImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+thirdImage+"']")).isDisplayed());

        List<WebElement> btnStart = driver.findElements(By.cssSelector("table button.start"));

        for(WebElement buttonStart: btnStart){
            buttonStart.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+firstImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+secondImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+thirdImage+"']")).isDisplayed());


    }


    @Test
    public void TC02_MultipleFile() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath +"\n" +secondImagePath+"\n" +thirdImagePath);


        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+firstImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+secondImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text() = '"+thirdImage+"']")).isDisplayed());

        List<WebElement> btnStart = driver.findElements(By.cssSelector("table button.start"));

        for(WebElement buttonStart: btnStart){
            buttonStart.click();
            Thread.sleep(1000);
        }

        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+firstImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+secondImage+"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p/a[@title='"+thirdImage+"']")).isDisplayed());


    }
}
