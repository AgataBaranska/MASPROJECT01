package gui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.PatientCreated;
import events.ShowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import models.Appointment;
import models.Patient;
import org.hibernate.Session;
import java.io.IOException;
import java.util.List;

public class PatientsController {


    @FXML
    private ListView listPatients;
    private EventBus eventBus;

    public void initialize() {
        //show patientsList
        ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(Patient.getExtent());
        listPatients.setItems(observablePatientList.sorted());
    }


    public void btnAddNewPatientClicked(ActionEvent actionEvent) {
        //change view to EditPatientsDataView
      eventBus.post(new ShowView(RootPaneController.View.EditPatientDataView));
    }

    public void btnDeletePatientClicked(ActionEvent actionEvent) {
        //delete selected patient

        if(listPatients.getSelectionModel().getSelectedItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to be deleted");
            alert.showAndWait();
        }else{
        Patient patient = (Patient)listPatients.getSelectionModel().getSelectedItem();

        System.out.println("Patient to be deleted: "+patient);

        //delete patient from db
            try {
                Session session = HibernateUtility.getSessionFactory().openSession();
                session.beginTransaction();
                session.delete(patient);
                session.getTransaction().commit();
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void btnPatientsAppointmentsClicked(ActionEvent actionEvent) {
        //show selected Patient's appointments

        if(listPatients.getSelectionModel().getSelectedItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select Patient to show Patient's appointments");
            alert.showAndWait();
        }else{
            Patient selectedPatient = (Patient)listPatients.getSelectionModel().getSelectedItem();

            //change pane to Appointments, pass appointmentsList to AppointmentsController
            List<Appointment> appointmentsList =  selectedPatient.getAppointmentList();
         //   Main.getAppointmentsController().setPatientsAppointments(appointmentsList);
          //  Main.set_pane(Main.Panes.AppointmentsPane);

        }
    }


    @Subscribe
    public void onPatientCreated(PatientCreated event){
        //show updated list of patients
        ObservableList<Patient> observablePatientList = FXCollections.observableArrayList(Patient.getExtent());
        listPatients.setItems(observablePatientList.sorted());
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
