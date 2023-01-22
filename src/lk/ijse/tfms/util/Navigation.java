package lk.ijse.tfms.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();

        switch (route) {
            case LOGIN:
                initUI("LoginForm.fxml");
                break;
            case ADMIN_DASHBOARD:
                initUI("admin/AdminDashBoardForm.fxml");
                break;
            case ADMIN_MANAGE_USERS:
                initUI("admin/ManageUsersForm.fxml");
                break;
            case ADMIN_SET_CONSTANTS:
                initUI("admin/setConstantsForm.fxml");
                break;
            case ACCOUNTANT_DASHBOARD:
                initUI("accountant/DashboardForm.fxml");
                break;
            case EMPLOYEES:
                initUI("accountant/EmployeeForm.fxml");
                break;
            case DAILY_CROP:
                initUI("accountant/EnterDailyCropForm.fxml");
                break;
            case OTHER_SUPPLIERS:
                initUI("accountant/OtherSuppliersForm.fxml");
                break;
            case PAYMENT:
                initUI("accountant/PaymentForm.fxml");
                break;
            case TEA_SUPPLIERS:
                initUI("accountant/TeaSuppliersForm.fxml");
                break;
            case BUYERS:
                initUI("accountant/ByersForm.fxml");
                break;
            case OTHER_STOCKS:
                initUI("accountant/OtherStocksForm.fxml");
                break;
            case TEA_STOCKS:
                initUI("accountant/TeaStockForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR, "UI Not Found!").show();
        }
    }
    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/lk/ijse/tfms/view/" + location)));
    }
}
