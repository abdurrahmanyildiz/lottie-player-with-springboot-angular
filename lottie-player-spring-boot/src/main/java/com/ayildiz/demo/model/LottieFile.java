package com.ayildiz.demo.model;


import java.util.UUID;

public class LottieFile {
    private final UUID ID;
    private final String name;
    private final String url;

    public LottieFile(UUID id, String name, String url) {
        ID = id;
        this.name = name;
        this.url = url;
    }

    public UUID getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
