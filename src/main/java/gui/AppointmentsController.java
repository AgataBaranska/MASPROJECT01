package gui;

import com.google.common.eventbus.Subscribe;
import events.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.Appointment;
import models.AppointmentCart;
import models.Patient;
import org.hibernate.Session;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentsController {

    @FXML
    private ListView listAppointment;

    @FXML
    private Label labelPatient;
    private Patient patient;

    private ObservableList<Appointment> observableAppointmentList;

    public void initialize(){
        observableAppointmentList= FXCollections.observableArrayList();
        listAppointment.setItems(observableAppointmentList);
    }

    public void btnGenerateAppointmentCartClicked(ActionEvent actionEvent) {

        if( listAppointment.getSelectionModel().getSelectedItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select an appointment in the list to create an Appointment Cart");
            alert.showAndWait();
        }else {
            //pass generatedAppointmentCart to AppointmentCartController
       Appointment selectedAppointment =(Appointment)listAppointment.getSelectionModel().getSelectedItem();
       System.out.println("Selected appointment"+selectedAppointment);

       AppointmentCart generatedAppointmentCart = selectedAppointment.generateAppointmentCart();

            EventBusUtility.getEventBus().post(new AppointmentCardCreated(generatedAppointmentCart));
            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentCartView));
        }
    }

    public void setPatientsAppointmentsListView(){
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        System.out.println("load patient's appointment list (not loaded before - lazy)");
        EntityGraph graph = session.getEntityGraph("graph.Patient.appointmentList");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        patient = session.find(Patient.class, patient.getId(), hints);

        observableAppointmentList.setAll(patient.getAppointmentList());

        session.getTransaction().commit();
    }

    @Subscribe
    public void onShowPatientsAppointments(ShowPatientsAppointments event){
        patient = event.getPatient();
        setPatientsAppointmentsListView();
    }

    @Subscribe
    public void onAppointmentCreated(AppointmentCreated event){
        setPatientsAppointmentsListView();
    }

    public void btnNewAppointmentClicked(ActionEvent actionEvent) {

        //show EditAppointmentDataView pass patient
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.EditAppointmentDataView));
        EventBusUtility.getEventBus().post(new ShowPatientsAppointmentsEditor(patient));
        System.out.println("Selected patient in Patients Controller sending to appointment editor" + patient);
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.PatientsView));
    }
}
