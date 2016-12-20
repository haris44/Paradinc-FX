import java.util.ArrayList;

/**
 * Created by Nathan on 02/12/2016.
 */
public class Language {
    public String name;
    public int robustness;
    public int facility;
    public int area;
    public int startArea;

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<Attribute> attributes) {
        this.attributes = attributes;
    }
    public String toString(){
        return name.toString() + " " + robustness + " " + facility + " " + area + " " + startArea + " " +this.attributes.toString();
    }
    private ArrayList<Attribute> attributes;

    public Language(String name, int robustness, int facility, int area, int startArea){
        this.name = name;
        this.robustness = robustness;
        this.facility = facility;
        this.area = area;
        this.startArea = startArea;
        this.attributes = new ArrayList<Attribute>();
    }
}
