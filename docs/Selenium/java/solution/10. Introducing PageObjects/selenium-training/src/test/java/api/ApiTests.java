package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.api.ApiClient;
import utils.models.Song;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTests {

    private ApiClient api;

    @BeforeEach
    public void setup() {
        api = new ApiClient();
    }

    @Test
    public void shouldReturnSongList() {
        // Arrange & Act
        Response response = api.getAllSongsRaw();

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
        JSONObject expected = api.buildExpectedJson(map);

        // Act & Assert
        api.assertSongMatches(expected, api.getAllSongsRaw(), 0);
    }

    @Test
    public void shouldReturnSongListPOJO() {
        // Arrange & Act
        Song[] songs = api.getAllSongsAsPojo();

        // Assert
        assertNotNull(songs);
        assertTrue(songs.length > 0);
        assertEquals("She's Kerosene", songs[0].title);
    }
}