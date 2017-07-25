package en.epam.veremyov.core.generator;

import java.util.Random;
import en.epam.veremyov.app.panel.EPanel;
import en.epam.veremyov.core.person.Person;

public class Generator implements Runnable {
	private Random rand = new Random();
	private EPanel panel;
	private int maxFlr;
	private int maxPersFlr;
	private static String[] arr = {"Erwin Wedeking",
			"Dominque Oros",
			"Emery Kerfien",
			"Cherelle Ly",
			"Carlie Ifill",
			"Rebbeca Debellis",
			"Emely Cooley",
			"Victorina Matarazzo",
			"Vince Farago",
			"Celinda Stearman",
			"Temeka Demyan",
			"Gussie Brun",
			"Romaine Squire",
			"Iris Goe",
			"Miguel Corl",
			"Sharlene Kintzel",
			"Man Dionisio",
			"Sherill Mayeda",
			"Elwood Wilbourn",
			"Tammy William"
			};
	
	public Generator(int maxFlr,int maxPersFlr, EPanel panel) {
		super();
		this.maxFlr = maxFlr;
		this.panel = panel;
		this.maxPersFlr = maxPersFlr;
	}

	private Person createPerson(){
		Person person = new Person();
		person.setName(arr[rand.nextInt(arr.length-1)]);
		while (true) {
			person.setStartFlr(rand.nextInt(maxFlr));
			if(person.getStartFlr() != 0) break;
		}
		while (true) {
			person.setEndFlr(rand.nextInt(maxFlr));
			if(person.getStartFlr() != person.getEndFlr()
					&& person.getEndFlr() != 0) break;
			
		}
		
		return person;
		
	}
	
	@Override
	public void run() {
		Person person = createPerson();
		if(panel.getFloor()[maxFlr - person.getStartFlr()].size() < maxPersFlr){
			panel.getFloor()[maxFlr - person.getStartFlr()].add(person);
		} else {
			System.out.println("Error add ["+person.toString()+"]");
		}
		//System.out.println(person.toString());
	}

}
