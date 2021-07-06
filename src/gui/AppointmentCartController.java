package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;

public class AppointmentCartController {
    @FXML
    TextField txtGlassesRightEye;
    @FXML
    TextField txtGlassesLeftEye;
    @FXML
    ListView listGlassesCorrection;

    @FXML
    ComboBox comboPurposeGlasses;

    @FXML
    Label labelPatient;

    //AppointmentCart object generated in MainController
    public  AppointmentCart appointmentCart;


    @FXML
    public void initialize() {

        //Patient label
      // labelPatient.setText(appointmentCart.getPatient().getName() + appointmentCart.getPatient().getSurname());

        //Glasses Purpose ChoiceBox
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_DISTANCE);
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_READING);
    }


    public void btnAddGlassesCorrectionClicked(ActionEvent actionEvent) {
        if (txtGlassesRightEye.getText().isEmpty()) {
            txtGlassesRightEye.setText("Please enter value");
        } else if (txtGlassesLeftEye.getText().isEmpty()) {
            txtGlassesLeftEye.setText("Please enter value");
        } else if (comboPurposeGlasses.getValue()== null) {
            comboPurposeGlasses.show();
        } else {
            GlassesCorrection glassesCorrection = new GlassesCorrection(txtGlassesRightEye.getText(), txtGlassesLeftEye.getText(), (GlassesCorrection.CorrectionPurpose) comboPurposeGlasses.getValue());

        }
    }






    public void btnAddLensesCorrectionClicked(ActionEvent actionEvent) {


    }


    public void btnSaveClicked(ActionEvent actionEvent) {
        //saveChanges
        Main.set_pane(0);
    }



    public void btnCancelClicked(ActionEvent actionEvent) {
        //discard changes

        Main.set_pane(0);

    }
}
