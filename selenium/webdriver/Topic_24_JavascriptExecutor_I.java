package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class Topic_24_JavascriptExecutor_I {
    WebDriver driver;
    JavascriptExecutor jsExecutor ;

    @BeforeClass
    public void beforeClass(){
//        FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("-profile");
//        options.addArguments("/home/vytran/snap/firefox/common/.mozilla/firefox/ylifljen.default");

        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC01_Fahasa(){
        driver.get("https://www.fahasa.com/");

        // Dùng ép kiểu
//        String fahasa = (String) jsExecutor.executeScript("return document.documentElement.innerText;");
//        System.out.println(fahasa);

        // Dùng hàm executeForBrowser
        String fahasa = (String) executeForBrowser("return document.documentElement.innerText;");
        System.out.println(fahasa);

        // Dùng getInnerText
        getInnerText();
    }

    @Test
    public void TC02_TechPanda(){
        navigateToUrlByJS("https://live.techpanda.org/");
        String getDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(getDomain,"live.techpanda.org");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[@title='Samsung Galaxy']/following-sibling::div/div[@class='actions']/button[@title='Add to Cart']");
        clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div/div[@class='actions']/button[@title='Add to Cart']");
        String text = getInnerText();
        // Tương đối
        // Assert.assertTrue(text.contains("Samsung Galaxy was added to your shopping cart."));

        //Tuyệt đối
//        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']"),"Samsung Galaxy was added to your shopping cart.");

        // Tương đối
        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");


        scrollToElementOnTop("//input[@type='email']");
        hightlightElement("//input[@type='email']");
        sendkeyToElementByJS("//input[@type='email']","Vy"+ new Random().nextInt(9999)+"@gmail.com");

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']"),"Thank you for your subscription.");
    }

    @Test
    public void TC03_Ubuntu(){
        driver.get("https://login.ubuntu.com/");
        sleepInSecond(3);
        driver.findElement(By.xpath("//span[text()='Log in']")).click();

        String validationMessage = getElementValidationMessage("//div[@class='login-form']//input[@id='id_email']");
        Assert.assertEquals(validationMessage,"Please fill out this field.");

        driver.findElement(By.xpath("//div[@class='login-form']//input[@id='id_email']")).sendKeys("aaaa@gmail.com");
        String passwordValidationMsg = getElementValidationMessage("//div[@class='login-form']//input[@type='password']");
        driver.findElement(By.xpath("//span[text()='Log in']")).click();
//        Assert.assertEquals(passwordValidationMsg,"Please fill out this field.");

        //Hoặc dùng getDomProperties
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='login-form']//input[@type='password']")).
                getDomProperty("validationMessage"),"Please fill out this field.");

    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].textContent;", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
