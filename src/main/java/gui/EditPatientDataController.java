package gui;

import events.PatientCreated;
import events.ShowView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Patient;

public class EditPatientDataController {

    @FXML
    public TextField txtName;
    @FXML
    public TextField txtSurname;
    @FXML
    public TextField txtPesel;
    @FXML
    public TextField txtTelephone;
    @FXML
    public TextField txtEmail ;
    @FXML
    public TextField txtStreet;
    @FXML
    public TextField txtPostalCode;
    @FXML
    public TextField txtCity;
    @FXML
    public TextField txtCountry;


    public void btnSaveClicked(ActionEvent actionEvent) {
        //check if all fields entered - validation here or in Patient class?

      try {
          //create new Patient and save in db
          Patient patient = new Patient(txtName.getText(),txtSurname.getText(),txtPesel.getText(),txtTelephone.getText(),txtEmail.getText(),txtStreet.getText(),txtCity.getText(),txtPostalCode.getText(),txtCountry.getText());

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText(null);
          alert.setContentText("New Patient added to Database");
          alert.showAndWait();
          //send event PatientCreated
          EventBusUtility.getEventBus().post(new PatientCreated());
          //send event to show PatientsView with updated patientList
          EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.PatientsView));
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
    public void btnDiscardClicked(ActionEvent actionEvent) {
        //send event to show PatientsView with unchanged patientList
          EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.PatientsView));
    }

}
