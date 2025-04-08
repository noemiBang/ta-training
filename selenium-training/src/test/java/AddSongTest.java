// Generated by Selenium IDE


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.Assert.assertEquals;


public class AddSongTest {
    private WebDriver driver;
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("http://localhost:8081/reset");
    }
    @AfterEach
    public void tearDown() {
        driver.get("http://localhost:8081/reset");
        driver.quit();
    }
    @Test
    public void addSongAndEdit() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.cssSelector("#add-song > .btn__content")).click();
        driver.findElement(By.id("sngTitle")).click();
        driver.findElement(By.id("sngTitle")).sendKeys("Bad Romance");
        driver.findElement(By.id("sngArtist")).click();
        driver.findElement(By.id("sngArtist")).sendKeys("Lady Gaga");
        driver.findElement(By.id("sngGenre")).click();
        driver.findElement(By.id("sngGenre")).sendKeys("Pop");
        driver.findElement(By.id("sngAlbumImg")).click();
        driver.findElement(By.id("sngAlbumImg")).sendKeys("https://i.scdn.co/image/ab67616d0000b2735c9890c0456a3719eeecd8aa");
        driver.findElement(By.id("sngAlbum")).click();
        driver.findElement(By.id("sngAlbum")).sendKeys("bad romance single");
        driver.findElement(By.id("sngYoutube")).click();
        driver.findElement(By.id("sngYoutube")).sendKeys("qrO4YZeyl0I");
        driver.findElement(By.id("sngTab")).click();
        driver.findElement(By.id("sngTab")).sendKeys("-");
        driver.findElement(By.id("sngLyrics")).click();
        driver.findElement(By.id("sngLyrics")).sendKeys("blabla");
        driver.findElement(By.cssSelector("#sngBtn > .btn__content")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        WebElement searchfield = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[data-test-id='search-bar']")));
        searchfield.click();
        searchfield.sendKeys("Bad Romance");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("song-title"), "Bad Romance"));
        String ActualTitle = driver.findElement(By.className("song-title")).getText();

        assertEquals("Bad Romance", ActualTitle);

       driver.findElement(By.xpath("//a[contains(@href, '/songs/') and contains(., 'View')]")).click();
        driver.findElement(By.xpath("//a[contains(@href, '/songs/') and contains(., 'Edit')]")).click();
        WebElement titleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[aria-label='Title']")));
        titleInput.sendKeys(Keys.chord(Keys.CONTROL,"a") + Keys.BACK_SPACE);
        titleInput.sendKeys("Bad Swap");

// 2. Auf "Save Song" Button klicken
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Save Song')]")));
        saveButton.click();


        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("song-title"), "Bad Swap"));
    String NewActualtTitle = driver.findElement(By.className("song-title")).getText();
    assertEquals("Bad Swap", NewActualtTitle);
    }
}
