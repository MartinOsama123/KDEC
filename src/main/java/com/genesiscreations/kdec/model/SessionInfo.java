package com.genesiscreations.kdec.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SESSION_INFO")
public class SessionInfo {
    @Id
    String channelName;
    String description;
    String hostName;
    String lang;
    String token;
    String imgPath;
    public SessionInfo() {
    }

    public SessionInfo(String channelName, String description, String hostName, String lang, String token,String imgPath) {
        this.channelName = channelName;
        this.description = description;
        this.hostName = hostName;
        this.lang = lang;
        this.token = token;
        this.imgPath = imgPath;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
