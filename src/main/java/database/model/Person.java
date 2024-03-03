package database.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Person", schema = "my_app")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "FIO")
    private String FIO;
    @Column (name = "BIRTH_DATE")
    private LocalDate birthDate;
    @Column (name = "SEX")
    private String sex;


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public int getId() {
        return id;
    }

    public String getFIO() {
        return FIO;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public int getAge(Person person) {
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(birthDate, currentDate);
        return age.getYears();
    }

    @Override
    public String toString() {
        return FIO + " " + birthDate + " " + sex;
    }
}
