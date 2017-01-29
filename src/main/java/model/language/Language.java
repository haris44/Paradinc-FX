package model.language;

import java.util.ArrayList;

/**
 * Created by Nathan on 02/12/2016.
 */
public class Language {
    public String name;
    public int robustness;
    public int facility;
    private ArrayList<Attribute> attributes;


    public Language(String name, int robustness, int facility){
        this.name = name;
        this.robustness = robustness;
        this.facility = facility;
        this.attributes = new ArrayList<Attribute>();
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String toString(){
        return name.toString() + " " + robustness + " " + facility + " " +this.attributes.toString();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRobustness() {
        return robustness;
    }

    public void setRobustness(int robustness) {
        this.robustness = robustness;
    }

    public int getFacility() {
        return facility;
    }

    public void setFacility(int facility) {
        this.facility = facility;
    }

}
