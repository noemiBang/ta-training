package utils.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import utils.models.Song;

import java.util.Map;

public class ApiClient {
    private static final String BASE_URL = "http://localhost:8081";

    public void resetApp() {
        RestAssured.get(BASE_URL + "/reset");
    }

    public Response getAllSongsRaw() {
        return RestAssured.get(BASE_URL + "/songs");
    }

    public Song[] getAllSongsAsPojo() {
        return getAllSongsRaw().as(Song[].class);
    }

    public Song getSongByIndex(int index) {
        Song[] allSongs = getAllSongsAsPojo();
        return allSongs[index];
    }

    public JSONObject buildExpectedJson(Map<String, Object> songMap) {
        return new JSONObject(songMap);
    }

    public void assertSongMatches(JSONObject expected, Response response, int index) throws Exception {
        JSONArray actual = new JSONArray(response.asString());
        JSONAssert.assertEquals(expected, actual.getJSONObject(index), JSONCompareMode.STRICT_ORDER);
    }
}
