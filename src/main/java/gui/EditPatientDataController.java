package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import models.Patient;
import org.hibernate.Session;

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
        //create new Patient and save in db
        Patient patient = new Patient(txtName.getText(),txtSurname.getText(),txtPesel.getText(),txtTelephone.getText(),txtEmail.getText(),txtStreet.getText(),txtCity.getText(),txtPostalCode.getText(),txtCountry.getText());
      try {
          Session session = HibernateUtility.getSessionFactory().openSession();
          session.beginTransaction();
          session.saveOrUpdate(patient);
          session.getTransaction().commit();
          session.close();

          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Alert");
          alert.setHeaderText(null);
          alert.setContentText("New Patient added to Database");
          alert.showAndWait();
          Main.set_pane(Main.Panes.PatientsPane);

      } catch (Exception e) {
          e.printStackTrace();
      }


    }

    public void btnDiscardClicked(ActionEvent actionEvent) {
    Main.set_pane(Main.Panes.PatientsPane);
    }
}
