package com.example.budgetexchange;

import java.util.ArrayList;

public class SocialFeed {
    //    name , type, location and rating are required details. The image, price and the search option are just for aesthetics
    private String title;
    private String content;

    public SocialFeed() {
    }

    public SocialFeed(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public static ArrayList<SocialFeed> getSocialFeed() {
        ArrayList<SocialFeed> restC = new ArrayList<>();
        restC.add(new SocialFeed("Test", "content"));

        return restC;
    }
}
