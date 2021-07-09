package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optometrist")
public class Optometrist extends Employee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
//    private int id;
    @Column(name = "optometrist_number")
    private String optometristNumber;


    @OneToMany(mappedBy = "optometrist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *


    private static List<Optometrist> extent;
    /**
     * Required by Hibernate.
     */
    private Optometrist() {
    }

    public Optometrist(String name, String surname, String pesel, String telephone, String email, String street,
                       String city, String postalCode, String country, LocalDate hireDate, ContractType contractType,
                       double monthlySalary, String optometristNumber) {
        super(name, surname, pesel, telephone, email, street, city, postalCode, country, hireDate, contractType,
                monthlySalary);
        this.optometristNumber = optometristNumber;
        addToExtent(this);
    }

    private void addToExtent(Optometrist optometrist) {
        if(extent ==null){
            extent = new ArrayList<>();
        }
        extent.add(optometrist);
    }

    public static List<Optometrist> getExtent() {
        return extent;
    }

    public static void setExtent(List<Optometrist> extent) {
        Optometrist.extent = extent;
    }
    //    public int getId() {
//        return id;
//    }
//
//
//    public void setId(int id) {
//        this.id = id;
//    }

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
}
