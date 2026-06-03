package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_14_Default_Checkbox_Radiobutton {
        WebDriver driver;
        WebDriverWait explicitWait ;
        JavascriptExecutor jsExecutor;

        @BeforeClass
        public void initBrowser(){
            driver = new ChromeDriver();
            jsExecutor = (JavascriptExecutor) driver;
            explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }

        public void scrollToElement(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                    element);
        }

        @Test
        public void TC01(){
            driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

            //Hàm isSelected chỉ áp dụng cho input
            Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input")).isEnabled());
            Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isEnabled());


            Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Leather trim']/preceding-sibling::span/input")).isSelected());
            Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::span/input")).isSelected());

            By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

            scrollToElement(driver.findElement(dualZoneCheckbox));
            explicitWait.until(ExpectedConditions.elementToBeClickable(dualZoneCheckbox));
            if (!driver.findElement(dualZoneCheckbox).isSelected()){
                driver.findElement(dualZoneCheckbox).click();
            }else {
                System.out.println("Không click");
            }
            Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        }

        @Test
        public void TC02(){
            driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
            By twoPetrolRadioButton = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
            scrollToElement(driver.findElement(twoPetrolRadioButton));
            explicitWait.until(ExpectedConditions.elementToBeClickable(twoPetrolRadioButton));
            if(!driver.findElement(twoPetrolRadioButton).isSelected()){
                driver.findElement(twoPetrolRadioButton).click();
            };
            Assert.assertTrue(driver.findElement(twoPetrolRadioButton).isSelected());

        }

        @Test
        public void TC03(){
            driver.get("https://automationfc.github.io/multiple-fields/");

            //Select all checkboxes
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));

            //Duyệt qua tất cả kiểm tra nếu chưa chọn thì click
            for (WebElement checkbox : checkboxes) {
                if(!checkbox.isSelected()){
                    checkbox.click();
                }
            }

            for (WebElement checkbox : checkboxes) {
                if(checkbox.isSelected()){
                    checkbox.click();
                }
            }


            for (WebElement checkbox : checkboxes) {
                Assert.assertFalse(checkbox.isSelected());
            }

            for (int i = 0; i<checkboxes.size(); i++) {
                int random = new Random().nextInt(checkboxes.size());
                WebElement checkboxElement = checkboxes.get(random);
                checkboxElement.click();
            }
        }

        @Test
        public void TC04() throws InterruptedException {
            driver.get("https://login.ubuntu.com/");
            //1. Dùng thẻ input để click -> fail do có label che
            // Dùng thẻ input để verify -> pass


            //2.
            // Dùng thẻ label để click -> pass
            // Dùng thẻ label để verify -> fail (do isSelected chỉ dùng cho option/input)

            //3
            //Dùng thẻ input để verify -> pass
            //Dùng thẻ label để click -> pass
            // -> phải khai báo 2 locator => code khó bảo trì, khó đọc

            //4. Dùng JS Executor để click
            By newUserRadioInput = By.cssSelector("input#id_new_user");
            jsExecutor.executeScript("arguments[0].click()",driver.findElement(newUserRadioInput));
            Assert.assertTrue(driver.findElement(newUserRadioInput).isSelected());


            By termCheckbox = By.cssSelector("input#id_accept_tos");
            jsExecutor.executeScript("arguments[0].click()",driver.findElement(termCheckbox));
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(termCheckbox).isSelected());

        }

        @Test
        public void TC05() throws InterruptedException {
            driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

            By hcmRadioButton = By.xpath("//div[@data-value='Hồ Chí Minh']");

            driver.findElement(hcmRadioButton).click();
            Assert.assertEquals(driver.findElement(hcmRadioButton).getAttribute("aria-checked"),"true");
        }

    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }

    }

