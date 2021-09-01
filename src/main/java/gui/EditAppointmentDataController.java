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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        System.out.println("Selected patient in New Appointment Controller" + patient);
    }
    public void btnSaveClicked(ActionEvent actionEvent) {
        //check if all fields with values

        Optometrist selectedOptometrist =(Optometrist) comboBoxOptometrists.getSelectionModel().getSelectedItem();
        LocalDate selectedDate = datePicker.getValue();
        LocalTime selectedTime = (LocalTime) comboTimePicker.getSelectionModel().getSelectedItem();

        Session session = HibernateUtility.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //load Optometrist appointmentList which was not loaded before - lazy
        System.out.println("load Optometrist appointmentList which was not loaded before - lazy");
       // selectedOptometrist.setAppointmentList(session.load(Optometrist.class,selectedOptometrist.getId()).getAppointmentList());
        System.out.println("Selected patient before saving appointment" + patient);
        Appointment appointment = new Appointment(patient,selectedOptometrist,LocalDateTime.of(selectedDate,selectedTime));

        session.getTransaction().commit();


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
