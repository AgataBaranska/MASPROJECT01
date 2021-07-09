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

public class MainController {

    @FXML
    private ListView listAppointment;

//field form AppointmentCart.fxml
    @FXML
    Label labelPatient;
    @FXML
    public void initialize() {
    //show appointmentsList
        ObservableList<Appointment> observableAppointmentList = FXCollections.observableArrayList(Appointment.getExtent());
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

       Main.getAppointmentController().setAppointmentCart(generatedAppointmentCart);

       Main.set_pane(1);

        }
    }

    public void btQuitClicked(ActionEvent actionEvent) {
        Main.getSessionFactory().close();
        Platform.exit();
    }
}
