package com.catgame;

public class FortuneData {

    private Data data;
    private Meta meta;

    public FortuneData() {

    }


    private class Data {
        String message;
    }

    private class Meta {
        int status;
    }

    public String getMessage() {
        return data.message;
    }

    public int getStatus() {
        return meta.status;
    }

}
