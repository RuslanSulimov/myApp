package database.dao;

import database.model.Person;

import java.util.List;
import java.util.function.Predicate;

public interface PersonDao {
    void addPerson(Person person);
    List<Person> getUniquePeople();
    void addPersonList(List<Person> personList);
    void createIndex();
    void dropIndex();

}
