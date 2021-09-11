package com.genesiscreations.kdec.model;


import javax.persistence.*;

@Entity
@Table(name = "song_info")
public class SongInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String albumName;
    String lang;
    String songName;
    String author;
    long views;
    public SongInfo() {
    }

    public SongInfo(String albumName, String lang, String songName, String author) {
        this.albumName = albumName;
        this.lang = lang;
        this.songName = songName;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }
}
