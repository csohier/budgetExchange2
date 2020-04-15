package com.example.budgetexchange;

import java.util.ArrayList;

public class SocialFeed {
    //    name , type, location and rating are required details. The image, price and the search option are just for aesthetics
    private String title;
    private String content;
    public static ArrayList<SocialFeed> socialFeed = new ArrayList<>();

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
        socialFeed = new ArrayList<>();
        socialFeed.add(new SocialFeed("Test", "content"));

        return socialFeed;
    }
}
