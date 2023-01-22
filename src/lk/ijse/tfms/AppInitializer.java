package lk.ijse.tfms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

            //  primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/accountant/DashboardForm.fxml"))));
            // primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/LoginForm.fxml"))));
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/admin/AdminDashBoardForm.fxml"))));
            primaryStage.show();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Tea Factory Management System");

    }
}
