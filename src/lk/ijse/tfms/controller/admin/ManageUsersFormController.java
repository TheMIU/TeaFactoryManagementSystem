package lk.ijse.tfms.controller.admin;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.util.CrudUtil;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ManageUsersFormController {
    public AnchorPane pane;
    public JFXButton btnChangeAdmin;
    public JFXTextField txtAdminName;
    public JFXTextField txtAdminPassword;
    public JFXButton btnUserChange;
    public JFXTextField txtUserName;
    public JFXTextField txtUserPassword;

    public void salesAndReportsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);
    }

    public void setConstanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_SET_CONSTANTS, pane);
    }


    public void userDashboardOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "View as Accountant ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();
        }
    }


    public void initialize() throws SQLException, ClassNotFoundException {
        txtAdminName.setText(getName("admin"));
        txtUserName.setText(getName("accountant"));
        txtAdminPassword.setText(getPassword("admin"));
        txtUserPassword.setText(getPassword("accountant"));
    }

    public static String getName(String search) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select name from users where type = ?", search);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }

    public static String getPassword(String search) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select password from users where type =?", search);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "";
    }


    public void btnChangeAdminOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtAdminName.getText();
        String pw = txtAdminPassword.getText();

        boolean isUpdated = CrudUtil.execute("update users set name = ?, password = ? where type = 'admin';", name, pw);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "not Updated").show();
        }
    }

    public void btnUserChangeOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtUserName.getText();
        String pw = txtUserPassword.getText();

        boolean isUpdated = CrudUtil.execute("update users set name = ?, password = ? where type = 'accountant';", name, pw);
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Updated").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "not Updated").show();
        }
    }
}
