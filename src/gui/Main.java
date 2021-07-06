package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Appointment;
import models.Employee;
import models.Optometrist;
import models.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    static AnchorPane root;
    static List<AnchorPane> grid = new ArrayList<>();

    private static int idx_cur = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{
 //generate fake data

        Patient patient1 = new Patient("Ala","Kowalska","109202183212","723928176","ala@fajna.com","Ogrodowa","Konstancin","02-345","Poland");
        Patient patient2= new Patient("Kasia","Nowak","109202183212","723928176","ala@fajna.com","Ogrodowa","Konstancin","02-345","Poland");
        Optometrist optometrist1 = new Optometrist("Ala","Kowalska","109202183212","723928176","ala@fajna.com","Ogrodowa","Konstancin","02-345","Poland", LocalDate.of(2015,12,13), Employee.ContractType.FULL_TIME, 4500,"NO2093");
        Appointment appointment1 = new Appointment(patient1,optometrist1,LocalDate.of(2021,7,20));
        Appointment appointment2 = new Appointment(patient2,optometrist1,LocalDate.of(2021,7,20));

        root = (AnchorPane) FXMLLoader.load(getClass().getResource("anchor.fxml"));
        grid.add((AnchorPane)FXMLLoader.load(getClass().getResource("main.fxml")));
        grid.add((AnchorPane)FXMLLoader.load(getClass().getResource("appointmentCart.fxml")));

        root.getChildren().add(grid.get(0));
        Scene scene = new Scene(root,600,505);
        primaryStage.setScene(scene);
        primaryStage.show();

//        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        primaryStage.setTitle("OptometristApp");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

    }

    public static void set_pane(int idx) {
        root.getChildren().remove(grid.get(idx_cur));
        root.getChildren().add(grid.get(idx));
        idx_cur = idx;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
