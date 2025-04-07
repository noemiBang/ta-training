package utils.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.models.Song;

import static junit.framework.Assert.assertEquals;

public class SongPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SongPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void openApplication() {
        driver.get("http://localhost:8080/");
    }

    public void createSong(Song song) {
        driver.findElement(By.cssSelector("a[href='#/songs/create'] div:first-child")).click();

        driver.findElement(By.id("sngTitle")).sendKeys(song.title);
        driver.findElement(By.id("sngArtist")).sendKeys(song.artist);
        driver.findElement(By.id("sngGenre")).sendKeys(song.genre);
        driver.findElement(By.id("sngAlbum")).sendKeys(song.album);
        driver.findElement(By.id("sngAlbumImg")).sendKeys(song.albumImageUrl);
        driver.findElement(By.id("sngYoutube")).sendKeys(song.youtubeId);
        driver.findElement(By.id("sngTab")).sendKeys(song.tab);
        driver.findElement(By.id("sngLyrics")).sendKeys(song.lyrics);

        driver.findElement(By.cssSelector(".flex > #sngBtn > .btn__content")).click();
    }

    public void searchForSong(Song song) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[aria-label*='Search by song']")));
        searchField.sendKeys(song.title);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-title"), song.title));
    }

    public void assertSongExists(Song song) {
        searchForSong(song);
        WebElement titleEl = driver.findElement(By.cssSelector(".song-title"));
        WebElement artistEl = driver.findElement(By.cssSelector(".song-artist"));
        WebElement genreEl = driver.findElement(By.cssSelector(".song-genre"));

        assertEquals(song.title, titleEl.getText());
        assertEquals(song.artist, artistEl.getText());
        assertEquals(song.genre, genreEl.getText());
    }

    public void editSong(Song newSong) {
        searchForSong(newSong);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'song')]//button[div[contains(text(),'View')]]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[div[contains(text(),'Edit')]]"))).click();

        WebElement genreField = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[aria-label='Genre']")));
        genreField.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
        genreField.sendKeys(newSong.genre);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[div[normalize-space(text())='Save Song']]"))).click();
    }
}
