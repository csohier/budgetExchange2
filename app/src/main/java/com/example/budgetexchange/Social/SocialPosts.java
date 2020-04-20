package com.example.budgetexchange.Social;

import java.util.ArrayList;
import java.util.List;

public class SocialPosts {

    public String firstName;
    public String lastName;
    public String postText;
    public String postDate;
    public static List<SocialPosts> feed = new ArrayList<>();
    //public String postType;
    //public String topic;

    public SocialPosts(){

    }

    public SocialPosts(String firstName, String lastName, String postText, String postDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postText = postText;
        this.postDate = postDate;
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

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
}
