package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Custom_Dropdown {
    WebDriver driver;

    WebDriverWait explicitWait ;
    @BeforeClass
    public void initBrowser(){
        driver = new FirefoxDriver();

//        //Explicit wait
//        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15));
//
//        //Implicit wait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC01() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        selectItemInCustomDropdown("#speed-button>span.ui-selectmenu-icon","#speed-menu>li>div","Fast");
        selectItemInCustomDropdown("#speed-button>span.ui-selectmenu-icon","#speed-menu>li>div","Medium");
        selectItemInCustomDropdown("#speed-button>span.ui-selectmenu-icon","#speed-menu>li>div","Faster");
    }

    @Test
    public void TC02() throws InterruptedException {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInCustomDropdown("div.btn-group","ul.dropdown-menu>li","First Option");
        selectItemInCustomDropdown("div.btn-group","ul.dropdown-menu>li","Second Option");
        selectItemInCustomDropdown("div.btn-group","ul.dropdown-menu>li","Third Option");
    }

    @Test
    public void TC03() throws InterruptedException {

    }


    private void selectItemInCustomDropdown(String parentCss, String childCss, String textItem) throws InterruptedException {
        WebElement dropdownlist = driver.findElement(By.cssSelector(parentCss));
        explicitWait.until(ExpectedConditions.elementToBeClickable(dropdownlist));
        dropdownlist.click();
        Thread.sleep(2000);

        //Chờ tất cả item load ra presence - xuất hiện trong html
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

        //click vào item mong đợi
        List<WebElement> listItem = driver.findElements(By.cssSelector(childCss));
        for(WebElement a: listItem){
            if (a.getText().equals(textItem)){
                a.click();
                break;
            }
        }
    }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
