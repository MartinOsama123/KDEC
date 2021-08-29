package com.genesiscreations.kdec.model;

import java.util.List;

public class SongSearch {
    List<String> albums;
    List<String> songs;
    List<String> authors;

    public SongSearch(List<String> albums, List<String> songs, List<String> authors) {
        this.albums = albums;
        this.songs = songs;
        this.authors = authors;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
