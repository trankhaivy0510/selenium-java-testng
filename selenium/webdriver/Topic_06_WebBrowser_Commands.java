package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    WebDriver driver;

    @Test
    public void TC_01 (){
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");

        WebElement registerIcon = driver.findElement(By.xpath("//a[@class='ico-register']"));
        registerIcon.click();
        String url = driver.getCurrentUrl();
        System.out.println(url);
        Assert.assertEquals(url,"https://demo.nopcommerce.com/register?returnUrl=%2F");
//        driver.manage().window().maximize();
//        driver.manage().window().minimize();
//        driver.manage().window().fullscreen();
        driver.manage().window().getSize();
        System.out.println(driver.manage().window().getSize());

        driver.manage().window().setSize(new Dimension(1900,782));

        driver.manage().window().setPosition(new Point(80,80));

        //đóng tất cả windows/tab
        driver.quit();

        //đóng active windows/tab
        driver.close();

        //Selenium version 3
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.MINUTES);

        //Selenium versione 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Lấy ra ID cửa sổ đang mở
        driver.getWindowHandle();

        //ID của tất cả cửa sổ đang mở
        driver.getWindowHandles();

        WebDriver.Timeouts timeout = driver.manage().timeouts();

        timeout.implicitlyWait(Duration.ofSeconds(15));

        driver.manage().logs();
        System.out.println(driver.manage().logs());

        // Lấy hết tất cả cookie
        Set <Cookie> cookies = driver.manage().getCookies();

        for (Cookie cookie: cookies){
            driver.manage().deleteCookie(cookie);
        }

        //
        driver.navigate().to("");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();


        //Alert
        WebDriver.TargetLocator alert = driver.switchTo();
        alert.alert().accept();
        alert.alert().dismiss();

    }
}
