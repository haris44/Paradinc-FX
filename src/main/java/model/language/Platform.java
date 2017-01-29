package model.language;

/**
 * Created by Nathan on 08/12/2016.
 */
public class Platform implements Attribute {
    private PlatformType name;
    private Integer weight;
    private Integer popularity;

    public Platform(PlatformType name, Integer weight, Integer popularity) {
        this.name = name;
        this.weight = weight;
        this.popularity = popularity;
    }

    public static Platform fromPlatformType(PlatformType type){
        switch (type){
            case Linux:
                return new Platform(PlatformType.Linux,60,40);
            case Unix:
                return new Platform(PlatformType.Unix,40,60);
            default:
                return new Platform(PlatformType.Windows,80,20);
        }
    }

    public Integer getWeight() {
        return weight;
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

    @Override
    public PlatformType getPlaformType() {
        return name;
    }
}
