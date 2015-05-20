public class Car{

	private String plate;
	private String type;
	
	public Car(String plate, String type) {
		this.plate = plate;
		this.type = type;
	}
	
	public String getPlate() {
		return plate;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "=> Type : " + type+"\n"; 
	}

}
	
