package usc.teamfive.helpinghand.datastructures;

import java.text.SimpleDateFormat;
import java.util.Date;

// object representing a data structure for a job posting
// the goal is to represent all of the data stored for each job posting
public class Job {

    // private variables
    private int id;
    private String description;
    private int cost;
    private String user;
    private boolean tagAssembly;
    private boolean tagCleaning;
    private boolean tagStorage;
    private boolean tagRepair;
    private boolean tagPetCare;
    private boolean tagChildCare;
    private boolean tagMealPrep;
    private boolean tagITHelp;
    private boolean tagBooks;
    private boolean tagSublets;
    private boolean tagOther;

    // constructors
    public Job(int id, String description, int cost, String user, boolean tagAssembly, boolean tagCleaning,
               boolean tagStorage, boolean tagRepair, boolean tagPetCare, boolean tagChildCare,
               boolean tagMealPrep, boolean tagITHelp, boolean tagBooks, boolean tagSublets,
               boolean tagOther) {
        // assign the variables
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.user = user;
        this.tagAssembly = tagAssembly;
        this.tagCleaning = tagCleaning;
        this.tagStorage = tagStorage;
        this.tagRepair = tagRepair;
        this.tagPetCare = tagPetCare;
        this.tagChildCare = tagChildCare;
        this.tagMealPrep = tagMealPrep;
        this.tagITHelp = tagITHelp;
        this.tagBooks = tagBooks;
        this.tagSublets = tagSublets;
        this.tagOther = tagOther;
    }

    public Job() {
        // default job and variables; in case a shell object is needed for some reason
        this.id = 0;
        this.description = "Description not found.";
        this.cost = 0;
        this.user = "User not found.";
        this.tagAssembly = false;
        this.tagCleaning = false;
        this.tagStorage = false;
        this.tagRepair = false;
        this.tagPetCare = false;
        this.tagChildCare = false;
        this.tagMealPrep = false;
        this.tagITHelp = false;
        this.tagBooks = false;
        this.tagSublets = false;
        this.tagOther = false;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public String getUser() {
        return user;
    }

    public boolean getTagAssembly() {
        return tagAssembly;
    }

    public boolean getTagCleaning() {
        return tagCleaning;
    }

    public boolean getTagStorage() {
        return tagStorage;
    }

    public boolean getTagRepair() {
        return tagRepair;
    }

    public boolean getTagPetCare() {
        return tagPetCare;
    }

    public boolean getTagChildCare() {
        return tagChildCare;
    }

    public boolean getTagMealPrep() {
        return tagMealPrep;
    }

    public boolean getTagITHelp() {
        return tagITHelp;
    }

    public boolean getTagBooks() {
        return tagBooks;
    }

    public boolean getTagSublets() {
        return tagSublets;
    }

    public boolean getTagOther() {
        return tagOther;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTagAssembly(boolean tagAssembly) {
        this.tagAssembly = tagAssembly;
    }

    public void setTagCleaning(boolean tagCleaning) {
        this.tagCleaning = tagCleaning;
    }

    public void setTagStorage(boolean tagStorage) {
        this.tagStorage = tagStorage;
    }

    public void setTagRepair(boolean tagRepair) {
        this.tagRepair = tagRepair;
    }

    public void setTagPetCare(boolean tagPetCare) {
        this.tagPetCare = tagPetCare;
    }

    public void setTagChildCare(boolean tagChildCare) {
        this.tagChildCare = tagChildCare;
    }

    public void setTagMealPrep(boolean tagMealPrep) {
        this.tagMealPrep = tagMealPrep;
    }

    public void setTagITHelp(boolean tagITHelp) {
        this.tagITHelp = tagITHelp;
    }

    public void setTagBooks(boolean tagBooks) {
        this.tagBooks = tagBooks;
    }

    public void setTagSublets(boolean tagSublets) {
        this.tagSublets = tagSublets;
    }

    public void setTagOther(boolean tagOther) {
        this.tagOther = tagOther;
    }

    // helper functions
    public String prettyPrintTags() {
        // return a string of all the tags
        String out = "";
        if (tagAssembly) {
            out += "Assembly, ";
        }
        if (tagCleaning) {
            out += "Cleaning, ";
        }
        if (tagStorage) {
            out += "Storage, ";
        }
        if (tagRepair) {
            out += "Repair, ";
        }
        if (tagPetCare) {
            out += "Pet Care, ";
        }
        if (tagChildCare) {
            out += "Child Care, ";
        }
        if (tagMealPrep) {
            out += "Meal Prep, ";
        }
        if (tagITHelp) {
            out += "IT Help, ";
        }
        if (tagBooks) {
            out += "Books, ";
        }
        if (tagSublets) {
            out += "Sublets, ";
        }
        if (tagOther) {
            out += "Other, ";
        }
        // clean up any extra ", "
        if (out.length() > 2) {
            out = out.substring(0, out.length() - 2);
        }
        return out;
    }

}
