package gui;

import javafx.beans.Observable;
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

import java.util.List;

public class MainController {

    @FXML
    private ListView listAppointment;

//field form AppointmentCart.fxml
    @FXML
    Label labelPatient;
    @FXML
    public void initialize() {
    //show appointmentsList
        ObservableList<Appointment> observableAppointmentList = FXCollections.observableArrayList(Appointment.getAppointmentList());
        listAppointment.setItems(observableAppointmentList);
    }


    public void btnGenerateAppointmentCartClicked(ActionEvent actionEvent) {

        if( listAppointment.getSelectionModel().getSelectedItems().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("select");
            alert.setHeaderText(null);
            alert.setContentText("Select an appointment in the list to create an Appointment Cart");
            alert.showAndWait();
        }else {
            //passData to AppointmentCartController
                //get selected Patient from the Appointment
       Appointment selectedAppointment =(Appointment)listAppointment.getSelectionModel().getSelectedItem();
       AppointmentCart generatedAppointmentCart = selectedAppointment.generateAppointmentCart();
       AppointmentCartController.appointmentCart = generatedAppointmentCart;
            Main.set_pane(1);




        }
    }

    public void btQuitClicked(ActionEvent actionEvent) {

    }
}
