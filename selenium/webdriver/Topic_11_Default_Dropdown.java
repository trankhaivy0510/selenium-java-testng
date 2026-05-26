package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class Topic_11_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01(){
        driver.get("https://rode.com/en-au/support/where-to-buy");
        select = new Select(driver.findElement(By.cssSelector("select#country")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText("Vietnam");
        WebElement textbox = driver.findElement(By.cssSelector("input#map_search_query"));
        textbox.sendKeys("HO CHI MINH");
        driver.findElement(By.cssSelector("button.btn.btn-default")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        List<WebElement> dealerList = driver.findElements(By.xpath("//h3[text()='Dealers']/following-sibling::div/child::div//h4"));
        System.out.println("Số lượng dealer: " + dealerList.size());

        for(WebElement dealer: dealerList){
            System.out.println(dealer.getText());
        }


//        for(int i = 0; i< dealerList.size(); i++){
//            WebElement dealerName = driver.findElement(By.xpath("//h3[text()='Dealers']" +
//                    "/following-sibling::div/child::div["+(i+1)+"]//h4"));
//            System.out.println(dealerName.getText());
//        }
    }

    @Test
    public void TC_02(){
        driver.get("https://www.cgv.vn/default/customer/account/create/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        select = new Select(driver.findElement(By.cssSelector("#r-day")));
        select.selectByVisibleText("01");
        List<WebElement> dateOption = driver.findElements(By.cssSelector("#r-day>option"));
        Assert.assertEquals(dateOption.size(),32);
        System.out.println(dateOption.size());

        select = new Select(driver.findElement(By.cssSelector("#r-month")));
        select.selectByVisibleText("05");

        select = new Select(driver.findElement(By.cssSelector("#r-year")));
        select.selectByVisibleText("2001");


        driver.findElement(By.cssSelector("#female-gender")).click();

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }


}
