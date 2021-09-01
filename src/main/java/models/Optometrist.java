package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optometrist")
public class Optometrist extends Employee {

    private static List<Optometrist> extent = new ArrayList<>();
    @Column(name = "optometrist_number")
    private String optometristNumber;
    //cascade = CascadeType.ALL,
    @OneToMany(mappedBy = "optometrist",  orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *

    /**
     * Required by Hibernate.
     */
    Optometrist() {
    }

    public Optometrist(String name, String surname, String pesel, String telephone, String email, String street,
                       String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
                       double monthlySalary, String optometristNumber) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
                monthlySalary);
        this.optometristNumber = optometristNumber;
        addToExtent(this);
    }

    public static List<Optometrist> getExtent() {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        extent = session.createQuery("FROM Optometrist", Optometrist.class).list();
        System.out.println();
        session.getTransaction().commit();
        return extent;
    }

    private void addToExtent(Optometrist optometrist) {
        extent.add(optometrist);
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(optometrist);
        session.getTransaction().commit();
        session.close();
    }

    public void addAppointmentToList(Appointment appointment) {
        if (appointmentList == null) {
            appointmentList = new ArrayList<>();
        }

        appointmentList.add(appointment);
    }

    public String getOptometristNumber() {
        return optometristNumber;
    }

    public void setOptometristNumber(String optometristNumber) {
        this.optometristNumber = optometristNumber;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public String toString() {
        return "Optometrist: " + getSurname() + " " + getName() +
                " NO" + optometristNumber;
    }
}
