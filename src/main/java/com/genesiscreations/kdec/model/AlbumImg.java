package com.genesiscreations.kdec.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "album_img")
public class AlbumImg {
    @Id
    String albumName;
    String categoryName;
    String imgPath;

    public AlbumImg() {
    }

    public AlbumImg(String albumName,String categoryName, String imgPath) {
        this.albumName = albumName;
        this.categoryName = categoryName;
        this.imgPath = imgPath;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
