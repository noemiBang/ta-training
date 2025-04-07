package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.api.ApiClient;
import utils.models.Song;
import utils.ui.SongPage;

import java.time.Duration;

public class SongTests {

    private WebDriver driver;
    private WebDriverWait wait;
    private ApiClient api = new ApiClient();

    private Song song = new Song(
            "Schatteboxe", "Zuerich West", "Mundart", "Love",
            "https://zueriwest.ch/wp-content/uploads/2017/02/Disko_Love.png",
            "TNW1sCFdDhI", "test", "D Sunne schynt duer d Storen uf mys Pult..."
    );

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        api.resetApp();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            api.resetApp();
            driver.quit();
        }
    }

    @Test
    public void assertsCreateNewSong() {
        // Arrange
        SongPage page = new SongPage(driver, wait);
        page.openApplication();

        // Act
        page.createSong(song);

        // Assert
        page.assertSongExists(song);
    }

    @Test
    public void assertSongCanBeEdited() {
        // Arrange
        SongPage page = new SongPage(driver, wait);
        Song newSong = new Song(song);
        newSong.genre = "Schweizer Rock"; // update expected value
        page.openApplication();

        // Act
        page.createSong(song);
        page.editSong(newSong);

        // Assert
        page.openApplication();
        page.assertSongExists(newSong);
    }
}
