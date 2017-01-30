package map;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegion {

	// CRegion region ???
	private int contamination;
	private String name;
	CountryRegion region;

	public ParadincRegion(String name, CountryRegion region){
		this.name = name;
		contamination = 0;
		this.region = region;
	}

	public int getContamination() {
		return contamination;
	}

	public void setContamination(int contamination) {
		this.contamination = contamination;
	}

	public String getName() {
		return name;
	}

	public  String toString(){
	    return name;
    }
}
