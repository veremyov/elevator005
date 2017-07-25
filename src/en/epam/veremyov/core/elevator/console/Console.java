package en.epam.veremyov.core.elevator.console;

import java.util.List;

import en.epam.veremyov.core.person.Person;

public interface Console {
	void move(List<Person>[] floor);
	boolean add(Person person); // if add retern true, else false
	List<Person> getAllPeople();
	String getResult();
	int getX();
	int getY();
}
