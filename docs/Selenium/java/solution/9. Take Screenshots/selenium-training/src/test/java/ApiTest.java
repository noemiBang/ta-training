import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import utils.Song;
import utils.SongHelper;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {

    @Test
    public void shouldReturnSongList() {
        // Arrange & Act
        Response response = RestAssured.get("http://localhost:8081/songs");

        // Assert
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void shouldContainExpectedSong() throws Exception {
        // Arrange
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("title", "She's Kerosene");
        map.put("artist", "The Interrupters");
        map.put("genre", "Ska Punk");
        map.put("album", "Fight the Good Fight");
        map.put("albumImageUrl", "https://i.scdn.co/image/ab67616d0000b273c9c292d93a9d27c4762cb559");
        map.put("youtubeId", "Yq2jJLswL8I");
        map.put("lyrics", "");
        map.put("tab", "");
        map.put("createdAt", "2018-02-13T12:56:24.432Z");
        map.put("updatedAt", "2025-04-07T18:16:02.146Z");

        // Act
        Response response = RestAssured.get("http://localhost:8081/songs");
        JSONObject expected = new JSONObject(map);
        JSONArray actual = new JSONArray(response.asString());

        // Assert
        JSONAssert.assertEquals(expected, actual.getJSONObject(0), JSONCompareMode.STRICT_ORDER);
    }

    @Test
    public void shouldReturnSongListPOJO() {
        // Arrange & Act
        Response response = RestAssured.get("http://localhost:8081/songs");
        Song[] songs = response.as(Song[].class);

        // Assert
        assertEquals(200, response.getStatusCode());
        assertEquals("She's Kerosene", songs[0].title);
    }
}