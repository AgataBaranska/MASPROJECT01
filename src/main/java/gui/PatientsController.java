package gui;

import com.google.common.eventbus.Subscribe;
import events.PatientCreated;
import events.ShowPatientsAppointments;
import events.ShowView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientsController {

    @FXML
    private ListView listViewPatients;

    @FXML
    private TextField textFieldPesel;

    private ObservableList<Patient> observablePatientsList;

    public void initialize() {
        observablePatientsList = FXCollections.observableArrayList();
        listViewPatients.setItems(observablePatientsList);
        setPatients(Patient.loadExtent());
    }

    private void setPatients(List<Patient> list) {
        observablePatientsList.setAll(list);

    }

    public void btnAddNewPatientClicked(ActionEvent actionEvent) {
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.EditPatientDataView));
    }

    public void btnDeletePatientClicked(ActionEvent actionEvent) {
        if (listViewPatients.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to be deleted");
            alert.showAndWait();
        } else {
            Patient patient = (Patient) listViewPatients.getSelectionModel().getSelectedItem();
            Patient.deletePatient(patient);
            observablePatientsList.setAll(Patient.loadExtent());
        }
    }

    public void btnPatientAppointmentsClicked(ActionEvent actionEvent) {
        //check if any Patient is selected from listPatients
        if (listViewPatients.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to show Patient's appointments");
            alert.showAndWait();
        } else {
            Patient selectedPatient = (Patient) listViewPatients.getSelectionModel().getSelectedItem();
            //change pane to Appointments, pass Patient
            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));
            EventBusUtility.getEventBus().post(new ShowPatientsAppointments(selectedPatient));
        }
    }

    @Subscribe
    public void onPatientCreated(PatientCreated event) {
        //show updated list of patients
        setPatients(Patient.loadExtent());
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        HibernateUtility.getSessionFactory().close();
        Platform.exit();
    }

    public void filterListOnKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String textPesel = textFieldPesel.getText();
            if (!textPesel.isEmpty()) {
                List<Patient> filtered = Patient.loadExtent().stream().filter(
                        p -> p.getPesel().equals(textPesel)).collect(Collectors.toList());
                setPatients(filtered);
            } else {
                setPatients(Patient.loadExtent());
            }
        }
    }
}
