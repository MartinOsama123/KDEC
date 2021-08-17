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

    public SongInfo() {
    }

    public SongInfo(String albumName, String lang, String songName) {
        this.albumName = albumName;
        this.lang = lang;
        this.songName = songName;
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
}
