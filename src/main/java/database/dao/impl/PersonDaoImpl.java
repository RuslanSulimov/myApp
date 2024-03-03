package database.dao.impl;

import database.dao.PersonDao;
import database.model.Person;
import database.hibernate.HibernateUtil;
import org.hibernate.Session;
import jakarta.persistence.Query;

import java.util.List;


public class PersonDaoImpl implements PersonDao {
    @Override
    public void addPerson(Person person) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }

    public void createTable() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String hqlDelete = "DROP TABLE IF EXISTS my_app.person";
        session.createNativeQuery(hqlDelete).executeUpdate();
        String hqlCreate = "CREATE TABLE my_app.person (ID SERIAL NOT NULL, FIO VARCHAR (50), BIRTH_DATE DATE, SEX VARCHAR (10))";
        session.createNativeQuery(hqlCreate).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    public List<Person> getUniquePeople() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String hql = "SELECT DISTINCT p FROM Person p ORDER BY p.FIO";
        Query query = session.createQuery(hql, Person.class);
        List<Person> personList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return personList;
    }
    public void addPersonList(List<Person> personList) {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        for (Person person: personList) {session.save(person);}
        session.getTransaction().commit();
        session.close();
    }
    public List<Person> getAllMalesByF() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String hql = "SELECT p FROM Person p WHERE FIO LIKE 'F%' AND sex = 'Male'";
        Query query = session.createQuery(hql, Person.class);
        List<Person> result = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
    public void createIndex() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String hql = "CREATE INDEX index_person ON Person p (FIO) WHERE FIO LIKE 'F%' AND sex = 'Male'";
        session.getTransaction().commit();
        session.close();
    }
    public void dropIndex() {
        Session session = HibernateUtil.openSession();
        session.beginTransaction();
        String hql = "DROP INDEX index_person";
        session.getTransaction().commit();
        session.close();
    }
}
