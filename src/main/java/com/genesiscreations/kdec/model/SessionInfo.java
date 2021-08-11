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
    String token;
    public SessionInfo() {
    }

    public SessionInfo(String channelName, String token) {
        this.channelName = channelName;
        this.token = token;
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


}
