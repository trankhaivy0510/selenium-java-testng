package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_10_TextBox_TextArea {
    WebDriver driver;
    Random random;

    String firstname, lastname, email, postfixEmail;

    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();
        random = new Random();

        firstname = "Vy";
        lastname = "Tran";
        postfixEmail = "@gmail.com";
        email = firstname + lastname + random.nextInt(999) + postfixEmail;
    }

    @Test
    public void TCS_0_GetSourceCode() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
//        driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
        driver.findElement(By.xpath("//button[@title='Register']")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"Thank you for registering with Main Website Store.");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
//        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p"))
//                .getText().contains(firstname + " " + lastname));
        Assert.assertTrue(contactInfo.contains(firstname)&&contactInfo.contains(lastname));


        driver.findElement(By.cssSelector("li.current")).click();
        Assert.assertEquals(firstname,driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"));

        driver.findElement(By.cssSelector("a.skip-link.skip-account")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();



    }

    @Test
    public void TCS_02_TextArea(){
        driver.get("https://live.techpanda.org/index.php/mobile/samsung-galaxy.html");
        driver.findElement(By.cssSelector("ul.toggle-tabs li.last")).click();

    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
