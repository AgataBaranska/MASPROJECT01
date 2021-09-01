package gui;

import com.google.common.eventbus.EventBus;
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
    private AnchorPane cur_pane;


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
        View requredView = event.getView();

        switch(requredView) {
            case PatientsView:
               if(patientsController ==null){
                   FXMLLoader patientsLoader = new FXMLLoader(getClass().getClassLoader().getResource("patients.fxml"));
                   AnchorPane patientsPane = null;
                   try {
                       patientsPane = patientsLoader.load();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   patientsController = patientsLoader.getController();
                   views.put(View.PatientsView, patientsPane);
                   EventBusUtility.getEventBus().register(patientsController);
               }
                break;
            case EditPatientDataView:
                if(editPatientDataController==null){
                    FXMLLoader editPatientDataLoader = new FXMLLoader(getClass().getClassLoader().getResource("editPatientData.fxml"));
                    AnchorPane editPatientDataPane = null;
                    try {
                        editPatientDataPane = editPatientDataLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    editPatientDataController = editPatientDataLoader.getController();
                    views.put(View.EditPatientDataView, editPatientDataPane);
                    EventBusUtility.getEventBus().register(editPatientDataController);
                }
                break;
            case AppointmentsView:
                if(appointmentsController == null) {
                    FXMLLoader appointmentsLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointments.fxml"));
                    AnchorPane appointmentsPane = null;
                    try {
                        appointmentsPane = appointmentsLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    appointmentsController = appointmentsLoader.getController();
                    views.put(View.AppointmentsView, appointmentsPane);
                    EventBusUtility.getEventBus().register(appointmentsController);
                }
                break;
            case EditAppointmentDataView:
               if(editAppointmentDataController == null){
                   FXMLLoader editAppointmentDataLoader = new FXMLLoader(getClass().getClassLoader().getResource("editAppointmentData.fxml"));
                   AnchorPane editAppointmentDataPane = null;
                   try {
                       editAppointmentDataPane = editAppointmentDataLoader.load();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   editAppointmentDataController = editAppointmentDataLoader.getController();
                   views.put(View.EditAppointmentDataView, editAppointmentDataPane);
                   EventBusUtility.getEventBus().register(editAppointmentDataController);

               }
                break;
            case AppointmentCartView:
                if(appointmentCardController == null){
                    FXMLLoader appointmentCartLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointmentCart.fxml"));
                    AnchorPane appointmentCartPane = null;
                    try {
                        appointmentCartPane = appointmentCartLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    appointmentCardController = appointmentCartLoader.getController();
                    views.put(View.AppointmentCartView, appointmentCartPane);
                    EventBusUtility.getEventBus().register(appointmentCardController);

                }
                break;

        }

        set_pane(views.get(event.getView()));
    }

    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    private Object loadFxmlFile(String file, View view){
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
