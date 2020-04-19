package com.example.budgetexchange.Social;

import java.util.ArrayList;

public class SocialFeed {
    //    name , type, location and rating are required details. The image, price and the search option are just for aesthetics
    private String title;
    private String content;
    private String zID;
    private String postDate;
    private String firstName;
    private String lastName;
    public static ArrayList<SocialFeed> socialFeed = new ArrayList<>();

    //List of posts, with comment and like option - potentially share
    //click on post should take you to post
    //post should have a picture (optional), post, comment, like
    //top of expense feed should have a dropdown list that shows type of content (all, budget, food, city)

    public SocialFeed() {
    }

    public SocialFeed(String title, String content, String zID, String firstName, String lastName, String postDate) {
        this.title = title;
        this.content = content;
        this.zID = zID;
        this.postDate = postDate;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getzID() {
        return zID;
    }

    public void setzID(String zID) {
        this.zID = zID;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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
        socialFeed.add(new SocialFeed("$5 Pasta Recipe", "content","z0000000","Corona","Virus","03/04/2020"));

        return socialFeed;
    }
}
