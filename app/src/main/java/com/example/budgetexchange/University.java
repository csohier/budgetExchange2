package com.example.budgetexchange;

import java.util.ArrayList;

public class University {

    private String name;
    private String discipline;
    private String country;
    private String region;
    private String currency;

    public University () {
        //Empty Constructor
    }

    public University(String name, String discipline, String country, String region, String currency) {
        this.name = name;
        this.discipline = discipline;
        this.country = country;
        this.region=region;
        this.currency = currency;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDiscipline() { return discipline; }

    public void setDiscipline(String discipline) { this.discipline = discipline; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }

    public void setRegion(String region) { this.region = region; }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) {this.currency = currency; }

    //ArrayList for Universities
    public static ArrayList<University> getUniversities (){
        ArrayList<University> universities = new ArrayList<>();
        universities.add(new University("McGill University","Faculty of Law","Canada","North America","CAD"));
        universities.add(new University("University of British Columbia","Saunder School of Business","Canada", "North America","CAD"));
        universities.add(new University("Seoul National University","Built Environment/Engineering","Korea","Asia","KRW"));
        universities.add(new University("Kyoto University","All available disciplines","Japan","Asia","JPY"));
        universities.add(new University("University of Amsterdam","All available disciplines","Netherlands","Europe","EUR"));
        universities.add(new University("University College London","Law & Built Environment","England","United Kingdom","GBP"));
        universities.add(new University("University of California, Berkeley","All available disciplines","United States", "North America","USD"));
        universities.add(new University("Boston College", "All available disciplines", "United States", "North America","USD"));
        universities.add(new University("Chinese University of Hong Kong", "All available disciplines", "Hong Kong", "Asia","HKD"));
        universities.add(new University("Copenhagen Business School", "Business School", "Denmark", "Europe","EUR"));
        universities.add(new University("Hokkaido University", "Arts and Social Science/Science", "Japan", "Asia","JPY"));

        return universities;

    }

    //Search Function of Universities
    public static University searchUniversity (String search) {
        ArrayList<University> universities = getUniversities();
        for (int i = 0; i < universities.size(); i++) { if (search.equals(universities.get(i).name)) { return universities.get(i);} }
        return null;
    }

}
