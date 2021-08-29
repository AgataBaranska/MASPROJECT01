package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@NamedEntityGraph(
        name = "graph.patientAppointments",
        attributeNodes = @NamedAttributeNode("appointmentList")
)
public class Patient extends Person {

    @Basic
    private static int counterId = 0;
    @OneToOne(cascade = CascadeType.ALL)
    private RodoForm rodo;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *
    private static List<Patient> extent = new ArrayList<>();;

    /**
     * Required by Hibernate.
     */
    Patient() {
    }

    public Patient(String name, String surname, String pesel, String telephone, String email, String street,
                   String city, String postalCode, String country) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country);
        this.rodo = generateRodoForm();
        addPatient(this);
        appointmentList = new ArrayList<>();
    }

    private static void addPatient(Patient patient) {
        extent.add(patient);
//        Session session = HibernateUtility.getSessionFactory().openSession();
//        session.beginTransaction();
//        session.save(patient);
//        session.getTransaction().commit();
//        session.close();

      //  System.out.println("Patient added to db " + patient);
    }

    public static List<Patient> getExtent() {
        return extent;
    }

    public static List<Patient> getExtendWithFetchedAppointments(){

        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        EntityGraph<?> entityGraph = session.createEntityGraph("graph.patientAppointments");
        List<Patient> patientsList = session.createQuery("SELECT p FROM Patient p ", Patient.class).setHint("javax.persistence.fetchgraph", entityGraph).list();

        entityGraph.addAttributeNodes("appointmentList");
        System.out.println();
        session.getTransaction().commit();
        session.close();

        return patientsList;
    }

    public static int getCounterId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Patient.counterId = counterId;
    }

    public void removePatient() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
        session.close();
        System.out.println("Patient removed " + this);
        extent.remove(this);
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

    private RodoForm generateRodoForm() {
        return new RodoForm("SignatureTemplate");
    }

    private int generateIdNumber() {
        return ++counterId;
    }

    public void addAppointmentToList(Appointment appointment) {
        appointmentList.add(appointment);
    }

    @Override
    public String toString() {
        return "Patient[ " +
                getSurname() + " " +
                getName() + " Pesel: " +
                getPesel() + " ]";
    }
}
