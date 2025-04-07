package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;

public class SongHelper {
    public static void resetApp(WebDriver driver) {
        driver.get("http://localhost:8081/reset");
    }

    public static void visitApp(WebDriver driver) {
        driver.get("http://localhost:8080/");
    }

    public static void createSong(WebDriver driver, WebDriverWait wait, Song song) {
        driver.findElement(By.cssSelector("a[href='#/songs/create'] div:first-child")).click();

        driver.findElement(By.id("sngTitle")).sendKeys(song.title);
        driver.findElement(By.id("sngArtist")).sendKeys(song.artist);
        driver.findElement(By.id("sngGenre")).sendKeys(song.genre);
        driver.findElement(By.id("sngAlbum")).sendKeys(song.album);
        driver.findElement(By.id("sngAlbumImg")).sendKeys(song.album_url);
        driver.findElement(By.id("sngYoutube")).sendKeys(song.youtube_id);
        driver.findElement(By.id("sngTab")).sendKeys(song.tab);
        driver.findElement(By.id("sngLyrics")).sendKeys(song.lyrics);

        driver.findElement(By.cssSelector(".flex > #sngBtn > .btn__content")).click();
    }

    public static void searchForSong(WebDriver driver, WebDriverWait wait, Song song) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[aria-label*='Search by song']")));
        searchField.sendKeys(song.title);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".song-title"), song.title));
    }

    public static void assertSongExists(WebDriver driver, WebDriverWait wait, Song song) {
        searchForSong(driver, wait, song);
        WebElement titleEl = driver.findElement(By.cssSelector(".song-title"));
        WebElement artistEl = driver.findElement(By.cssSelector(".song-artist"));
        WebElement genreEl = driver.findElement(By.cssSelector(".song-genre"));

        assertEquals(song.title, titleEl.getText());
        assertEquals(song.artist, artistEl.getText());
        assertEquals(song.genre, genreEl.getText());
    }

    public static void editSong(WebDriver driver, WebDriverWait wait, Song newSong) {
        searchForSong(driver, wait, newSong);

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
