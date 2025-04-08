import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ExampleTest {

    @Test
    public void openXebiaWebsite() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        //ChromeOptions =
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.youtube.com/");
        Thread.sleep(3000);
        driver.quit();
    }


}