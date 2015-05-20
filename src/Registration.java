import java.util.Date;

//Cette classe permet de créer un enregistrement d'une voiture dans une section donnée.
//Un enregistrement a pour attribut :
//	- le nom de la plaque de la voiture enregistrée
//	- le nom de la section dans laquelle l'enregistrement a eu lieux
//	- la date de l'enregistrement

public class Registration implements Comparable<Registration> {

	private String plate;
	private String section;
	private Date date;
	
	public Registration(String plate, String section, Date date) {
		this.plate = plate;
		this.section = section;
		this.date = date;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public String getSection() {
		return section;
	}
	
	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "-Plate # : " + plate + " -Section: " + section + " -Date: " + date; 
	}

	@Override
	public int compareTo(Registration o) {
		return 0;
	}

}
	
