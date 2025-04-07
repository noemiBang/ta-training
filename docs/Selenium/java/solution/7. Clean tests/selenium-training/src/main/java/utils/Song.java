package utils;

public class Song {
    public String title;
    public String artist;
    public String genre;
    public String album;
    public String album_url;
    public String youtube_id;
    public String tab;
    public String lyrics;

    public Song(String title, String artist, String genre, String album,
                String album_url, String youtube_id, String tab, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.album_url = album_url;
        this.youtube_id = youtube_id;
        this.tab = tab;
        this.lyrics = lyrics;
    }

    public Song(Song other) {
        this(
                other.title,
                other.artist,
                other.genre,
                other.album,
                other.album_url,
                other.youtube_id,
                other.tab,
                other.lyrics
        );
    }

}
