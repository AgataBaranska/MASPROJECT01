package gui;

import com.google.common.eventbus.Subscribe;
import events.AppointmentCardCreated;
import events.ShowView;
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

public class AppointmentCardController {
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

    //AppointmentCart object generated in AppointmentsController
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
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            //session.saveOrUpdate(appointmentCart);
            session.update(appointmentCart.getAppointment());

//            if(!appointmentCart.getGlassesCorrectionList().isEmpty()) {
//                for (GlassesCorrection glassesCorrection : appointmentCart.getGlassesCorrectionList()) {
//                    session.saveOrUpdate(glassesCorrection);
//                }
//            }
//
//            if(!appointmentCart.getLensesCorrectionList().isEmpty()) {
//                for (LensesCorrection lensesCorrection : appointmentCart.getLensesCorrectionList()) {
//                    session.saveOrUpdate(lensesCorrection);
//                }
//            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText("Appointment Cart saved in database");
        alert.showAndWait();
        clearAppointmentCartView();

        EventBusUtility.getEventBus().post(new ShowView(RootPaneController.View.AppointmentsView));
    }

    public void btnQuitClicked(ActionEvent actionEvent) {
        HibernateUtility.getSessionFactory().close();
        clearAppointmentCartView();
        Platform.exit();
    }

    @Subscribe
    public void onAppointmentCardCreated(AppointmentCardCreated event){
        this.appointmentCart = event.getAppointmentCart();
    }
    private void clearAppointmentCartView(){
        txtGlassesLeftEye.clear();
        txtGlassesRightEye.clear();
        txtLensesLeftEye.clear();
        txtLensesRightEye.clear();
        txtInterview.clear();
        txtRecommendations.clear();
        comboPurposeGlasses.getSelectionModel().clearSelection();
        listAvailableLenses.getSelectionModel().clearSelection();
        listLensesCorrection.getItems().clear();
        listGlassesCorrection.getItems().clear();
    }
}
