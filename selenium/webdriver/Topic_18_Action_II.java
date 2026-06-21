package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class Topic_18_Action_II {
    WebDriver driver ;
    Actions action ;
    String osName = System.getProperty("os.name");
    Keys keys;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Click_And_Hover(){
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        action.clickAndHold(listNumber.get(0))
                .moveToElement(listNumber.get(3))
                .release()
                .perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);

    }

    @Test
    public void TC02_Click_And_Hover_Random(){

        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> listNumber = driver.findElements(By.cssSelector("ol#selectable>li"));
        keys = osName.startsWith("Windows") ? Keys.CONTROL : Keys.COMMAND;
        action.keyDown(Keys.CONTROL).perform();

        action.click(listNumber.get(1))
                .click(listNumber.get(5))
                .click(listNumber.get(7))
                .click(listNumber.get(9))
                .perform();

        action.keyUp(Keys.CONTROL).perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 4);


    }

    @Test
    public void TC03_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }

    @Test
    public void TC04_RightClick(){
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover")).isDisplayed());

        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        driver.switchTo().alert().accept();

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }
}
