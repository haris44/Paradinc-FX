package map;

/**
 * Created by Alex on 22/01/2017.
 */
public class ParadincRegion {

	// CRegion region ???
	private int contamination;
	private String name;

	public ParadincRegion(String name){
		this.name = name;
		contamination = 0;
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
}
