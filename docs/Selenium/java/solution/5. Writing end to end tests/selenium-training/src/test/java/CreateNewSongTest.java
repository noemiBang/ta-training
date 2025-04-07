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

import static org.junit.jupiter.api.Assertions.*;

public class CreateNewSongTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://localhost:8080");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void assertsCreateNewSong() throws InterruptedException {
        // Navigate to "Create Song"
        driver.findElement(By.cssSelector("a[href='#/songs/create'] div:first-child")).click();

        // Fill out form
        driver.findElement(By.id("sngTitle")).clear();
        driver.findElement(By.id("sngTitle")).sendKeys("Schatteboxe");

        driver.findElement(By.id("sngArtist")).clear();
        driver.findElement(By.id("sngArtist")).sendKeys("Zuerich West");

        driver.findElement(By.id("sngGenre")).clear();
        driver.findElement(By.id("sngGenre")).sendKeys("Mundart");

        driver.findElement(By.id("sngAlbum")).clear();
        driver.findElement(By.id("sngAlbum")).sendKeys("Love");

        driver.findElement(By.id("sngAlbumImg")).clear();
        driver.findElement(By.id("sngAlbumImg")).sendKeys("https://zueriwest.ch/wp-content/uploads/2017/02/Disko_Love.png");

        driver.findElement(By.id("sngYoutube")).clear();
        driver.findElement(By.id("sngYoutube")).sendKeys("TNW1sCFdDhI");

        driver.findElement(By.id("sngTab")).clear();
        driver.findElement(By.id("sngTab")).sendKeys("test");

        driver.findElement(By.id("sngLyrics")).clear();
        driver.findElement(By.id("sngLyrics")).sendKeys("D Sunne schynt duer d Storen uf mys Pult...");

        // Submit form
        driver.findElement(By.cssSelector(".flex > #sngBtn > .btn__content")).click();

        // Search for song
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[aria-label*='Search by song']")));
        searchField.sendKeys("Schatteboxe");

        // Verify song data
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-title"), "Schatteboxe"));
        WebElement titleEl = driver.findElement(By.cssSelector(".song-title"));
        WebElement artistEl = driver.findElement(By.cssSelector(".song-artist"));
        WebElement genreEl = driver.findElement(By.cssSelector(".song-genre"));

        // Assertions
        assertEquals("Schatteboxe", titleEl.getText());
        assertEquals("Zuerich West", artistEl.getText());
        assertEquals("Mundart", genreEl.getText());
    }

    @Test
    public void assertSongCanBeEdited() {
        // Search the song
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[aria-label*='Search by song']")));
        searchField.sendKeys("Schatteboxe");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-title"), "Schatteboxe"));

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'song')]//button[div[contains(text(),'View')]]"))).click();

        // Edit the song
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[div[contains(text(),'Edit')]]"))).click();

        WebElement genreField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Genre']")));
        genreField.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
        genreField.clear();
        genreField.sendKeys("Schweizer Rock");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[div[normalize-space(text())='Save Song']]")));
        saveButton.click();

        // Search the song again
        driver.get("http://localhost:8080");
        WebElement updatedSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[aria-label*='Search by song']")));
        updatedSearch.clear();
        updatedSearch.sendKeys("Schatteboxe");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-title"), "Schatteboxe"));

        // Assert the updated song
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-genre"), "Schweizer Rock"));
        WebElement genreEl = driver.findElement(By.cssSelector(".song-genre"));
        assertEquals("Schweizer Rock", genreEl.getText());
    }

    @Test
    public void assertFocusBehaviorOnSearchField() {
        // Locate the search field
        WebElement searchField = driver.findElement(By.cssSelector("input[aria-label*='Search by song']"));

        // Assert it is NOT focused initially
        assertNotEquals(searchField, driver.switchTo().activeElement());

        // Click on the field to focus
        searchField.click();

        // Assert it is now the focused element
        assertEquals(searchField, driver.switchTo().activeElement());
    }
}
