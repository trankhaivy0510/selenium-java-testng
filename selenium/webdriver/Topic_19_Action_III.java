package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class Topic_19_Action_III {
    WebDriver driver ;
    Actions action ;
    String projectPath = System.getProperty("user.dir");
    JavascriptExecutor js;

    @BeforeClass
    public void initBrowser(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        js =  (JavascriptExecutor) driver; // ép kiểu qua driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//        driver.manage().window().maximize();
    }

    @Test
    public void TC01_Drag_And_Drop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        action.dragAndDrop(smallCircle, bigCircle).perform();

        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");

    }

    @Test
    public void TC0_Drag_And_Drop_HTML5(){
        // Không support HTML5 nên không chạy được
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement boxA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement boxB = driver.findElement(By.cssSelector("div#column-b"));
        action.dragAndDrop(boxA, boxB).perform();

    }


    @Test
    public void TC02_Drag_And_Drop_HTML5_JQuery() throws IOException {
        // dùng code jquery , chỉ áp dụng CSS không áp dụng Xpath
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jqueryDragAndDropContent = getContentFile(projectPath+"/dragDrop/dragAndDrop.js");
        js.executeScript(jqueryDragAndDropContent);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.cssSelector(sourceLocator));
        WebElement target = driver.findElement(By.cssSelector(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    @Test
    public void TC_06_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String sourceXpath = "div#column-a";
        String targetXpath = "div#column-b";

        dragAndDropHTML5ByXpath(sourceXpath, targetXpath);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");
    }
}
