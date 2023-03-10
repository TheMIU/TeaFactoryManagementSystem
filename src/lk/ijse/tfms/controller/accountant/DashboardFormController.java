package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.bo.BOFactory;
import lk.ijse.tfms.bo.custom.BuyerBO;
import lk.ijse.tfms.bo.custom.DashboardNotesBO;
import lk.ijse.tfms.bo.custom.impl.DashboardNotesBOImpl;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;


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

    DashboardNotesBO dashboardNotesBO = (DashboardNotesBO) BOFactory.getBoFactory().getBO(BOFactory.Type.DASHBOARD_NOTES);

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

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }

    //========================== Beginning ====================
    public void initialize() throws SQLException, ClassNotFoundException {
        lblCollectedTea.setText(dashboardNotesBO.getTotalKg()+" kg");
        lblBags.setText(dashboardNotesBO.getBagsCountKg());
        lblProduction.setText(dashboardNotesBO.getProductionKg()+" kg");

        task1.setText(getNote(1));
        task2.setText(getNote(2));
        task3.setText(getNote(3));
        task4.setText(getNote(4));

    }

    //========================== Notes ====================
    public String getNote(int id) throws SQLException, ClassNotFoundException {
        return dashboardNotesBO.getNote(id);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String task01 = task1.getText();
        String task02 = task2.getText();
        String task03 = task3.getText();
        String task04 = task4.getText();

        // save note
        boolean isSaved = dashboardNotesBO.saveNote(task01, task02, task03, task04);
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Saved!").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "not saved!").show();
        }
    }
}
