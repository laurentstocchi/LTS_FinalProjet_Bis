import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class main {
	
	static Random rand = new Random();
	static int firstPlateNumber, indiceLetter, secondPlateNumber, indiceSections, year, month, day, indiceType;
	static char firstLetter, secondLetter, thirdLetter, fourthLetter;
	static String theType, plate;
	static Date theDate;
	static int formatPlate;
	static int compteurFR = 0;
	static int compteurLux = 0;
	static int compteurBel = 0;
	static int compteurAll = 0;
	static int compteurPolice = 0;
	static String theSection;
	static String [] carTypes = {"car","car","car","car","car","truck","truck","motorcycle","police"};
	static String [] lesSections = {"A10","A11","A12","A13","A14","A15"};
	static char [] arrayLetter = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	//Alphabet pour tirer au sort des lettres pour création automatique et aléatoire des plaques des véhicules
	
	//Création des sections en premier, elles ne seront pas répertoriées dans une Collection ou un HashMap 
	// car l'ensemble complet des sections est connue à l'avance par le système, il n'y aura aucune possibilité
	// d'en rajouter d'autres pendant l'exécution du logiciel.
	static Section sectionA10 = new Section("A10","Esch","Luxembourg",3);
	static Section sectionA11 = new Section("A11","Esch","Rodange",2);
	static Section sectionA12 = new Section("A12","Wiltz","Luxembourg",2);
	static Section sectionA13 = new Section("A13","Luxembourg","Metz",6);
	static Section sectionA14 = new Section("A14","Luxembourg","Trier",4);
	static Section sectionA15 = new Section("A15","Luxembourg","Arlon",5);

	//On choisit une Collection pour stocker les registrations afin de pouvoir effectuer des 
	//requêtes différentes sur cette collection via la fonction private static <E, T> void process
	public static Collection<Registration> registrations = new ArrayList<Registration>();
	
	//On choisit un HashMap pour stocker les voitures afin d'éviter les doublons
	private static HashMap<String,Car> vehicles = new HashMap<String,Car>();
	
	//On choisit un HashMap pour stocker les password et login afin de vérifier facilement 
	//la concordence entre les clés et les valeurs pour pouvoir se connecter
	private static HashMap<String,String> passwordLogin = new HashMap<String,String>();
	
	private static <E, T> void process(Iterable<E> elements, Predicate<E> predicate, Function<E, T> mapper, Consumer<T> block) {
		for (E e : elements) {
			if (predicate.test(e)) {
				T value = mapper.apply(e);
				
				block.accept(value);
			}
		}
	}
	
	//Fonction permettant de facturer une voiture, prend en paramètre l'object Car qu'il faut facturer
	private static int getPrice(Car car){
		int price = 0;
		
		//si le véhicule a facturer fait partie de la police il ne paye rien.
		if(car.getType()=="police"){
			return 0;
		}
		else {
			for (Registration r : registrations) {
				 if(r.getPlate()==car.getPlate()){
					 switch(r.getSection()){
					 case "A10" :
						 price+=sectionA10.getPrice();
						 break;
					 case "A11" :
						 price+=sectionA11.getPrice();
						 break;
					 case "A12" :
						 price+=sectionA12.getPrice();
						 break;
					 case "A13" :
						 price+=sectionA13.getPrice();
						 break;
					 case "A14" :
						 price+=sectionA14.getPrice();
						 break;
					 case "A15" :
						 price+=sectionA15.getPrice();
						 break;
					 default : 
						 price = 0;
						 break;
					 }
				 }
			}
		}
		
		return price;
	}

	//Fonction qui créé une plaque Luxembourgeoise : AA-0000
	public static String getLuxPlate(){
		indiceLetter = rand.nextInt(arrayLetter.length);
		firstLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		secondLetter = arrayLetter[indiceLetter];
		firstPlateNumber = rand.nextInt(9929 - 1000 + 1) + 1000;
		return ""+firstLetter+secondLetter+"-"+firstPlateNumber;
	}
	
	//Fonction qui créé une plaque Belge : 0-AAA-000
	public static String getBelPlate(){
		firstPlateNumber = rand.nextInt(9 - 0 + 1) + 0;
		indiceLetter = rand.nextInt(arrayLetter.length);
		firstLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		secondLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		thirdLetter = arrayLetter[indiceLetter];
		secondPlateNumber = rand.nextInt(999 - 100 + 1) + 100;
		return ""+firstPlateNumber+"-"+firstLetter+secondLetter+thirdLetter+"-"+secondPlateNumber;
	}
	
	//Fonction qui créé une plaque Allemande : AA-AA-0000
	public static String getAllPlate(){
		indiceLetter = rand.nextInt(arrayLetter.length);
		firstLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		secondLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		thirdLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		fourthLetter = arrayLetter[indiceLetter];
		secondPlateNumber = rand.nextInt(9999 - 1000 + 1) + 1000;
		return ""+firstLetter+secondLetter+"-"+thirdLetter+fourthLetter+"-"+secondPlateNumber;
	}
	
	//Fonction qui créé une plaque Française : AA-000-AA
	public static String getFrPlate(){
		indiceLetter = rand.nextInt(arrayLetter.length);
		firstLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		secondLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		thirdLetter = arrayLetter[indiceLetter];
		indiceLetter = rand.nextInt(arrayLetter.length);
		fourthLetter = arrayLetter[indiceLetter];
		secondPlateNumber = rand.nextInt(999 - 100 + 1) + 100;
		return ""+firstLetter+secondLetter+"-"+secondPlateNumber+"-"+thirdLetter+fourthLetter;
	}
	
	public static void main(String[] args) {
		java.util.Scanner entree =   new java.util.Scanner(System.in);
		
		//ajout des password/login acceptés par le système
		passwordLogin.put("1234","lstocchi");
		passwordLogin.put("4321","imendoza");
		
		System.out.println("You must be logged to continue...");
		
		int index=0;
		int i = 0;
		int menuChoice = 0;
		
		//boucle do { }while() tant que l'utilisateur n'ai pas connecté
		do{
			
			System.out.println("Login :");
			String login = entree.next();
			System.out.println("Password :");
			String password = entree.next();
		
		Iterator iterator = passwordLogin.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
	 
			if(key.equals(password) && value.equals(login)){
				index=1;
				break;
			}
		}
		
		if(index==1){
			System.out.println("Welcome to the Luxembourg Toll System.");
		}else {
			System.out.println("Wrong password and/or login, please try again.");
		}
		
		}while(index==0);
		
		
		//Ici l'utilisateur est connecté
		try {
			System.out.println();
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		
		//Pour initialiser le système et remplir la database
		// on insère 100 registrations dans le LTS sans les afficher à l'écran
		//Pour cela on utilise la fonction initLTS() qui créé automatiquement 100 enregistrements
		initLTS();
		
		//Puis on en insere 10 nouvelles en les affichant à l'écran
		//pour que l'utilisateur voit de lui même le système fonctionner
		displayRegistrations(10);
		
		System.out.println();
		
		//une fois les valeurs insérées dans le système, 
		//le menu avec les différentes requêtes peut s'afficher
		do{
			System.out.println();
			menuChoice = 0;
			
			System.out.println("----------------Luxembourg_Toll_System----------------");
			System.out.println();
			System.out.println("1. Display all the registrations");
			System.out.println("2. Display cars traveling on a given section");
			System.out.println("3. Display the section used by a given car");
			System.out.println("4. Display cars circulating for a given date");
			System.out.println("5. Display all the cars");
			System.out.println("6. Billing of a car");
			System.out.println("7. Display the drivers's statistics");
			System.out.println("8. Continue to observe the registration to the system");
			System.out.println("9. Display informations about a section");
			System.out.println("10. Display all the sections");
			System.out.println("11. Display all the police and rescue workers");
			System.out.println("12. Log out");
			System.out.println();
			System.out.println("----------------Luxembourg_Toll_System----------------");
			
			menuChoice = entree.nextInt();
			
			switch(menuChoice){
			case 1 : 
				process(registrations, e -> true, e -> e, e -> System.out.println(e));
				break;
			case 2 :
				System.out.println("Enter the name of a section..");
				theSection = entree.next();
				process(registrations, e -> e.getSection().contains(theSection), e -> e.getPlate(), t -> System.out.println(t));
				break;
			case 3 : 
				System.out.println("Enter the car's plate..");
				plate = entree.next();
				process(registrations, e -> e.getPlate().contains(plate), e -> e.getSection(), t -> System.out.println(t));
				break;
			case 4 : 
				java.util.Scanner saisirDate =   new java.util.Scanner(System.in);
				try {
					System.out.println("Enter a year..");
					year = saisirDate.nextInt();
					System.out.println("Enter a month..");
					month = saisirDate.nextInt();
					System.out.println("Enter a day..");
					day = saisirDate.nextInt();
					System.out.println("Here are the cars registered on "+day+"/"+month+"/"+year+" :");
					year -= 1900;
					month -= 1;
					theDate = new Date(year, month, day);
					process(registrations, e -> e.getDate().compareTo(theDate)==0, e -> e.getPlate(), t -> System.out.println(t));
					break;
				}catch(InputMismatchException e){
					System.out.println("Invalid Date Format.");
				}
				System.out.println("Back to main menu.");
				break;
			case 5 :
				System.out.println(vehicles.toString());
				break;
			case 6 : 
				System.out.println("Enter the car's plate you want to bill..");
				plate = entree.next();
				if(!vehicles.containsKey(plate)){
					System.out.println("This plate is not recorded in the system.");
					break;
				}
				int price = getPrice(vehicles.get(plate));
				System.out.println("This vehicle will be charged to "+price+"€.");
				break;
			case 7 :
				int compteurTotal=compteurFR+compteurLux+compteurBel+compteurAll;
				double pourcentage;
				pourcentage = (100*compteurFR)/compteurTotal;
				System.out.println("Fr : "+compteurFR+" ==> "+pourcentage+" %");
				pourcentage = (100*compteurLux)/compteurTotal;
				System.out.println("Lux : "+compteurLux+" ==> "+pourcentage+" %");
				pourcentage = (100*compteurBel)/compteurTotal;
				System.out.println("Bel : "+compteurBel+" ==> "+pourcentage+" %");
				pourcentage = (100*compteurAll)/compteurTotal;
				System.out.println("All : "+compteurAll+" ==> "+pourcentage+" %");
				
				System.out.println();
				System.out.println("----------------Police and rescue workers----------------");
				pourcentage = (100*compteurPolice)/compteurTotal;
				System.out.println("Police : "+compteurPolice+" ==> "+pourcentage+" %");
				System.out.println("NB : they can only be from Luxembourg.");
				System.out.println("----------------Police and rescue workers----------------");
				System.out.println();
				break;
			case 8 :
				//On affiche ici 30 nouvelles registrations avant de revenir au menu principal
				displayRegistrations(30);
				break;
			case 9 :
				System.out.println("Enter a section's name..");
				theSection = entree.next();
				switch (theSection){
				case "A10" : 
					System.out.println(sectionA10.toString());
					break;
				case "A11" : 
					System.out.println(sectionA11.toString());
					break;
				case "A12" : 
					System.out.println(sectionA12.toString());
					break;
				case "A13" : 
					System.out.println(sectionA13.toString());
					break;
				case "A14" : 
					System.out.println(sectionA14.toString());
					break;
				case "A15" : 
					System.out.println(sectionA15.toString());
					break;
				default : 
					System.out.println("This section does not exist.");
				}
				break;
			case 10 :
				System.out.println(sectionA10.toString());
				System.out.println(sectionA11.toString());
				System.out.println(sectionA12.toString());
				System.out.println(sectionA13.toString());
				System.out.println(sectionA14.toString());
				System.out.println(sectionA15.toString());
				break;
			case 11 :
				Iterator carIterator = vehicles.entrySet().iterator();
				
				while (carIterator.hasNext()) {
					Map.Entry entry = (Map.Entry) carIterator.next();
					String key = (String) entry.getKey();
					Car value = (Car) entry.getValue();
					
					if(value.getType()=="police"){
						System.out.println("==> "+value.getPlate());
					}
			 
				}
				break;
			case 12 :
				System.out.println("Thank you and see you soon.");
				break;
			default :
				System.out.println("Character not authorized, please try again.");
				break;
			}
			
		}while(menuChoice!=12);
		
	}
	
	//Fonction qui affiche les registrations dans le système LTS
	//qui prend en paramètre le nombre de registrations que l'on veut afficher
	public static void displayRegistrations(int numberOfRegistrations){
		int i = 0;
		
			for(i=0;i<numberOfRegistrations;i++){
			
			indiceType = rand.nextInt(carTypes.length);
			theType = carTypes[indiceType];
			
			if(theType=="police"){
				compteurPolice+=1;
				formatPlate = 1;
			}else {
				formatPlate = rand.nextInt(4 - 1 + 1)+ 1;
			}
			
			switch(formatPlate){
			case 1 : 
				compteurLux++;
				plate=getLuxPlate();
				break;
			case 2 : 
				compteurBel++;
				plate=getBelPlate();
				break;
			case 3 :
				compteurAll++;
				plate=getAllPlate();
				break;
			case 4 : 
				compteurFR++;
				plate=getFrPlate();
				break;
			default : 
				compteurLux++;
				plate=getLuxPlate();
				break;
			}
			
			indiceSections = rand.nextInt(lesSections.length);
			theSection = lesSections[indiceSections];
			year = rand.nextInt(115 - 100 + 1) + 100;
			month = rand.nextInt(11 - 0 + 1) + 0;
			day = rand.nextInt(30 - 0 + 1) + 0;
			
			Registration car = new Registration(plate,theSection, new Date(year, month, day));
			
			Car vehicle = new Car(plate,theType);
			
			if(vehicles.containsKey(plate)){
				System.out.println("This vehicle is already registered in the system, no duplication allowed.");
			}
			
			vehicles.put(plate,vehicle);
			
			registrations.add(car);
			
			System.out.println("--> Registration in section "+theSection+".");
			System.out.println("Plate number: "+plate+".");
			

			if(i%3==0){
				try {
					System.out.println();
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(i%4==0){
				try {
					System.out.println();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	//Fonction qui initialise le système
	//cette fonction créé 100 enregistrements dans le système
	//sans les afficher à l'utilisateur
	public static void initLTS(){
		int i = 0;
		for(i=0;i<100;i++){
			indiceType = rand.nextInt(carTypes.length);
			theType = carTypes[indiceType];
			
			if(theType=="police"){
				compteurPolice+=1;
				formatPlate = 1;
			}else {
				formatPlate = rand.nextInt(4 - 1 + 1)+ 1;
			}
			
			switch(formatPlate){
			case 1 : 
				compteurLux++;
				plate=getLuxPlate();
				break;
			case 2 : 
				compteurBel++;
				plate=getBelPlate();
				break;
			case 3 :
				compteurAll++;
				plate=getAllPlate();
				break;
			case 4 : 
				compteurFR++;
				plate=getFrPlate();
				break;
			default : 
				compteurLux++;
				plate=getLuxPlate();
				break;
			}
			
			indiceSections = rand.nextInt(lesSections.length);
			theSection = lesSections[indiceSections];
			year = rand.nextInt(115 - 100 + 1) + 100;
			month = rand.nextInt(11 - 0 + 1) + 0;
			day = rand.nextInt(30 - 0 + 1) + 0;
			
			Registration car = new Registration(plate,theSection, new Date(year, month, day));
			
			Car vehicle = new Car(plate,theType);
			
			vehicles.put(plate,vehicle);
			
			registrations.add(car);
			
		}
	}

}