package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    private LocalDate appointmentDate;
    private Patient patient; //association cardinality 1
    private Optometrist optometrist;//association cardinality 1

    private AppointmentCart appointmentCart;

    private static List<Appointment> entity;

    public Appointment( Patient patient,Optometrist optometrist, LocalDate appointmentDate) {
        this.patient = patient;
        patient.addAppointmentToList(this);//auto reverse connection
        this.appointmentDate = appointmentDate;
        this.optometrist = optometrist;
        optometrist.addAppointmentToList(this);
        addToEntitiy(this);
    }

    private void addToEntitiy(Appointment appointment){
        if(entity ==null){
        entity = new ArrayList<>();
        }
        entity.add(appointment);
    }

    public static List<Appointment> getAppointmentList(){
        return entity;
    }

    public Patient getPatient(){
        return patient;
    }

    @Override
    public String toString() {
        return "Date: " + appointmentDate +
                ", patient: " + patient.getName() +" "+patient.getSurname() +
                ", optometrist: " + optometrist.getName() + " "+ optometrist.getSurname();
    }
   public AppointmentCart generateAppointmentCart(){
  this.appointmentCart = new AppointmentCart(patient);
  return appointmentCart;
   }
}
