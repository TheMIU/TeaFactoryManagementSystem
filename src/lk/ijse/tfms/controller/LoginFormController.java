package lk.ijse.tfms.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.tfms.controller.admin.ManageUsersFormController;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public JFXPasswordField txtPassword;
    public JFXTextField txtUserName;
    public JFXButton btnLogin;
    public AnchorPane pane;

    String adminName = ManageUsersFormController.getName("admin");
    String adminPw = ManageUsersFormController.getPassword("admin");
    String accountantName = ManageUsersFormController.getName("accountant");
    String accountantPw = ManageUsersFormController.getPassword("accountant");

    public LoginFormController() throws SQLException, ClassNotFoundException {
    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String password = txtPassword.getText();
        String userName = txtUserName.getText();
        Shake shakeUserName = new Shake(txtUserName);
        Shake shakePassword = new Shake(txtPassword);


  /*      System.out.println(accountantName);
        System.out.println(accountantPw);
        System.out.println(adminName);
        System.out.println(adminPw);
*/
        if(password.equals(adminPw) && userName.equals(adminName)){
            txtUserName.setFocusColor(Paint.valueOf("GREEN"));
            Navigation.navigate(Routes.ADMIN_DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();

        }else if(password.equals(accountantPw) && userName.equals(accountantName)){
            txtPassword.setFocusColor(Paint.valueOf("GREEN"));
            Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();
        }

        txtUserName.requestFocus();
        txtUserName.setFocusColor(Paint.valueOf("RED"));
        shakeUserName.setSpeed(3).play();
        txtPassword.clear();

        if(userName.equals(accountantName)||userName.equals(adminName)){
            txtUserName.setFocusColor(Paint.valueOf("GREEN"));

            if(!password.equals(accountantPw) || password.equals(adminPw)){
                txtPassword.requestFocus();
                txtPassword.setFocusColor(Paint.valueOf("RED"));
                shakeUserName.stop();
                shakePassword.setSpeed(3).play();
            }
        }

        if(password.equals(password.equals(accountantPw) || password.equals(adminPw))){
            txtPassword.setFocusColor(Paint.valueOf("GREEN"));
        }
    }

    public void forgotPasswordClickOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Please contact Developer !\n0773572070").show();
    }
}
