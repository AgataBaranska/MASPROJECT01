package gui;

import com.google.common.eventbus.EventBus;
import events.PatientCreated;
import events.ShowView;
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


    private EventBus eventBus;


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

          //send event that new Patient was created
          eventBus.post(new PatientCreated());

          //send event to change view to PatientsView
            eventBus.post(new ShowView(RootPaneController.View.PatientsView));

      } catch (Exception e) {
          e.printStackTrace();
      }


    }

    public void btnDiscardClicked(ActionEvent actionEvent) {

        //Main.set_pane(Main.Panes.PatientsPane);
    }


    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
