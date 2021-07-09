package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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

    static AnchorPane root;
    static List<AnchorPane> grid = new ArrayList<>();
    private static int idx_cur = 0;
    private static gui.MainController mainController;
    private static AppointmentCartController appotmentController;
    private static StandardServiceRegistry registry = null;
    private static SessionFactory sessionFactory = null;

    public static void set_pane(int idx) {
        AnchorPane newPane = grid.get(idx);
        root.getChildren().remove(grid.get(idx_cur));
        root.getChildren().add(grid.get(idx));
        idx_cur = idx;

        AnchorPane.setTopAnchor(newPane, 0.0);
        AnchorPane.setBottomAnchor(newPane, 0.0);
        AnchorPane.setLeftAnchor(newPane, 0.0);
        ;
        AnchorPane.setRightAnchor(newPane, 0.0);
        ;
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

    private static void addMockDataToDb() {
        Patient patient1 = new Patient("Ala", "Kowalska", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");
        Patient patient2 = new Patient("Kasia", "Nowak", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");
        Patient patient3 = new Patient("Jan", "Rogala", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");
        Patient patient4 = new Patient("Kacper", "Dąbrowski", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland");


        Optometrist optometrist1 = new Optometrist("Ala", "Kowalska", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland", LocalDate.of(2015, 12, 13), Employee.ContractType.FULL_TIME, 4500, "NO2093");
        Optometrist optometrist2 = new Optometrist("Robert", "Szalony", "109202183212", "723928176", "ala@fajna.com", "Ogrodowa", "Konstancin", "02-345", "Poland", LocalDate.of(2015, 12, 13), Employee.ContractType.FULL_TIME, 4500, "NO2093");


        Appointment appointment1 = new Appointment(patient1, optometrist1, LocalDateTime.of(2021, 7, 20, 10, 30, 0));
        Appointment appointment2 = new Appointment(patient2, optometrist2, LocalDateTime.of(2021, 7, 20, 1, 30, 0));
        Appointment appointment3 = new Appointment(patient3, optometrist1, LocalDateTime.of(2021, 7, 20, 10, 30, 0));
        Appointment appointment4 = new Appointment(patient4, optometrist2, LocalDateTime.of(2021, 7, 20, 1, 30, 0));

        ContactLense contactLense1 = new ContactLense("J&J", "Acuvue", "140", ContactLense.WearingMode.DAILY);
        ContactLense contactLense2 = new ContactLense("CooperVision", "Biofinity", "150", ContactLense.WearingMode.MONTHLY);
        ContactLense contactLense3 = new ContactLense("J&J", "Moist", "140", ContactLense.WearingMode.DAILY);

        Receptionist recepcionist1 = new Receptionist("Bolek", "Bolkowski", "9081184242", "234567543", "bolek@email.com", "Polna", "Warszawa", "02-022", "Poland", LocalDate.of(2015, 12, 13), Employee.ContractType.FULL_TIME, 4500);
        Training training1 = new Training("Księgowosc", "Super", "MądryCzłowiek");

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            for (Patient patient : Patient.getExtent()) {
                session.save(patient);
//                    session.save(patient.getRodo());
//                    session.save(patient.getAddress());
            }

            for (RodoForm rodoForm : RodoForm.getExtent()) {
                session.save(rodoForm);
            }
            for (Address address : Address.getExtent()) {
                session.save(address);
            }

            for (Optometrist optometrist : Optometrist.getExtent()) {
                session.save(optometrist);
            }
            for (Appointment appointment : Appointment.getExtent()) {
                session.save(appointment);
            }
            for (ContactLense contactLense : ContactLense.getExtent()) {
                session.save(contactLense);
            }
            for (Receptionist receptionist : Receptionist.getExtent()) {
                session.save(receptionist);
            }
            for (Training training : Training.getExtent()) {
                session.save(training);
            }


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
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create Service Registry
        registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
                .build();
        //Create Session Factory
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

//        sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Training.class)
//                .addAnnotatedClass(RodoForm.class)
//                .addAnnotatedClass(Receptionist.class)
//                .addAnnotatedClass(Patient.class)
//                .addAnnotatedClass(Optometrist.class)
//                .addAnnotatedClass(LensesCorrection.class)
//                .addAnnotatedClass(GlassesCorrection.class)
//                .addAnnotatedClass(ContactLense.class)
//                .addAnnotatedClass(AppointmentCart.class)
//                .addAnnotatedClass(Appointment.class)
//               .addAnnotatedClass(Address.class)
//                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

         addMockDataToDb();

        //loadData();

        root = FXMLLoader.load(getClass().getClassLoader().getResource("anchor.fxml"));
        FXMLLoader mainLoader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
        AnchorPane mainPane = mainLoader.load();
        mainController = mainLoader.getController();
        grid.add(mainPane);

        FXMLLoader appointmentLoader = new FXMLLoader(getClass().getClassLoader().getResource("appointmentCart.fxml"));
        AnchorPane appointmentPane = appointmentLoader.load();
        appotmentController = appointmentLoader.getController();
        grid.add(appointmentPane);

        set_pane(0);

        Scene scene = new Scene(root, 600, 520);
        primaryStage.setTitle("OptometristApp");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void loadData() {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Patient.setExtent(session.createQuery("SELECT Patient").list());
            RodoForm.setExtent(session.createQuery("SELECT RodoForm").list());
            Address.setExtent(session.createQuery("SELECT Address").list());
            Optometrist.setExtent(session.createQuery("SELECT Optometrist").list());
            Appointment.setExtent(session.createQuery("SELECT Appointment").list());
            ContactLense.setExtent(session.createQuery("SELECT ContactLense").list());
            Receptionist.setExtent(session.createQuery("SELECT Receptionist").list());
            Training.setExtent(session.createQuery("SELECT Training").list());

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


    }
}

