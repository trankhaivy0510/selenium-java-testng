package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_07_If_Else {
    WebDriver driver;
    public static void main(String[] args) {
        String osName = System.getProperty("os.name");
        String browserName = "IE";
        WebDriver driver;
        // Biểu thức điều kiện
        //If
        if(browserName.equals("IE")){
            System.out.println("Click to Submit button");
        }

        //If-else
        if(osName.contains("Window")){
            System.out.println("Hệ điều hành là windows");
        }else{
            System.out.println("Hệ điều hành là Linux");
        }

        //If-else-if-else
        if (browserName.equals("Chrome")){
            driver= new ChromeDriver();
        }else if (browserName.equals("Firefox")){
            driver = new FirefoxDriver();
        }else {
            driver = new EdgeDriver();
        }

        //Switch-case
        switch (browserName){
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Chrome":
                driver= new ChromeDriver();
                break;
            default:
                driver = new EdgeDriver();
        }
    }
}
