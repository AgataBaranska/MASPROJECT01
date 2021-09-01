package events;

import models.Patient;

public class ShowPatientsAppointmentsEditor {
    private Patient patient;
    public ShowPatientsAppointmentsEditor(Patient selectedPatient) {
        patient = selectedPatient;
    }

    public Patient getPatient() {
        return patient;
    }

}
