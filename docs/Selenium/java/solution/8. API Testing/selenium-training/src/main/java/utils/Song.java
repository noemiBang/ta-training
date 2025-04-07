package utils;

public class Song {
    public int id;
    public String createdAt;
    public String updatedAt;

    public String title;
    public String artist;
    public String genre;
    public String album;
    public String albumImageUrl;
    public String youtubeId;
    public String tab;
    public String lyrics;

    public Song() {
        // Required by Jackson
    }

    public Song(String title, String artist, String genre, String album,
                String albumImageUrl, String youtubeId, String tab, String lyrics) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.album = album;
        this.albumImageUrl = albumImageUrl;
        this.youtubeId = youtubeId;
        this.tab = tab;
        this.lyrics = lyrics;
    }

    public Song(Song other) {
        this(
                other.title,
                other.artist,
                other.genre,
                other.album,
                other.albumImageUrl,
                other.youtubeId,
                other.tab,
                other.lyrics
        );
    }

}
