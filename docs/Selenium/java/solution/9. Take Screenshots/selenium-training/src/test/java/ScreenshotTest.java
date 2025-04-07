import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class ScreenshotTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void openXebiaWebsite() {
        driver.get("https://xebia.com/switzerland-de");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);

            long timestamp = System.currentTimeMillis();
            File targetFile = new File("screenshots/" + timestamp + ".png");

            targetFile.getParentFile().mkdirs();
            screenshot.renameTo(targetFile);

            System.out.println("📸 Screenshot saved: " + targetFile.getAbsolutePath());
            driver.quit();
        }
    }
}