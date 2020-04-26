package com.example.budgetexchange.Social;

import java.util.ArrayList;
import java.util.UUID;

public class SocialFeed {
    //    name , type, location and rating are required details. The image, price and the search option are just for aesthetics
    private String title;
    private String type;
    private String content;
    private String zID;
    private String postID;
    private String postDate;
    private String firstName;
    private String lastName;
    public static ArrayList<SocialFeed> socialFeed;

    //List of posts, with comment and like option - potentially share
    //click on post should take you to post
    //post should have a picture (optional), post, comment, like
    //top of expense feed should have a dropdown list that shows type of content (all, budget, food, city)

    public SocialFeed() {
    }

    public SocialFeed(String title, String content, String zID, String firstName, String lastName, String postDate, String type) {
        this.title = title;
        this.content = content;
        this.zID = zID;
        this.postDate = postDate;
        this.firstName = firstName;
        this.lastName = lastName;
        postID= UUIDcreate();
        this.type=type;

    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        socialFeed.add(new SocialFeed("$5 Pasta Recipe", "This is how you make the pasta. First you buy the ingredients...","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));
        socialFeed.add(new SocialFeed("Discounted products from Coles and Woolies this week", "Half price chicken at Coles! 30% discount for Milk from Woolies!","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));
        socialFeed.add(new SocialFeed("Inexpensive Meals based on pantry goods", "So, to start, this will inspire you with some ideas on how to make cheap meals based on pantry goods for breakfast, lunch and dinner. How good aye.","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));
        socialFeed.add(new SocialFeed("Here are the best food to stockpile during COVID-19", "Start with adding frozen fruits and vegetables to your list. Potatoes are also a good, low-cost food to stockpile...","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));
        socialFeed.add(new SocialFeed("ALDI vs Coles this week", "Both giants have lowered their prices for the Sweets section this week. Let's take a look at it.","z0000000","Corona","Virus","03/04/2020", "Cheap Eats"));

        socialFeed.add(new SocialFeed("Budgeting Tips and Tricks During COVID-19", "How to save money when there's coronavirus?", "z0000000", "Corona", "Virus", "10/04/2020", "Budgeting Tips"));
        socialFeed.add(new SocialFeed("Quarantine Budgeting Tips", "Budgeting Mistake #1..., Budgeting Mistake #2... ", "z0000000", "Corona", "Virus", "12/04/2020", "Budgeting Tips"));
        socialFeed.add(new SocialFeed("5 Budgeting Tips from a Financial Planner", "So, some financial exports offered some quick money and budgeting tips that might help you out now", "z0000000", "Corona", "Virus", "15/04/2020", "Budgeting Tips"));
        socialFeed.add(new SocialFeed("Wanna save while spending? Here's how!", "Now saving while spending isn't really that hard tbh. All you have to do is...", "z0000000", "Corona", "Virus", "16/04/2020", "Budgeting Tips"));
        socialFeed.add(new SocialFeed("How to cover your expenses when you lost your income due to COVID-19?", "Firstly, it feels bad to have lost your job due to COVID-19. However, to address your issue, this is what you should do...", "z0000000", "Corona", "Virus", "20/04/2020", "Budgeting Tips"));

        return socialFeed;
    }

    public static SocialFeed searchPosts(int position){
        return socialFeed.get(position);
    }

    public static String UUIDcreate(){
        UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

           return randomUUIDString;
        }
    }

