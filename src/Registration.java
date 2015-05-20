import java.util.Date;

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
	
