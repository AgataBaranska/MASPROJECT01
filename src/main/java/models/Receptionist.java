package models;

import gui.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receptionist")
public class Receptionist extends Employee {

    public static List<Receptionist> extent = new ArrayList<>();
    @ManyToMany
    private List<Training> trainingList;

    /**
     * Required by Hibernate.
     */
    Receptionist() {
    }

    public Receptionist(String name, String surname, String pesel, String telephone, String email, String street,
                        String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
                        double monthlySalary) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
                monthlySalary);
        addToExtent(this);
    }

    public static List<Receptionist> getExtent() {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        extent = session.createQuery("FROM Receptionist", Receptionist.class).list();
        session.getTransaction().commit();
        return extent;
    }

    private void addToExtent(Receptionist receptionist) {
        extent.add(receptionist);
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(receptionist);
        session.getTransaction().commit();
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "trainingList=" + trainingList +
                '}';
    }
}
