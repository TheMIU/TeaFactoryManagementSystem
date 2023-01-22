package lk.ijse.tfms.controller.admin;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.util.Optional;

public class SetConstantsFormController {
    public AnchorPane pane;

    public void salesAndReportsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);
    }

    public void manageUsersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_MANAGE_USERS, pane);
    }

    public void userDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "View as Accountant ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();
        }
    }
}
