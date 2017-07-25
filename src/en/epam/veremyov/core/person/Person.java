package en.epam.veremyov.core.person;

public class Person {
	private String name;
	private int startFlr, endFlr; // start&end floor
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStartFlr() {
		return startFlr;
	}
	public void setStartFlr(int startFlr) {
		this.startFlr = startFlr;
	}
	public int getEndFlr() {
		return endFlr;
	}
	public void setEndFlr(int endFlr) {
		this.endFlr = endFlr;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, int startFlr, int endFlr) {
		super();
		this.name = name;
		this.startFlr = startFlr;
		this.endFlr = endFlr;
	}
	@Override
	public String toString() {
		return "["+name + "(" + startFlr + "," + endFlr + ")] ";
	}
	
}
