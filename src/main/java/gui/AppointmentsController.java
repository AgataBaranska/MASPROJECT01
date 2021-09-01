package gui;

import com.google.common.eventbus.Subscribe;
import events.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import models.Appointment;
import models.AppointmentCart;
import models.Patient;
import org.hibernate.Session;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.Map;

public class AppointmentsController {

    @FXML
    private ListView listAppointment;

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

       AppointmentCart generatedAppointmentCart = selectedAppointment.generateAppointmentCart();

            EventBusUtility.getEventBus().post(new AppointmentCardCreated(generatedAppointmentCart));
            EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentCartView));
        }
    }

    public void loadAppointments(){
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        EntityGraph graph = session.getEntityGraph("graph.Patient.appointmentList.optometrist");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        patient = session.find(Patient.class, patient.getId(), hints);
        observableAppointmentList.setAll(patient.getAppointmentList());
        session.getTransaction().commit();
    }

    @Subscribe
    public void onShowPatientsAppointments(ShowPatientsAppointments event){
        patient = event.getPatient();
        loadAppointments();
    }

    @Subscribe
    public void onAppointmentCreated(AppointmentCreated event){
        loadAppointments();
    }

    public void btnNewAppointmentClicked(ActionEvent actionEvent) {

        //show EditAppointmentDataView pass patient
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.EditAppointmentDataView));
        EventBusUtility.getEventBus().post(new ShowPatientsAppointmentsEditor(patient));
    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.PatientsView));
    }
}
