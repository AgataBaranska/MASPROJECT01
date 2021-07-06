package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    static AnchorPane root;
    static List<AnchorPane> grid = new ArrayList<>();

    private static int idx_cur = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{

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
