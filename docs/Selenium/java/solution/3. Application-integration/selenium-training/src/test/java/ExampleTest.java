import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExampleTest {

    @Test
    public void openXebiaWebsite() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://xebia.com/switzerland-de");
        driver.quit();
    }

    @Test
    public void slowTestExample() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://xebia.com/switzerland-de");

        // Wait 3 seconds before quitting the browser
        Thread.sleep(3000);

        driver.quit();
    }
}