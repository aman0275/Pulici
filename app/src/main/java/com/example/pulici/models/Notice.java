package com.example.pulici.models;

public class Notice {

    String topic;
    String desc;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Notice() {

    }

    public Notice(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }
}
