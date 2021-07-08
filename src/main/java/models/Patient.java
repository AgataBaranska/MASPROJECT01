package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Person {

    private static int idCounter = 0;
    private int idNumber;
    private RodoForm rodoForm;
    private List<Appointment> appointmentList;//association cardinality *
    private static List<Patient> extent;
    /**
     * Required by Hibernate.
     */
    private Patient() {
    }

    public Patient(String name, String surname, String pesel, String telephone, String email, String street,
                   String city, String postalCode, String country) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country);
        this.idNumber = generateIdNumber();
        this.rodoForm = generateRodoForm();
        addPatient(this);
    }

    @Basic
    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        Patient.idCounter = idCounter;
    }

    private static void addPatient(Patient patient) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(patient);

    }

    private static void removePatient(Patient patient) {
        extent.remove(patient);
    }

    private static List<Patient> getExtent() {
        return extent;
    }

    @Id
    @GeneratedValue(generator = "increment")
    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    public RodoForm getRodoForm() {
        return rodoForm;
    }

    public void setRodoForm(RodoForm rodoForm) {
        this.rodoForm = rodoForm;
    }

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    private RodoForm generateRodoForm() {
        return new RodoForm("SignatureTemplate", this);
    }

    private int generateIdNumber() {
        return ++idCounter;
    }

    public void addAppointmentToList(Appointment appointment) {
        if (appointmentList == null) {
            appointmentList = new ArrayList<>();
        }
        appointmentList.add(appointment);
    }
}
