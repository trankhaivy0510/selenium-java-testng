package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_ExcerciseI {
    WebDriver driver;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC1_isDisplayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextBox = driver.findElement(By.id("mail"));
        if (emailTextBox.isDisplayed()) {
            System.out.println("Email is isDisplayed");
            emailTextBox.sendKeys("Automation testing");
        } else {
            System.out.println("Email is not isDisplayed");
        }

        WebElement eduTextBox = driver.findElement(By.id("edu"));
        if (eduTextBox.isDisplayed()) {
            System.out.println("Edu is isDisplayed");
            eduTextBox.sendKeys("OU-HCM");
        } else {
            System.out.println("Edu is not isDisplayed");
        }

        WebElement under18RadioButton = driver.findElement(By.id("under_18"));
        if (under18RadioButton.isDisplayed()) {
            System.out.println("under18RadioButton is isDisplayed");
            under18RadioButton.click();
        } else {
            System.out.println("under18RadioButton is not isDisplayed");
        }
    }


    @Test
    public void TC2_isEnabled(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement emailTextBox = driver.findElement(By.id("mail"));
        if(emailTextBox.isEnabled()){
            System.out.println("Email is isEnabled");
            emailTextBox.sendKeys("Automation testing");
        }else{
            System.out.println("Email is not isEnabled");
        }

        WebElement eduTextBox = driver.findElement(By.id("edu"));
        if(eduTextBox.isEnabled()){
            System.out.println("Edu is isEnabled");
            eduTextBox.sendKeys("OU-HCM");
        }else{
            System.out.println("Edu is not isEnabled");
        }

        WebElement under18RadioButton = driver.findElement(By.id("under_18"));
        if(under18RadioButton.isEnabled()){
            System.out.println("under18RadioButton is isEnabled");
            under18RadioButton.click();
        }else{
            System.out.println("under18RadioButton is not isEnabled");
        }

        WebElement job1 = driver.findElement(By.id("job1"));
        if(job1.isEnabled()){
            System.out.println("job1 is isEnabled");
        }else{
            System.out.println("job1 is not isEnabled");
        }

        WebElement job2 = driver.findElement(By.id("job2"));
        if(job1.isEnabled()){
            System.out.println("job2 is isEnabled");
        }else{
            System.out.println("job2 is not isEnabled");
        }

        WebElement developmentCheckBox = driver.findElement(By.id("development"));
        if(developmentCheckBox.isEnabled()){
            System.out.println("developmentCheckBox is isEnabled");
        }else{
            System.out.println("developmentCheckBox is not isEnabled");
        }

        WebElement passwordInput = driver.findElement(By.cssSelector("input[disabled='disabled']"));
        if(passwordInput.isEnabled()){
            System.out.println("passwordInput is isEnabled");
        }else{
            System.out.println("passwordInput is not isEnabled");
        }

        WebElement radioButtonDisabled = driver.findElement(By.id("radio-disabled"));
        if(radioButtonDisabled.isEnabled()){
            System.out.println("radioButtonDisabled is isEnabled");
        }else{
            System.out.println("radioButtonDisabled is not isEnabled");
        }

        WebElement bioTextBox = driver.findElement(By.id("bio"));
        if(bioTextBox.isEnabled()){
            System.out.println("bioTextBox is isEnabled");
        }else{
            System.out.println("bioTextBox is not isEnabled");
        }

    }

    @Test
    public void TC_03_isSelected() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement under18RadioButton = driver.findElement(By.id("under_18"));
        under18RadioButton.click();
        if(under18RadioButton.isSelected()){
            System.out.println("under18RadioButton is Selected");
        }else{
            System.out.println("under18RadioButton is not selected");
        }

        WebElement languageJavaCheckBox = driver.findElement(By.id("java"));
        languageJavaCheckBox.click();
        if(languageJavaCheckBox.isSelected()){
            System.out.println("languageJavaCheckBox is Selected");
        }else{
            System.out.println("languageJavaCheckBox is not selected");
        }


        languageJavaCheckBox.click();
        if(under18RadioButton.isSelected()){
            System.out.println("under18RadioButton is Selected");
        }else{
            System.out.println("under18RadioButton is not selected");
        }
        if(languageJavaCheckBox.isSelected()){
            System.out.println("languageJavaCheckBox is Selected");
        }else{
            System.out.println("languageJavaCheckBox is not selected");
        }

    }

    @Test
    public void TC03_RegisterMailChimp(){
        driver.get("https://login.mailchimp.com/signup/");

        WebElement emailField = driver.findElement(By.id("email"));
        emailField.sendKeys("vytran@gmail.com");
        driver.findElement(By.id("new_username")).sendKeys(Keys.TAB);

        driver.findElement(By.id("new_password")).sendKeys("12345");
        driver.findElement(By.id("new_password")).sendKeys(Keys.TAB);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

    }


    @AfterClass
    public void quitBrowser(){
        driver.quit();
    }
}
