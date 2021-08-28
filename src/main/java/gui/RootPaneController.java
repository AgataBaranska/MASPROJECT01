package gui;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.PatientCreated;
import events.ShowView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import models.Patient;

import java.io.IOException;
import java.util.EnumMap;

public class RootPaneController {

    static EnumMap<View, AnchorPane> views = new EnumMap<>(View.class);
    private AppointmentsController appointmentsController;
    private EditPatientDataController editPatientDataController;
    private AppointmentCartController appointmentCartController;
    private PatientsController patientsController;
    private AnchorPane rootPane;
    private AnchorPane cur_pane;
    private EventBus eventBus;

    public RootPaneController() {
        try {
            FXMLLoader patientsLoader = new FXMLLoader(getClass().getClassLoader().getResource("patients.fxml"));
            AnchorPane patientsPane = patientsLoader.load();
            patientsController = patientsLoader.getController();
            views.put(View.PatientsView, patientsPane);

            FXMLLoader appointmentsLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointments.fxml"));
            AnchorPane appointmentsPane = appointmentsLoader.load();
            appointmentsController = appointmentsLoader.getController();
            views.put(View.AppointmentsView, appointmentsPane);

            FXMLLoader editPatientDataLoader = new FXMLLoader(getClass().getClassLoader().getResource("editPatientData.fxml"));
            AnchorPane editPatientDataPane = editPatientDataLoader.load();
            editPatientDataController = editPatientDataLoader.getController();
            views.put(View.EditPatientDataView, editPatientDataPane);

            FXMLLoader appointmentCartLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointmentCart.fxml"));
            AnchorPane appointmentCartPane = appointmentCartLoader.load();
            appointmentCartController = appointmentCartLoader.getController();
            views.put(View.AppointmentCartView, appointmentCartPane);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerEventBuses(){
        patientsController.setEventBus(eventBus);
        editPatientDataController.setEventBus(eventBus);
//      appointmentsController.setEventBus(eventBus);
        eventBus.register(patientsController);
        eventBus.register(editPatientDataController);

    }

        private void set_pane(AnchorPane newPane) {
        if(rootPane!=null) {
            rootPane.getChildren().remove(cur_pane);
        }
        rootPane.getChildren().add(newPane);
        cur_pane = newPane;

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
    }

    @Subscribe
    public void onShowView(ShowView event) {
        set_pane(views.get(event.getView()));
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public enum View {
        PatientsView,
        AppointmentsView,
        AppointmentCartView,
        EditPatientDataView,
    }

}
