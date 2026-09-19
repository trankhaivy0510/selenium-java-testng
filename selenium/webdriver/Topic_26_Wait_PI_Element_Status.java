package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Wait_PI_Element_Status {
    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();

        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
    }


    @Test
    public void TC_01_Visible(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Điều kiện 1 element xuất hiện trên UI và trong HTML
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed());


    }

    @Test
    public void TC_02_InvisibleI_Element_Found(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("ul.form-list input[type='email']")).sendKeys("vy@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // Điều kiện 2 element không có trên UI nhưng có trong HTML
        // Email address error mesage không có trên Ui nhưng vẫn còn trong HTML
        driverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));
        Assert.assertFalse(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed());
    }

    @Test
    public void TC_03_InvisibleII_Element_NotFound(){
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Điều kiện 2+3: Elemnet ko có trong UI và cũng không có trong HTML
        // Email Address error message không có trên UI và không còn trong HTML

//        driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        // Step này chạy lâu vì cầng element (findElement) mà element lại không có trong HTML
        // Chờ và tìm đi tìm lại cho đến khi hết timeout

        // Thứ tự khi có cả implicit với WebDriverWait:
        // 1: Ta xét findElement trước -> wait 12s
        // 2. Sau đó mới invisibilityOf(..) -> wait 10s
        driverWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));

//        Assert.assertFalse(driver.findElement(By.cssSelector("div#advice-required-entry-email")).isDisplayed());
    }

    @Test
    public void TC_03_Presence(){
        // Điều kiện 1: Element có trên UI và có trong HTML
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        // Điều kiện 2: Element không có trên UI nhưng có trong HTML
        driver.findElement(By.cssSelector("ul.form-list input[type='email']")).sendKeys("vy@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        // Miễn sao có xuất hiện trong HTML là presence
    }

    @Test
    public void TC_04_Staleness(){
        // Tại thời điểm A element xuất hiện = lưu element
        // Tại thời điểm B element không xuất hiện trong HTML nữa = dùng element đã lưu tại thời điểm A check
        // element đó staleness tại thời điểm B

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement emailErrorMessage = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.findElement(By.cssSelector("ul.form-list input[type='email']")).sendKeys("vy@gmail.com");
        driver.findElement(By.cssSelector("ul.form-list input[type='password']")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        
        driverWait.until(ExpectedConditions.stalenessOf(emailErrorMessage));

    }

    @Test
    public void TC_05_(){

    }
}
