package model.language;

/**
 * Created by Nathan on 02/12/2016.
 */
public class Paradygm implements Attribute {
    public String name;
    public int popularity;
    public int weight;

    public Paradygm(String name, int popularity, int weight){
        this.name= name;
        this.popularity = popularity;
        this.weight = weight;
    }
    public static Paradygm fromParadygmeType(ParadygmType type){
        if(ParadygmType.Fonctionel == type)
            return new Paradygm("Fonctionel",70,30);
        else if (ParadygmType.Object == type)
            return new Paradygm("Objet",20,80);
        else
            return new Paradygm("Impératif",40,60);
    }
}
