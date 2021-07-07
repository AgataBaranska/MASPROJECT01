package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @GeneratedValue
    private int Id;
    
    private LocalDateTime appointmentDate;

    private Patient patient; //association cardinality 1
    private Optometrist optometrist;//association cardinality 1
    private AppointmentCart appointmentCart;

    private static List<Appointment> entity;
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

    private void addToEntitiy(Appointment appointment) {
        if (entity == null) {
            entity = new ArrayList<>();
        }
        entity.add(appointment);
    }

    public Patient getPatient() {
        return patient;
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
