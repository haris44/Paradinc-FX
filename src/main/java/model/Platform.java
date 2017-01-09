package model;

/**
 * Created by Nathan on 08/12/2016.
 */
public class Platform implements Attribute {
    private String name;
    private Integer weight;
    private Integer popularity;

    public Platform(String name, Integer weight, Integer popularity) {
        this.name = name;
        this.weight = weight;
        this.popularity = popularity;
    }

    public static Platform fromPlatformType(PlatformType type){
        switch (type){
            case Linux:
                return new Platform("Linux",60,40);
            case Unix:
                return new Platform("Unix",40,60);
            default:
                return new Platform("Windows",80,20);
        }
    }

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getPopularity() {
        return popularity;
    }

}
