package gui;

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

import java.util.ArrayList;
import java.util.List;

public class AppointmentsController {

    @FXML
    private ListView listAppointment;

    @FXML
    private Label labelPatient;

    //set by PatientsController when Patient's Appointment button is clicked
    private  List<Appointment> patientsAppointments;



    public void initialize() {
    //show appointmentsList

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

       System.out.println(generatedAppointmentCart);

      // Main.getAppointmentCartController().setAppointmentCart(generatedAppointmentCart);

     //  Main.set_pane(Main.Panes.AppointmentCartPane);

        }
    }

    public void btQuitClicked(ActionEvent actionEvent) {
        Main.getSessionFactory().close();
        Platform.exit();
    }

    public void setPatientsAppointments(List<Appointment> appointmentsList){
        this.patientsAppointments = appointmentsList;
        if(!patientsAppointments.isEmpty()) {
            ObservableList<Appointment> observableAppointmentList = FXCollections.observableList(patientsAppointments);
            listAppointment.setItems(observableAppointmentList);
        }
    }
}
