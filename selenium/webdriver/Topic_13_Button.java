package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Button {
    WebDriver driver;

    WebDriverWait explicitWait ;
    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01(){
        driver.get("https://www.fahasa.com/?srsltid=AfmBOoqed-AV6pWnkiZQ1gDox5MQ9OGVhbO8n2WOncYwMScHTBaP3-dV");
        driver.findElement(By.cssSelector("div.fhs_top_account_button")).click();

        WebElement loginButton = driver.findElement(By.xpath("//div[@class='fhs-btn-box']/button[@title='Đăng nhập']"));
        Assert.assertFalse(loginButton.isEnabled());
        String loginBackgroundColor = loginButton.getCssValue("background-color");
        System.out.println(loginBackgroundColor);

        Color loginColor = Color.fromString(loginBackgroundColor);
        Assert.assertEquals(loginColor.asHex().toLowerCase(),"#000000");

        driver.findElement(By.cssSelector("#login_username")).sendKeys("vytran@gmail.com");
        driver.findElement(By.cssSelector("#login_password")).sendKeys("vytran@gmail.com");

        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color"))
                .asHex().toUpperCase(),"#C92127");
    }


    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
