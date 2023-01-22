package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dao.DailyCropDAOImpl;
import lk.ijse.tfms.util.CrudUtil;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DashboardFormController {
    public JFXButton btnMakePayment;
    public Label lblProduction;
    public Label lblCollectedTea;
    public Label lblBags;
    public JFXTextArea task1;
    public JFXTextArea task2;
    public JFXTextArea task3;
    public JFXTextArea task4;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnEnterDailyCrop;

    @FXML
    private JFXButton btnteaCollecting;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnSalesAndReports;

    @FXML
    private JFXButton btnGenarateBills;

    @FXML
    private JFXButton btnManageFertilizer;

    @FXML
    private JFXButton btnManageAdvance;

    @FXML
    private JFXButton btnMakePayments;

    @FXML
    private JFXButton btnManageSuppliers;


    //====================Navigation==========================
    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
    }

    public void teaStocksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_STOCKS, pane);
    }

    public void teaSuplliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, pane);
    }

    public void dailyCropOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DAILY_CROP, pane);
    }


    //========================== Beginning ====================
    public void initialize() throws SQLException, ClassNotFoundException {
        lblCollectedTea.setText(DailyCropDAOImpl.getTotalKg()+" kg");
        lblBags.setText(DailyCropDAOImpl.getBagsCountKg());
        lblProduction.setText(DailyCropDAOImpl.getProductionKg()+" kg");

        task1.setText(getNote(1));
        task2.setText(getNote(2));
        task3.setText(getNote(3));
        task4.setText(getNote(4));

    }

    private String getNote(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs =  CrudUtil.execute("select note from notes where id = ?;",id);
        if (rs.next()) {
            return  rs.getString(1);
        }
        return "";
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String task01 = task1.getText();
        String task02 = task2.getText();
        String task03 = task3.getText();
        String task04 = task4.getText();

        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        CrudUtil.execute("update notes set note = ? where id = ?",task01,1);
        CrudUtil.execute("update notes set note = ? where id = ?",task02,2);
        CrudUtil.execute("update notes set note = ? where id = ?",task03,3);
        CrudUtil.execute("update notes set note = ? where id = ?",task04,4);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save notes ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            connection.commit();
            new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
        } else {
            connection.rollback();
            new Alert(Alert.AlertType.WARNING, "not saved!").show();
        }
        connection.setAutoCommit(true);

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }
}
