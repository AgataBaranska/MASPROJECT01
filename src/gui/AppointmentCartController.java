package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AppointmentCartController {
    @FXML
    TextField txtGlassesRightEye;
    @FXML
    TextField txtGlassesLeftEye;
    @FXML
    ListView listGlassesCorrection;

    public void btnAddLensesCorrectionClicked(ActionEvent actionEvent) {
if(txtGlassesRightEye.)

    }
    public void btnAddGlassesCorrectionClicked(ActionEvent actionEvent) {


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
