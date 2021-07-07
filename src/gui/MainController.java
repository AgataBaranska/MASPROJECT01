package gui;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import models.Appointment;
import models.AppointmentCart;

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
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select an appointment in the list to create an Appointment Cart");
            alert.showAndWait();
        }else {
            //pass generatedAppointmentCart to AppointmentCartController
       Appointment selectedAppointment =(Appointment)listAppointment.getSelectionModel().getSelectedItem();
       AppointmentCart generatedAppointmentCart = selectedAppointment.generateAppointmentCart();

       Main.getAppotmentController().setAppointmentCart(generatedAppointmentCart);

       Main.set_pane(1);

        }
    }

    public void btQuitClicked(ActionEvent actionEvent) {
        Platform.exit();
    }
}
