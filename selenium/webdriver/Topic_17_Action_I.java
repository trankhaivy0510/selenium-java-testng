package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Action_I {
    WebDriver driver ;
    Actions action ;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        action.moveToElement(ageTextbox).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC02_Hover_Myntra() throws InterruptedException {
        driver.get("https://www.myntra.com/");

        action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
        Thread.sleep(2000);
//        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Personal Care']")).click();
        action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Personal Care']"))).perform();

    }

    @Test
    public void TC03_Hover_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        Thread.sleep(2000);
        action.moveToElement(driver.findElement(By.xpath("//a[@title='VPP - Dụng Cụ Học Sinh']"))).perform();
        Thread.sleep(2000);
        action.click(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Gôm - Tẩy']"))).perform();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Gôm - tẩy']")).isDisplayed());

    }


    @AfterClass
    public void cleanBrowser(){
        driver.quit();
    }

}
