package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    private int id;
    @Basic
    private static int counterId = 0;

    private static List<Patient> extent;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "rodo_id", referencedColumnName = "id")
    @OneToOne
    private RodoForm rodo;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *

    /**
     * Required by Hibernate.
     */
    private Patient() {
    }

    public Patient(String name, String surname, String pesel, String telephone, String email, String street,
                   String city, String postalCode, String country) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country);
       // this.id = generateIdNumber();
        this.rodo = generateRodoForm();
        addPatient(this);
    }


    public static int getCounterOfId() {
        return counterId;
    }

    public static void setCounterId(int counterId) {
        Patient.counterId = counterId;
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






//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public static List<Patient> getExtent() {
        return extent;
    }

    public static void setExtent(List<Patient> extent) {
        Patient.extent = extent;
    }

    public static int getCounterId() {
        return counterId;
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
        if (appointmentList == null) {
            appointmentList = new ArrayList<>();
        }
        appointmentList.add(appointment);
    }
}
