package gui;

import com.google.common.eventbus.Subscribe;
import events.AppointmentCreated;
import events.ShowPatientsAppointmentsEditor;
import events.ShowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import models.Appointment;
import models.Optometrist;
import models.Patient;
import org.hibernate.Session;

import javax.persistence.EntityGraph;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class EditAppointmentDataController {

    @FXML
    public ComboBox comboBoxOptometrists;
    @FXML
    public DatePicker datePicker;
    @FXML
    public ComboBox comboTimePicker;

    private Patient patient;

    public void initialize() {
        //initialize comboBoxOptometrist with Optometrists
        ObservableList<Optometrist> observableOptometristList = FXCollections.observableArrayList(Optometrist.getExtent());
        comboBoxOptometrists.setItems(observableOptometristList.sorted());
        comboTimePicker.setItems(FXCollections.observableArrayList(Appointment.getPossibleAppointmentTime()));
    }

    @Subscribe
    public void onShowPatientsAppointmentsEditor(ShowPatientsAppointmentsEditor event){
        patient = event.getPatient();
    }
    public void btnSaveClicked(ActionEvent actionEvent) {
        //check if all fields with values

        Optometrist selectedOptometrist =(Optometrist) comboBoxOptometrists.getSelectionModel().getSelectedItem();
        LocalDate selectedDate = datePicker.getValue();
        LocalTime selectedTime = (LocalTime) comboTimePicker.getSelectionModel().getSelectedItem();

        //load appointment list of selected optometrist -
        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        EntityGraph graph = session.getEntityGraph("graph.Optometrist.appointmentList");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        selectedOptometrist = session.find(Optometrist.class, selectedOptometrist.getId(), hints);
        session.getTransaction().commit();


        Appointment appointment = new Appointment(patient,selectedOptometrist,LocalDateTime.of(selectedDate,selectedTime));

        clearEditAppointmentDataView();
        EventBusUtility.getEventBus().post(new AppointmentCreated());
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));

    }

    public void btnCancelClicked(ActionEvent actionEvent) {
        clearEditAppointmentDataView();
        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));
    }

    private void clearEditAppointmentDataView(){
     comboBoxOptometrists.getSelectionModel().clearSelection();
     datePicker.setValue(LocalDate.now());
     datePicker.getEditor().clear();
     comboTimePicker.getSelectionModel().clearSelection();
    }
}
