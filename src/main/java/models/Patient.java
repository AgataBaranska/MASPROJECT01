package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@NamedEntityGraph(
        name = "graph.Patient.appointmentList.optometrist",
        attributeNodes = @NamedAttributeNode(value = "appointmentList", subgraph = "subgraph.appointment"),
        subgraphs = {
                @NamedSubgraph(name = "subgraph.appointment",
                        attributeNodes = @NamedAttributeNode(value = "optometrist")),
        })
public class Patient extends Person {

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private RodoForm rodo;

    @OneToMany(mappedBy = "patient",  orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *

    private static List<Patient> extent = new ArrayList<>();

    /**
     * Required by Hibernate.
     */
    Patient() {
    }

    public Patient(String name, String surname, String pesel, String telephone, String email, String street,
                   String city, String postalCode, String country) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country);
        this.rodo = generateRodoForm();
        addToExtent(this);
        appointmentList = new ArrayList<>();
    }

    private static void addToExtent(Patient patient) {
        extent.add(patient);
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(patient);
        session.getTransaction().commit();
    }

    public static List<Patient> loadExtent() {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        extent = session.createQuery("FROM Patient", Patient.class).list();
        session.getTransaction().commit();
        return extent;
    }

    public RodoForm getRodo() {
        return rodo;
    }

    public void setRodo(RodoForm rodo) {
        this.rodo = rodo;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public static void deletePatient(Patient patient) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(patient);
        session.getTransaction().commit();
        session.close();
        extent.remove(patient);
    }

    private RodoForm generateRodoForm() {
        return new RodoForm("SignatureTemplate");
    }

    public void addAppointmentToList(Appointment appointment) {
        appointmentList.add(appointment);
    }

    @Override
    public String toString() {
        return "Patient: " +
                getSurname() + " " +
                getName() + ", pesel: " +
                getPesel() ;
    }
}
