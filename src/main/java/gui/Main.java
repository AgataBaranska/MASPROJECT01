package gui;

import com.google.common.eventbus.EventBus;
import events.ShowView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main extends Application {

    private static AnchorPane rootPane;
    private RootPaneController rootPaneController;

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
            Session session = HibernateUtility.getSessionFactory().openSession();
            session.beginTransaction();

            for (Patient patient : Patient.getExtent()) {
                session.save(patient);
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
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

       //Use to add mock data to DB
      //  addMockDataToDb();
        //load all data saved in db
       // loadData();
       //showApplicationExtents();

        FXMLLoader rootPaneLoader = new FXMLLoader(getClass().getClassLoader().getResource("anchor.fxml"));
        AnchorPane rootPane = rootPaneLoader.load();
        rootPaneController = rootPaneLoader.getController();
        rootPaneController.setRootPane(rootPane);

        EventBus eventBus = EventBusUtility.getEventBus();
        eventBus.register(rootPaneController);

        Scene scene = new Scene(rootPane, 600, 520);
        eventBus.post(new ShowView(RootPaneController.View.PatientsView));

        primaryStage.setTitle("OptometristApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
// only for database tests
    private void showApplicationExtents(){
    //    System.out.println(Address.getExtent().toString());
     //  System.out.println(Appointment.getExtent().toString());
       System.out.println(AppointmentCart.getExtent().toString());
      // System.out.println(ContactLense.getExtent().toString());
     System.out.println(GlassesCorrection.getExtent().toString());
      System.out.println(LensesCorrection.getExtent().toString());
//        System.out.println(Patient.getExtent().toString());
//        System.out.println(Receptionist.getExtent().toString());
//        System.out.println(RodoForm.getExtent().toString());
//        System.out.println(Training.getExtent().toString());


    }
//
//    private void loadData() {
//        try {
//            Session session = HibernateUtility.getSessionFactory().openSession();
//            session.beginTransaction();
//            Patient.setExtent(session.createQuery("FROM Patient").list());
//            RodoForm.setExtent(session.createQuery("FROM RodoForm").list());
//            Address.setExtent(session.createQuery("FROM Address").list());
//            Optometrist.setExtent(session.createQuery("FROM Optometrist").list());
//            Appointment.setExtent(session.createQuery("FROM Appointment").list());
//            AppointmentCart.setExtent(session.createQuery("FROM AppointmentCart").list());
//            ContactLense.setExtent(session.createQuery("FROM ContactLense").list());
//            Receptionist.setExtent(session.createQuery("FROM Receptionist").list());
//            Training.setExtent(session.createQuery("FROM Training").list());
//            GlassesCorrection.setExtent(session.createQuery("FROM GlassesCorrection").list());
//            LensesCorrection.setExtent(session.createQuery("FROM LensesCorrection").list());
//
//            session.getTransaction().commit();
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

