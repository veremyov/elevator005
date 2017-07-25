package en.epam.veremyov.core.elevator;

import java.util.ArrayList;
import java.util.List;

import en.epam.veremyov.core.elevator.console.Console;
import en.epam.veremyov.core.elevator.way.Way;
import en.epam.veremyov.core.person.Person;

public class Elevator implements Console{
	private Way way;
	private int sizeFlr, // max floor
		flr, // floor post
		maxPpl; // max people in elevator
	private int x, y; // (x,y)
	private String result; // write info 
	private List<Person> people = new ArrayList<Person>();
	
	public Elevator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Elevator(Way way, int sizeFlr, int flr, int maxPpl, int x, int y, String result, List<Person> people) {
		super();
		this.way = way;
		this.sizeFlr = sizeFlr;
		this.flr = flr;
		this.maxPpl = maxPpl;
		this.x = x;
		this.y = y;
		this.result = result;
		this.people = people;
	}
	public Elevator(int sizeFlr, int maxPpl, int x, int y) {
		super();
		this.way = Way.down;
		this.sizeFlr = sizeFlr;
		this.flr = sizeFlr;
		this.maxPpl = maxPpl;
		this.x = x;
		this.y = y;
	}
	
	private void act(List<Person>[] floor){
		for (int j = 0; j < people.size(); j++) {
			if(people.get(j).getEndFlr() == flr) {
				people.remove(j);
			}
		}
		for (int j = 0; j < floor[sizeFlr-flr].size(); j++) {
			if(people.size() < maxPpl){
				this.add(floor[sizeFlr-flr].get(j));
				floor[sizeFlr-flr].remove(j);
				
			}
			
		}
	}
	@Override
	public void move(List<Person>[] floor){
		int i = flr; //  i - next floor
		
		if(flr == sizeFlr) way = Way.down;
		if(flr == 1) way = Way.up;
			
		i = flr;
		if (way == Way.up) {
			i++;
			act(floor);
			this.result = "run: "+flr+" -> "+i+" -up";
			this.y--;
			this.flr++;
			
		} else {
			i--;
			act(floor);
			this.result = "run: "+flr+" -> "+i+" -down";
			this.y++;
			this.flr--;	
			
		}
		
		//System.out.println(result);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean add(Person person) {
		if(people.size() < maxPpl){
			people.add(person);
			return true;
		}
		
		return false;
		
	}
	@Override
	public List<Person> getAllPeople() {
		return people;
	}
	@Override
	public String getResult() {
		if(result == null) return "Elevator";
		return result;
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
}
