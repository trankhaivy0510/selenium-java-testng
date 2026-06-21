package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_Iframe_Frame {
    WebDriver driver ;
    Select select;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Iframe_FormSite() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        //click để hiện iframe
        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();

        //switch qua iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer iframe")));

        //element thuộc trang B
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("North Dorm");
        driver.findElement(By.xpath("//label[text()='Male']")).click();

        //Từ B quay lại A
        driver.switchTo().defaultContent();
        Thread.sleep(3000);
        // Tương tác element trang A
        driver.findElement(By.cssSelector("a.fs-btn.fs-btn--transparent-kashmir")).click();
        driver.findElement(By.cssSelector("button#login")).click();

    }

    @Test
    public void TC02_Iframe_FormSite() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.fb-page.fb_iframe_widget iframe")));
        By followerText = By.xpath("//a[@title='Tôi đi code dạo']/parent::div/following-sibling::div[text()]");
        Assert.assertEquals(driver.findElement(followerText).getText(), "390K followers");
    }

}
