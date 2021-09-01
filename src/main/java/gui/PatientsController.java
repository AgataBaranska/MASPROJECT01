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
    private ListView listPatients;

    @FXML
    private TextField textFieldPesel;

    private ObservableList<Patient> observablePatientsList;

    public void initialize(){
        observablePatientsList = FXCollections.observableArrayList();
        listPatients.setItems(observablePatientsList);
        setListPatients(Patient.getExtent());
    }

    private void setListPatients(List<Patient> list){
       observablePatientsList.setAll(list);
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
            observablePatientsList.setAll(Patient.getExtent());
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
            //change pane to Appointments, pass Patient
            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));
            EventBusUtility.getEventBus().post(new ShowPatientsAppointments(selectedPatient));
        }
    }

    @Subscribe
    public void onPatientCreated(PatientCreated event) {
        //show updated list of patients
        setListPatients(Patient.getExtent());
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        HibernateUtility.getSessionFactory().close();
        Platform.exit();
    }

    public void filterListOnKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){
            String textPesel = textFieldPesel.getText();
            if(!textPesel.isEmpty()){
                List<Patient> filtered =  Patient.getExtent().stream().filter(p->p.getPesel().equals(textPesel)).collect(Collectors.toList());
                setListPatients(filtered);
            }else{
                setListPatients(Patient.getExtent());
            }
        }
    }
}
