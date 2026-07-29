package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Shadow_DOM {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_shadowDOM(){
        driver.get("https://automationfc.github.io/shadow-dom/");

        WebElement shadowHostFirst = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootFirst = shadowHostFirst.getShadowRoot();
        String someText = shadowRootFirst.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);

        WebElement shadowHostSecond = shadowRootFirst.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext shadowRootSecond = shadowHostSecond.getShadowRoot();
        String nestedText = shadowRootSecond.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        System.out.println(nestedText);

        WebElement input = shadowRootFirst.findElement(By.cssSelector("input[type='text']"));
        input.sendKeys("automation testing");
    }

    @Test
    public void TC02_shadowDOM(){
        driver.get("https://shop.polymer-project.org/");

        WebElement shopApp = driver.findElement(By.cssSelector("shop-app[page='home']"));
        SearchContext shadowRootFirst = shopApp.getShadowRoot();

        SearchContext shadowRootSecond = shadowRootFirst.findElement(By.cssSelector("shop-home.iron-selected")).getShadowRoot();

        shadowRootSecond.findElement(By.cssSelector("shop-button>a[aria-label=\"Men's Outerwear Shop Now\"]")).click();

        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void TC03_shadowDOM() throws InterruptedException {
        driver.get("https://developer.salesforce.com/");

        WebElement shopApp = driver.findElement(By.cssSelector("div.global-nav-container>hgf-c360nav"));
        SearchContext shadowRootFirst = shopApp.getShadowRoot();

        SearchContext shadowRootSecond = shadowRootFirst.findElement(By.cssSelector("div.desktop-cta>hgf-button[data-tracking-type=\"cta-utility\"]")).getShadowRoot();

        Thread.sleep(3000);

//        shadowRootSecond.findElement(By.cssSelector("a[class='hgf-button']")).click();

        js.executeScript("arguments[0].click();", shadowRootSecond.findElement(By.cssSelector("a[class='hgf-button']")));

        System.out.println(driver.getCurrentUrl());
    }
}
