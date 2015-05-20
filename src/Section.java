//Cette classe permet de créer une section.
//Une section a pour attribut :
//	- un nom de section, cet attribut est unique pour chaque section, il sert de primary key.
//	- un point de départ
//	- un point d'arrivé
//	- un prix, chaque voiture entrant dans cette section sera facturée de ce prix.

public class Section{

	private String section;
	private String entryPoint;
	private String exitPoint;
	private int price;
	
	//Constructeur de la classe Section
	public Section(String section,String entryPoint, String exitPoint, int price) {
		this.section = section;
		this.entryPoint = entryPoint;
		this.exitPoint = exitPoint;
		this.price = price;
	}
	
	public String getSection() {
		return section;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getEntryPoint() {
		return entryPoint;
	}
	
	public String getExitPoint(){
		return exitPoint;
	}

	@Override
	public String toString() {
		return "-Section : " + section + " -entryPoint: " + entryPoint +" -exitPoint: " + exitPoint + " -Price: " + price; 
	}

}
	
