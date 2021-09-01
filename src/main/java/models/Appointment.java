package models;

import gui.HibernateUtility;
import org.hibernate.Session;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment {
    private static List<Appointment>  extent = new ArrayList<>();;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_appointment")
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

    private static List<LocalTime> possibleAppointmentTime = Arrays.asList( LocalTime.of(11,0),LocalTime.of(12,0),LocalTime.of(13,0),LocalTime.of(14,30));
    /**
     * Required by Hibernate.
     */
    Appointment() {
    }

    public Appointment(Patient patient, Optometrist optometrist, LocalDateTime appointmentDateTime) {
        this.patient = patient;
        patient.addAppointmentToList(this);//auto reverse connection
        this.appointmentDate = appointmentDateTime;
        this.optometrist = optometrist;
        optometrist.addAppointmentToList(this);//auto reverse connection
        addToExtent(this);
    }

    public static List<Appointment> getExtent() {
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        extent = session.createQuery("FROM Appointment", Appointment.class).list();
        session.getTransaction().commit();
        return extent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public static List<LocalTime> getPossibleAppointmentTime() {
        return possibleAppointmentTime;
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
        extent.add(appointment);
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(appointment);
        session.getTransaction().commit();
    }
    public AppointmentCart generateAppointmentCart() {
        this.appointmentCart = new AppointmentCart();
        appointmentCart.setAppointment(this);//reverse connection
        return appointmentCart;
    }

    @Override
    public String toString() {
        return "Date: " + appointmentDate +
                ", patient: " + patient.getName() + " " + patient.getSurname() +
                ", optometrist: " + optometrist.getName() + " " + optometrist.getSurname();
    }




}
