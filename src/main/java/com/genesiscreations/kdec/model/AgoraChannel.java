package com.genesiscreations.kdec.model;

import java.util.List;

public class AgoraChannel {
    private boolean success;
    private Data data;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public Data getData() { return data; }
    public void setData(Data value) { this.data = value; }
    public class Data {
         List<Channel> channels;
         int total_size;

        public List<Channel> getChannels() { return channels; }
        public void setChannels(List<Channel> value) { this.channels = value; }

        public long getTotalSize() { return total_size; }
        public void setTotalSize(int value) { this.total_size = value; }
    }
    public class Channel {
         String channel_name;
        int user_count;
        public String getChannelName() { return channel_name; }
        public void setChannelName(String value) { this.channel_name = value; }

        public long getUserCount() { return user_count; }
        public void setUserCount(int value) { this.user_count = value; }
    }

}




