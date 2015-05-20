public class Section{

	private String section;
	private String entryPoint;
	private String exitPoint;
	private int price;
	
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
	
