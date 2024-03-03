import database.dao.impl.PersonDaoImpl;
import database.model.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class myApp {
    static final String[]  MALE_NAMES = {"Vavila", "Vadim", "Valentine", "Valery", "Valerian", "Varlam", "Oleg", "Artyom", "Fedor", "Maxim", "Anton"};
    static final String[] FEMALE_NAMES = {"Valeria", "Victoria", "Valentina", "Varvara", "Elena", "Olga", "Vasilisa", "Katerina", "Anastasia", "Vladislava", "Alisa"};
    static final String[] FEMALE_LAST_NAMES = {"Sidorova", "Seredkina", "Saltykova", "Sabirova", "Simonova", "Petrova", "Ivanova", "Fedorova", "Kapustina", "Matrosova", "Golubeva"};
    static final String[] MALE_LAST_NAMES = {"Sidorov", "Seredkin", "Saltykov", "Sabirov", "Simonov", "Petrov", "Ivanov", "Fedorov", "Kapustin", "Matrosov", "Golubev"};
    static final String[] MALE_SUR_NAMES = {"Artyomovich", "Vladimirovich", "Fedorovich", "Olegovich", "Petrovich", "Alexandrovich"};
    static final String[] FEMALE_SUR_NAMES = {"Artyomovna", "Vladimirovna", "Fedorovna", "Olegovna", "Petrovna", "Alexandrovna"};
    public static void main(String[] args) {
        PersonDaoImpl personDao = new PersonDaoImpl();


        if (args[0].equals("1")) {
            personDao.createTable();
        }
        if (args[0].equals("2")) {
            Person person = new Person();
            person.setFIO(args[1]);
            person.setBirthDate(LocalDate.parse(args[2]));
            person.setSex(args[3]);
            personDao.addPerson(person);
        }
        if (args[0].equals("3")) {
            for (Person person : personDao.getUniquePeople()) {
                System.out.println(person + " " + person.getAge(person));
            }
        }
        if (args[0].equals("4")) {
            Person person = new Person();
            Random random = new Random();
            List<Person> personList = new ArrayList<>();


            for (int i = 0; i < 1000000; i++) {
                if (i % 2 == 0) {
                    person.setSex("Male");
                    person.setFIO(MALE_LAST_NAMES[random.nextInt(MALE_LAST_NAMES.length)] + " " + MALE_NAMES[random.nextInt(MALE_NAMES.length)] + " " + MALE_SUR_NAMES[random.nextInt(MALE_SUR_NAMES.length)]);
                    person.setBirthDate(LocalDate.of(random.nextInt(1980, 2010), random.nextInt(1, 12), random.nextInt(1, 29)));

                    personDao.addPerson(person);

                } else {
                    person.setSex("Female");
                    person.setFIO(FEMALE_LAST_NAMES[random.nextInt(FEMALE_LAST_NAMES.length)] + " " + FEMALE_NAMES[random.nextInt(FEMALE_NAMES.length)] + " " + FEMALE_SUR_NAMES[random.nextInt(FEMALE_SUR_NAMES.length)]);
                    person.setBirthDate(LocalDate.of(random.nextInt(1980, 2010), random.nextInt(1, 12), random.nextInt(1, 29)));

                    personDao.addPerson(person);
                }
            }
            for (int k = 0; k < 100; k++) {
                person.setSex("Male");
                person.setFIO("Fedorov" + " " + MALE_NAMES[random.nextInt(MALE_NAMES.length)] + " " + MALE_SUR_NAMES[random.nextInt(MALE_SUR_NAMES.length)]);
                person.setBirthDate(LocalDate.of(random.nextInt(1980, 2010), random.nextInt(1, 12), random.nextInt(1, 29)));

                personList.add(person);
                personDao.addPersonList(personList);
            }
        }
        if (args[0].equals("5")) {
            long start = System.currentTimeMillis();

            personDao.getAllMalesByF().forEach(System.out::println);

            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start));
        }
        if (args[0].equals("6")) {
            personDao.createIndex();

            long start = System.currentTimeMillis();

            personDao.getAllMalesByF().forEach(System.out::println);

            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start));

            personDao.dropIndex();

        }

    }
}
