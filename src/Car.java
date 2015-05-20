//Cette classe permet de créer des voitures.
//Une voiture a pour attribut :
//	- une plaque, cet attribut est unique pour chaque voiture, il sert de primary key.
//	- un type qui peut être : car, motorcycle, truck, police

public class Car{

	private String plate;
	private String type;
	
	//Constructeur de la classe Car
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
	
