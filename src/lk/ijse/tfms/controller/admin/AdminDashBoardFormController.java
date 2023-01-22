package lk.ijse.tfms.controller.admin;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dao.PaymentDAOImpl;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDashBoardFormController {
    public AnchorPane pane;
    public Label lblSummery;
    public Label lblDif;
    public Label lblExpense;
    public Label lblIncome;


    public void manageUsersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_MANAGE_USERS, pane);
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

    //========================== Beginning ====================
    public void initialize() throws SQLException, ClassNotFoundException {
        double income = PaymentDAOImpl.getSummery("credit");
        double expence = PaymentDAOImpl.getSummery("debit");

        lblExpense.setText(String.valueOf(expence));
        lblIncome.setText(String.valueOf(income));

        double prof = income - expence;
        if (prof > 0) {
            lblSummery.setText("Profit");
        } else if (prof == 0) {
            lblSummery.setText("0");
        } else {
            lblSummery.setText("loss");
        }

        lblDif.setText(String.valueOf(prof));

    }

    public void btnPaymentReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/PaymentReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnTeaStockReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/TeaStockReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnOtherStockReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/OtherStockReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnSupplierReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/SupplierReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnBuyerReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/BuyersReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void btnEmployeeReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/EmployeesReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }
}
