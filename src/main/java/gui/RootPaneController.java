package gui;

import com.google.common.eventbus.Subscribe;
import events.ShowView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.EnumMap;

public class RootPaneController {

    static EnumMap<View, AnchorPane> views = new EnumMap<>(View.class);
    private EditAppointmentDataController editAppointmentDataController;
    private AppointmentsController appointmentsController;
    private EditPatientDataController editPatientDataController;
    private AppointmentCardController appointmentCardController;
    private PatientsController patientsController;
    private AnchorPane rootPane;
    private AnchorPane curPane;


    private void setPane(AnchorPane newPane) {
        rootPane.getChildren().remove(curPane);
        rootPane.getChildren().add(newPane);
        curPane = newPane;

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        AnchorPane.setRightAnchor(newPane, 0.0);
    }

    @Subscribe
    public void onShowView(ShowView event) {
        View requiredView = event.getView();

        switch (requiredView) {
            case PatientsView:
                if (patientsController == null) {
                    patientsController = initView(View.PatientsView, "patients.fxml");
                }
                break;
            case EditPatientDataView:
                if (editPatientDataController == null) {
                    editPatientDataController = initView(View.EditPatientDataView, "editPatientData.fxml");
                }
                break;
            case AppointmentsView:
                if (appointmentsController == null) {
                    appointmentsController = initView(View.AppointmentsView, "appointments.fxml");
                }
                break;
            case EditAppointmentDataView:
                if (editAppointmentDataController == null) {
                    editAppointmentDataController = initView(View.EditAppointmentDataView, "editAppointmentData.fxml");
                }
                break;
            case AppointmentCartView:
                if (appointmentCardController == null) {
                    appointmentCardController = initView(View.AppointmentCartView, "appointmentCart.fxml");

                }
                break;
        }

        setPane(views.get(requiredView));
    }

    private <T> T initView(View view, String fileName) {
        FXMLLoader appointmentCartLoader = new FXMLLoader(
                getClass().getClassLoader().getResource(fileName));
        AnchorPane pane = null;
        try {
            pane = appointmentCartLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        T controller = appointmentCartLoader.getController();
        views.put(view, pane);
        EventBusUtility.getEventBus().register(controller);
        return controller;

    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    private Object loadFxmlFile(String file, View view) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(file));
        AnchorPane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object controller = loader.getController();
        views.put(view, pane);
        return controller;
    }


    public enum View {
        PatientsView,
        AppointmentsView,
        AppointmentCartView,
        EditPatientDataView,
        EditAppointmentDataView
    }

}
