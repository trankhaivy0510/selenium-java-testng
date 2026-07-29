package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

public class Topic_22_Window_Tab_I {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Github(){
        driver.get("https://automationfc.github.io/basic-form/");

        // Lấy ra window id của driver hiện tại đang đứng
        String githubId = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("automation");

//        // Lấy ra tất cả window id
//        switchToWindowById(githubId);
//
//        System.out.println(driver.getTitle());

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        switchToWindowByTitle("Facebook");

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("automation");
        driver.findElement(By.cssSelector("input[name='pass']")).sendKeys("automation123");

        closeAllWindow(githubId);

    }

    // Chỉ áp dụng khi chỉ có 2 window/tab
    private void switchToWindowById(String githubId) {
        Set<String> allIDs = driver.getWindowHandles();

        for(String id: allIDs){
            System.out.println(id);
            if(!id.equals(githubId)){
                driver.switchTo().window(id);
            }
        }
    }


    // Dùng cho nhiều window/tab trở lên
    private void switchToWindowByTitle(String title) {
        Set<String> allIDs = driver.getWindowHandles();

        for(String id: allIDs){
            System.out.println("Window id" + id);
            driver.switchTo().window(id);
            String pageTitle = driver.getTitle();
            System.out.println("Window title" + id);
            if(pageTitle.equals(title)){
                break;
            }
        }
    }

    public void closeAllWindow(String windowID){
        Set<String> allIDs = driver.getWindowHandles();

        for(String id: allIDs){
            if(!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }

        driver.switchTo().window(windowID);

    }

    @Test
    public void TC02_Techpanda(){
        driver.get("https://live.techpanda.org/index.php/mobile.html");


        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        String mobileWindowID =  driver.getWindowHandle();
        switchToWindowByTitle(mobileWindowID);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        // Lưu ý mặc dù là Close window rồi nhưng vẫn phải switch để driver về lại page trc
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();

    }

    @Test
    public void TC03_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        String courseWindowID =  driver.getWindowHandle();
        driver.findElement(By.cssSelector("i.fa-sign-in")).click();
        switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        Assert.assertEquals(driver.findElement(By.cssSelector("header>h1")).getText(),"DCE Login Portal");
        closeAllWindow(courseWindowID);
        Assert.assertEquals(driver.findElement(By.cssSelector("p.sam-wait__message")).getText(),"Authentication was not successful. Please try again.");

        driver.findElement(By.cssSelector("button.sam-wait__close")).click();

        String courseName = "Data Science: An Artificial Ecosystem";
        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys(courseName);
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2026");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");

        driver.findElement(By.cssSelector("button#search-button")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(),courseName);



    }

    @Test
    public void TC04_Selenium_4x() throws InterruptedException {
    // Trang A
        driver.get("https://live.techpanda.org/index.php/mobile.html");
    // Trang B
        driver.switchTo().newWindow(WindowType.TAB).get("https://admin-demo.nopcommerce.com/login");
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("automation");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("automation123");
        driver.findElement(By.cssSelector("button[type=submit]")).click();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.cssSelector("button[title='Compare']")).click();



    }


}
