import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Song;
import utils.SongHelper;

import java.time.Duration;

public class CreateNewSongTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Song song = new Song(
            "Schatteboxe", "Zuerich West", "Mundart", "Love",
            "https://zueriwest.ch/wp-content/uploads/2017/02/Disko_Love.png",
            "TNW1sCFdDhI", "test", "D Sunne schynt duer d Storen uf mys Pult..."
    );

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SongHelper.resetApp(driver);
        SongHelper.visitApp(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            SongHelper.resetApp(driver);
            driver.quit();
        }
    }

    @Test
    public void assertsCreateNewSong() {
        // Arrange & Act
        SongHelper.createSong(driver, wait, song);

        // Assert
        SongHelper.assertSongExists(driver, wait, song);
    }

    @Test
    public void assertSongCanBeEdited() {
        // Arrange
        Song newSong = new Song(song);
        newSong.genre = "Schweizer Rock"; // update expected value

        // Act
        SongHelper.createSong(driver, wait, song);
        SongHelper.editSong(driver, wait, newSong);

        // Assert
        SongHelper.visitApp(driver);
        SongHelper.assertSongExists(driver, wait, newSong);
    }
}
