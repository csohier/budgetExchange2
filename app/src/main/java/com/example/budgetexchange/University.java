package com.example.budgetexchange;

import java.util.ArrayList;

public class University {

    private String name;
    private String discipline;
    private String country;
    private String region;

    public University(String name, String discipline, String country, String region) {
        this.name = name;
        this.discipline = discipline;
        this.country = country;
        this.region=region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public static ArrayList<University> getUniversities (){
        ArrayList<University> universities = new ArrayList<>();
        universities.add(new University("McGill University","Faculty of Law","Canada","North America"));
        universities.add(new University("University of British Columbia","Saunder School of Business","Canada", "North America"));
        universities.add(new University("Seoul National University","All available disciplines","Korea","Asia"));
        universities.add(new University("Kyoto University","All available disciplines","Japan","Asia"));
        universities.add(new University("University of Amsterdam","All available disciplines","Netherlands","Europe"));
        universities.add(new University("University College London","Law & Built Environment","England","United Kingdom"));
        universities.add(new University("University of California, Berkeley","All available disciplines","United States", "North America"));
        return universities;

    }

    public static University searchUniversity(int position) {
        for(int i = 0; i > getUniversities().size(); i++)
            if(i==position){
                return getUniversities().get(i);
            }
        return null;
    }

}
