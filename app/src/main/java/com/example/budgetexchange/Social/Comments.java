package com.example.budgetexchange.Social;

import java.util.ArrayList;

public class Comments {
    private String content, postID, firstName, lastName, zID, postDate;
    public static ArrayList<Comments> commentsList = new ArrayList<>();

    public Comments(){

    }

    public Comments(String content, String postID, String firstName, String lastName, String zID, String postDate) {
        this.content = content;
        this.postID = postID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.zID = zID;
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
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

    public static ArrayList<Comments> getComments() {
        commentsList = new ArrayList<>();
        commentsList.add(new Comments("Where do you buy your ingredients?",SocialFeed.getSocialFeed().get(0).getPostID(),"Corona","Virus","z0000000","03/04/2020"));

        return commentsList;
    }






}
