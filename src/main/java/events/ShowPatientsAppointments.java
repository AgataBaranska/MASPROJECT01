package events;

import models.Appointment;
import models.Patient;

import java.util.List;

public class ShowPatientsAppointments {
    private Patient patient;
    public ShowPatientsAppointments(Patient selectedPatient) {
        patient = selectedPatient;
    }

    public Patient getPatient() {
      return patient;
    }
}
