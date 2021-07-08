package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment {
    private static List<Appointment> entity;
    private int Id;
    private LocalDateTime appointmentDate;
    private Patient patient; //association cardinality 1
    private Optometrist optometrist;//association cardinality 1
    private AppointmentCart appointmentCart;

    /**
     * Required by Hibernate.
     */
    private Appointment() {
    }

    public Appointment(Patient patient, Optometrist optometrist, LocalDateTime appointmentDateTime) {
        this.patient = patient;
        patient.addAppointmentToList(this);//auto reverse connection
        this.appointmentDate = appointmentDateTime;
        this.optometrist = optometrist;
        optometrist.addAppointmentToList(this);
        addToEntitiy(this);
    }

    public static List<Appointment> getAppointmentList() {
        return entity;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Column(name = "appointment_date")
    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Basic
    public Optometrist getOptometrist() {
        return optometrist;
    }

    public void setOptometrist(Optometrist optometrist) {
        this.optometrist = optometrist;
    }

    @Basic
    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    private void addToEntitiy(Appointment appointment) {
        if (entity == null) {
            entity = new ArrayList<>();
        }
        entity.add(appointment);
    }


    @Override
    public String toString() {
        return "Date: " + appointmentDate +
                ", patient: " + patient.getName() + " " + patient.getSurname() +
                ", optometrist: " + optometrist.getName() + " " + optometrist.getSurname();
    }

    public AppointmentCart generateAppointmentCart() {
        this.appointmentCart = new AppointmentCart(patient);
        return appointmentCart;
    }
}
