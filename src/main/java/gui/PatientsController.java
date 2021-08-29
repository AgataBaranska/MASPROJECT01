package gui;

import com.google.common.eventbus.Subscribe;
import events.PatientCreated;
import events.ShowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import models.Patient;

public class PatientsController {

    @FXML
    private ListView listPatients;

    public void initialize() {
        //show patientsList
        ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(Patient.getExtendWithFetchedAppointments());
        listPatients.setItems(observablePatientList.sorted());
    }
    public void btnAddNewPatientClicked(ActionEvent actionEvent) {
        //event to show EditPatientsDataView
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.EditPatientDataView));
    }
    public void btnDeletePatientClicked(ActionEvent actionEvent) {
        //check if selected from listPatients
        if (listPatients.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to be deleted");
            alert.showAndWait();
        } else {
            Patient patient = (Patient) listPatients.getSelectionModel().getSelectedItem();
            //remove patient
            patient.removePatient();
            ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(Patient.getExtent());
            listPatients.setItems(observablePatientList.sorted());
            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.PatientsView));
        }
    }

    public void btnPatientsAppointmentsClicked(ActionEvent actionEvent) {
        //check if any Patient is selected from listPatients
        if (listPatients.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to show Patient's appointments");
            alert.showAndWait();
        } else {
            Patient selectedPatient = (Patient) listPatients.getSelectionModel().getSelectedItem();
            System.out.println(selectedPatient.getAppointmentList());
            //change pane to Appointments, pass Patient
//            EventBusUtility.getEventBus().post(new ShowPatientsAppointments(selectedPatient));
//            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));


        }
    }

    @Subscribe
    public void onPatientCreated(PatientCreated event) {
        //show updated list of patients
        ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(Patient.getExtent());
        listPatients.setItems(observablePatientList.sorted());
    }


}
