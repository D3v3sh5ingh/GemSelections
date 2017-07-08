package com.example.nikhil3000.gemselections.Lists;

/**
 * Created by anonymous on 8/7/17.
 */

public class ConnectOptions {
    private String url, text, link;

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    public ConnectOptions(String url, String text, String link) {
        this.url = url;
        this.text = text;
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
