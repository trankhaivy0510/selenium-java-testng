package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.*;

public class Topic_2 {
    WebDriver driver;

    @BeforeTest
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        WebElement emailField = driver.findElement(By.id("Email"));
        emailField.sendKeys("");
    }

    @Test
    public void Register(){
        driver = new ChromeDriver();
        driver.get("https://www.nopcommerce.com/vi/register?returnUrl=%2Fvi%2Fget-started");
        WebElement lastNameField = driver.findElement(By.id("LastName"));
        lastNameField.click();
        lastNameField.sendKeys("Tran");
    }


    @Test
    public void Login(){

    }

    @Test
    public void TCS03_Relative_Locator (){
        WebElement rememberMeCheckbox = driver.findElement(By.cssSelector("input#RememberMe"));
        WebElement forgotPasswordLinkBy = driver.findElement(By.cssSelector("a[href='/passwordrecovery']"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.login-button"));
        WebElement passwordField = driver.findElement(By.cssSelector("input#Password"));

        WebElement rememberMeLabel = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButton)
                .below(passwordField)
                .toRightOf(rememberMeCheckbox)
                .toLeftOf(forgotPasswordLinkBy)
        );

//        System.out.println(rememberMeLabel.toString());

    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
