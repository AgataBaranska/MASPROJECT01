package models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int Id;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    @ManyToOne
    private Patient patient; //association cardinality 1

    @ManyToOne
    private Optometrist optometrist;//association cardinality 1

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_cart_id", referencedColumnName = "id")
    private AppointmentCart appointmentCart;//foreign key

    private static List<Appointment> extent;
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
        addToExtent(this);
    }

    public static List<Appointment> getExtent() {
        return extent;
    }

    public static void setExtent(List<Appointment> extent) {
        Appointment.extent = extent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Optometrist getOptometrist() {
        return optometrist;
    }

    public void setOptometrist(Optometrist optometrist) {
        this.optometrist = optometrist;
    }

    public AppointmentCart getAppointmentCart() {
        return appointmentCart;
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
    }

    private void addToExtent(Appointment appointment) {
        if (extent == null) {
            extent = new ArrayList<>();
        }
        extent.add(appointment);
    }


    @Override
    public String toString() {
        return "Date: " + appointmentDate +
                ", patient: " + patient.getName() + " " + patient.getSurname() +
                ", optometrist: " + optometrist.getName() + " " + optometrist.getSurname();
    }

    public AppointmentCart generateAppointmentCart() {
        this.appointmentCart = new AppointmentCart();
        appointmentCart.setAppointment(this);//reverse connection
        return appointmentCart;
    }
}
