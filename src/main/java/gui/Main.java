package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.MetadataSources;

public class Main extends Application {

    static Pane root;
    static List<Pane> grid = new ArrayList<>();
    private static int idx_cur = 0;
    private static gui.MainController mainController;
    private static AppointmentCartController appotmentController;
    StandardServiceRegistry registry = null;
    SessionFactory sessionFactory = null;

    public static void set_pane(int idx) {
        root.getChildren().remove(grid.get(idx_cur));
        root.getChildren().add(grid.get(idx));
        idx_cur = idx;
    }

    public static MainController getMainController() {
        return mainController;
    }

    public static AppointmentCartController getAppointmentController() {
        return appotmentController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                    .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //generate  data

            models.Patient patient1 = new Patient("Ala", "Kowalska", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");
            Patient patient2 = new Patient("Kasia", "Nowak", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");
            Optometrist optometrist1 = new Optometrist("Ala", "Kowalska", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland", LocalDate.of(2015, 12, 13), Employee.ContractType.FULL_TIME, 4500, "NO2093");
            Appointment appointment1 = new Appointment(patient1, optometrist1, LocalDateTime.of(2021, 7, 20, 10, 30, 0));
            Appointment appointment2 = new Appointment(patient2, optometrist1, LocalDateTime.of(2021, 7, 20, 1, 30, 0));
            ContactLense contactLense1 = new ContactLense("J&J", "Acuvue", "140", ContactLense.WearingMode.DAILY);
            ContactLense contactLense2 = new ContactLense("CooperVision", "Biofinity", "150", ContactLense.WearingMode.MONTHLY);
            ContactLense contactLense3 = new ContactLense("J&J", "Moist", "140", ContactLense.WearingMode.DAILY);

            //save all data
            session.save(patient1);


            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
                sessionFactory = null;
            }
        }

        root = FXMLLoader.load(getClass().getClassLoader().getResource("anchor.fxml"));

        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        Pane mainPane = mainLoader.load();
        mainController = mainLoader.getController();
        grid.add(mainPane);

        FXMLLoader appointmentLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointmentCart.fxml"));
        Pane appointmentPane = appointmentLoader.load();
        appotmentController = appointmentLoader.getController();
        grid.add(appointmentPane);

        root.getChildren().add(grid.get(0));
        Scene scene = new Scene(root);
        primaryStage.setTitle("OptometristApp");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

