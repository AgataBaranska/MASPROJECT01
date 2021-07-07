package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;

import java.util.ArrayList;
import java.util.List;

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
    TextField txtLensesRightEye;
    @FXML
    TextField txtLensesLeftEye;
    @FXML
    ListView listLensesCorrection;
    @FXML
    ListView listAvailableLenses;

    @FXML
    Label labelPatient;

    //AppointmentCart object generated in MainController
    private  AppointmentCart appointmentCart;


    @FXML
    public void initialize() {


        //Glasses Purpose ComboBox
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_DISTANCE);
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_READING);

        //Contact Lense ComboBox
        listAvailableLenses.getItems().addAll(ContactLense.getEntity());
        listAvailableLenses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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
            appointmentCart.addGlassesCorrection(glassesCorrection);
            listGlassesCorrection.getItems().add(glassesCorrection);
        }
    }
    public void btnAddLensesCorrectionClicked(ActionEvent actionEvent) {
        if (txtLensesRightEye.getText().isEmpty()) {
            txtLensesRightEye.setText("Please enter value");
        } else if (txtLensesLeftEye.getText().isEmpty()) {
            txtLensesLeftEye.setText("Please enter value");
        } else if( listAvailableLenses.getSelectionModel().getSelectedItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText(null);
                alert.setContentText("Select types of lenses to add Contact Lenses Correction");
                alert.showAndWait();
        }else {
            List<ContactLense> selectedLensesList =new ArrayList<ContactLense>(listAvailableLenses.getSelectionModel().getSelectedItems());
            LensesCorrection lensesCorrection = new LensesCorrection(txtLensesRightEye.getText(),txtLensesLeftEye.getText(),selectedLensesList.toArray(new ContactLense[0]));
            appointmentCart.addLensesCorrection(lensesCorrection);
            listLensesCorrection.getItems().add(lensesCorrection);
        }


    }


    public void btnSaveClicked(ActionEvent actionEvent) {
        //saveChanges
        Main.set_pane(0);
    }



    public void btnCancelClicked(ActionEvent actionEvent) {
        //discard changes

        Main.set_pane(0);

    }
    public void setAppointmentCart(AppointmentCart appointmentCart){
        this.appointmentCart = appointmentCart;
        //Set Patient label
        labelPatient.setText(appointmentCart.getPatient().getName() +" "+appointmentCart.getPatient().getSurname());

    }
}
