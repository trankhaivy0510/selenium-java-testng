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

import java.time.Duration;
import java.util.List;

public class Topic_21_Fixed_Popup {
    WebDriver driver ;
    Select select;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private void setImplicitTimeout(long timeInSecond) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01_Fixed_Popup_INDOM() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        WebElement registerPopUP = driver.findElement(By.cssSelector("div.modal-content"));

        if(registerPopUP.isDisplayed()){
            driver.findElement(By.cssSelector("button.close")).click();
        }
        driver.findElement(By.cssSelector("input.search-form")).sendKeys("Khóa học Lập Trình PLC Mitsubishi");
        driver.findElement(By.cssSelector("i.fa-search")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("h3.title>a")).getText(),"Khóa học Lập Trình PLC Mitsubishi");

    }


    @Test
    public void TC02_Fixed_Popup_VNK_INDOM() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        WebElement mktPopUp = driver.findElement(By.cssSelector("div.pum-container"));

        if(mktPopUp.isDisplayed()){
            driver.findElement(By.cssSelector("button.pum-close")).click();
        }

        driver.findElement(By.cssSelector("button.btn-danger")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content>h1")).getText(),"Lịch Khai Giảng Trung Tâm VNK EDU");
    }

    @Test
    public void TC03_Fixed_Popup_INDOM() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        WebElement popupContainer = driver.findElement(By.cssSelector("div.pop-container"));

        if(popupContainer.isDisplayed()){
            driver.findElement(By.cssSelector("span.close_icon")).click();
        }

        new Select(driver.findElement(By.cssSelector("select#selectLang"))).selectByVisibleText("日本語");

        // sau khi load lại trạng mặc dù locator vẫn i chang nhưng vẫn phải find element lại do đã thay đổi trạng thái
        popupContainer = driver.findElement(By.cssSelector("div.pop-container"));
        if(popupContainer.isDisplayed()){
            driver.findElement(By.cssSelector("span.close_icon")).click();
        }
    }

    @Test
    public  void TC04_Fixed_Popup_TIKI_NOTINDOM() throws InterruptedException {
        driver.get("https://tiki.vn/");
        setImplicitTimeout(5);
        List<WebElement> popupContainer = driver.findElements(By.cssSelector("div#VIP_BUNDLE"));

        setImplicitTimeout(30);
        if(popupContainer.size()>0 && popupContainer.get(0).isDisplayed()){
            driver.findElement(By.cssSelector("img[alt='close-icon']")).click();
            System.out.println("Pop up is displayed");
        }else {
            System.out.println("Pop up is not displayed");
        }
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Email không được để trống']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Mật khẩu không được để trống']")).isDisplayed());
        driver.findElement(By.cssSelector("img.close-img")).click();

        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);

    }

    @Test
    public  void TC05_NgoaiNgu24h_NOTINDOM() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        Thread.sleep(2000);

        List<WebElement> loginPopUp = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));
        Assert.assertTrue(loginPopUp.size()>0 && loginPopUp.get(0).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("automation");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("111111");
        driver.findElement(By.xpath("//form//button[text()='Đăng nhập']")).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");
        Thread.sleep(6000);

        driver.findElement(By.cssSelector("svg[data-testid='CloseIcon']")).click();
        Thread.sleep(3000);
        loginPopUp = driver.findElements(By.cssSelector("div.MuiDialog-container>div"));
        Assert.assertTrue(loginPopUp.size()==0 && loginPopUp.isEmpty());
    }
}
