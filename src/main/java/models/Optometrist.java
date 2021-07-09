package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optometrist")
public class Optometrist extends Employee {

    private static List<Optometrist> extent;
    @Column(name = "optometrist_number")
    private String optometristNumber;
    @OneToMany(mappedBy = "optometrist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointmentList;//association cardinality *

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

    public static List<Optometrist> getExtent() {
        return extent;
    }

    public static void setExtent(List<Optometrist> extent) {
        Optometrist.extent = extent;
    }

    private void addToExtent(Optometrist optometrist) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(optometrist);
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
        return "Optometrist{" +
                "optometristNumber='" + optometristNumber + '\'' +
                ", appointmentList=" + appointmentList +
                '}';
    }
}
