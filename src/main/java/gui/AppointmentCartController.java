package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
    @FXML
    TextArea txtInterview;
    @FXML
    TextArea txtRecommendations;

    //AppointmentCart object generated in MainController
    private AppointmentCart appointmentCart;


    @FXML
    public void initialize() {


        //Glasses Purpose ComboBox
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_DISTANCE);
        comboPurposeGlasses.getItems().add(GlassesCorrection.CorrectionPurpose.FOR_READING);

        //Contact Lense ComboBox
        listAvailableLenses.getItems().addAll(ContactLense.getExtent());
        listAvailableLenses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    public void btnAddGlassesCorrectionClicked(ActionEvent actionEvent) {
        if (txtGlassesRightEye.getText().isEmpty()) {
            txtGlassesRightEye.setText("Please enter value");
        } else if (txtGlassesLeftEye.getText().isEmpty()) {
            txtGlassesLeftEye.setText("Please enter value");
        } else if (comboPurposeGlasses.getValue() == null) {
            comboPurposeGlasses.show();
        } else if (!(GlassesCorrection.checkIfValueCorrect(txtGlassesRightEye.getText())) || !(GlassesCorrection.checkIfValueCorrect(txtGlassesLeftEye.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Correction must be a positive or negative integer or 0");
            alert.showAndWait();
        } else {
            System.out.println(comboPurposeGlasses.getValue());
            GlassesCorrection glassesCorrection = new GlassesCorrection(txtGlassesRightEye.getText(), txtGlassesLeftEye.getText(), (GlassesCorrection.CorrectionPurpose) comboPurposeGlasses.getSelectionModel().getSelectedItem());
            appointmentCart.addGlassesCorrection(glassesCorrection);
            listGlassesCorrection.getItems().add(glassesCorrection);
        }
    }

    public void btnAddLensesCorrectionClicked(ActionEvent actionEvent) {
        if (txtLensesRightEye.getText().isEmpty()) {
            txtLensesRightEye.setText("Please enter value");
        } else if (txtLensesLeftEye.getText().isEmpty()) {
            txtLensesLeftEye.setText("Please enter value");
        } else if (!(LensesCorrection.checkIfValueCorrect(txtLensesLeftEye.getText())) || !(LensesCorrection.checkIfValueCorrect(txtLensesRightEye.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Correction must be a positive or negative integer or 0");
            alert.showAndWait();
        } else if (listAvailableLenses.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("Select types of lenses to add Contact Lenses Correction");
            alert.showAndWait();
        } else {
            List<ContactLense> selectedLensesList = new ArrayList<ContactLense>(listAvailableLenses.getSelectionModel().getSelectedItems());
            LensesCorrection lensesCorrection = new LensesCorrection(txtLensesRightEye.getText(), txtLensesLeftEye.getText(), selectedLensesList.toArray(new ContactLense[0]));
            appointmentCart.addLensesCorrection(lensesCorrection);
            listLensesCorrection.getItems().add(lensesCorrection);
        }
    }


    public void btnSaveClicked(ActionEvent actionEvent) {
        //add interview and recommendations to AppointmentCart
        if (!(txtInterview.getText() == null)) {
            appointmentCart.setInterview(txtInterview.getText());
        }
        if (!(txtRecommendations.getText() == null)) {
            appointmentCart.setRecommendations(txtRecommendations.getText());
        }

        System.out.println("AppointmentCrt przed zapisem"+ appointmentCart);
        //save AppointmentCart, GlassesCorrection, LensesCorrection
        SessionFactory sessionFactory = Main.getSessionFactory();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(appointmentCart);
            session.update(appointmentCart.getAppointment());

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Appointment Cart saved in database");
        alert.showAndWait();
        Main.set_pane(0);
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        Main.getSessionFactory().close();
        Platform.exit();
    }

    public void setAppointmentCart(AppointmentCart appointmentCart) {
        this.appointmentCart = appointmentCart;
        //Set Patient label
        labelPatient.setText("Patient: " + appointmentCart.getPatient().getName() + " " + appointmentCart.getPatient().getSurname());

    }
}
