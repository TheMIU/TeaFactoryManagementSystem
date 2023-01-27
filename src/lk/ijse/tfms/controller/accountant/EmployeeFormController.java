package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.bo.custom.EmployeeBO;
import lk.ijse.tfms.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.tfms.dto.EmployeeDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class EmployeeFormController {

    public JFXTextField txtID;
    public JFXTextField txtAddress;
    public JFXTextField txtMobile;
    public JFXTextField txtEmpID;
    public JFXTextField txtName;
    public JFXComboBox cmbType;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public JFXButton btnEdit;
    public JFXButton btnNew;
    public JFXButton btnCancel;
    public JFXTextField txtSearch;
    public TableView<EmployeeDTO> tblEmployee;
    public TableColumn<EmployeeDTO, String> colEmpID;
    public TableColumn<EmployeeDTO, String> colType;
    public TableColumn<EmployeeDTO, String> colName;
    public TableColumn<EmployeeDTO, String> colID;
    public TableColumn<EmployeeDTO, String> colAddress;
    public TableColumn<EmployeeDTO, String> colMobileNo;


    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnEnterDailyCrop;

    @FXML
    private JFXButton btnSalesAndReports;

    @FXML
    private JFXButton btnGenarateBills;

    @FXML
    private JFXButton btnManageFertilizer;

    @FXML
    private JFXButton btnManageAdvance;

    @FXML
    private JFXButton btnMakePayment;

    @FXML
    private JFXButton btnteaCollecting;

    EmployeeBO employeeBO = new EmployeeBOImpl();

    //====================Navigation==========================
    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
    }

    public void teaStocksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_STOCKS, pane);
    }

    public void teaSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, pane);
    }

    public void dailyCropOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DAILY_CROP, pane);
    }

    public void btnBuyersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BUYERS, pane);
    }

    public void otherSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OTHER_SUPPLIERS, pane);
    }
    //========================== Beginning ====================

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        makeEditableTxtField(false);
        txtEmpID.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadEmployeeData(newValue);
            makeEditableTxtField(false);
        });

        loadEmployeeData("");
        //--------------------
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("Office", "Worker", "Driver");
        cmbType.setItems(list2);
    }

    private void setData(EmployeeDTO newValue) {
        cmbType.setPromptText(newValue.getType());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtMobile.setText(newValue.getContact());
        txtID.setText(newValue.getId());
        txtEmpID.setText(newValue.getEmpID());
    }



    private void loadEmployeeData(String SearchID) {
        ObservableList<EmployeeDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<EmployeeDTO> employeeDTOData = employeeBO.getEmployeeData();
            for (EmployeeDTO e : employeeDTOData) {
                if (e.getEmpID().contains(SearchID) || e.getName().contains(SearchID) || e.getAddress().contains(SearchID)) {
                    EmployeeDTO em = new EmployeeDTO(e.getEmpID(), e.getType(), e.getName(), e.getAddress() , e.getContact(),e.getId());
                    list.add(em);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblEmployee.setItems(list);
    }

    //========================== Click On  Actions====================
    //-----------------------EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtEmpID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Employee ID Not selected !").show();
        }
    }

    //-----------------------New Button---------------------
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");

        String nextID = generateNextSup_ID(employeeBO.getCurrentID());
        txtEmpID.setText(nextID);
        txtName.requestFocus();
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtName.getText().equals("") || txtID.getText().equals("") || txtMobile.getText().equals("")) {
            if (cmbType.getValue() != null) {
                if (btnSave.getText().equals("Save")) {
                    String empID = txtEmpID.getText();
                    String type = cmbType.getValue().toString();
                    String name = txtName.getText();
                    String id = txtID.getText();
                    String address = txtAddress.getText();
                    String mobileNo = txtMobile.getText();

                    try {
                        boolean isInserted = employeeBO.insertNewEmployee(new EmployeeDTO(empID, type, name, address, mobileNo, id));
                        if (isInserted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                            btnNewOnAction(actionEvent);
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        new Alert(Alert.AlertType.ERROR, "Not Added !").show();
                    } catch (NumberFormatException e) {
                        new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
                    }
                }

                if (btnSave.getText().equals("Update")) {
                    String empID = txtEmpID.getText();
                    String type = cmbType.getValue().toString();
                    String name = txtName.getText();
                    String id = txtID.getText();
                    String address = txtAddress.getText();
                    String mobileNo = txtMobile.getText();

                    try {
                        EmployeeDTO employeeDTO = new EmployeeDTO(empID, type, name, address, mobileNo, id);
                        boolean isUpdated = employeeBO.updateEmployee(employeeDTO);
                        if (isUpdated) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                        }
                    } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                        new Alert(Alert.AlertType.ERROR, e.toString()).show();
                    }
                }
                loadEmployeeData("");
            } else {
                new Alert(Alert.AlertType.WARNING, "Select Type !").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Fill data !").show();
        }
    }

    //-----------------------Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        clearFields();
        txtEmpID.setText("");
        loadEmployeeData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String EmpID = txtEmpID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = employeeBO.deleteEmployee(EmpID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadEmployeeData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadEmployeeData("");
        clearFields();
        txtEmpID.setText("");
    }

    //===================================================
    private void makeEditableTxtField(boolean b) {
        txtName.setEditable(b);
        txtAddress.setEditable(b);
        txtMobile.setEditable(b);
        txtID.setEditable(b);
    }

    private void clearFields() {
        txtName.clear();
        txtID.clear();
        txtMobile.clear();
        txtAddress.clear();
    }

    private String generateNextSup_ID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("E0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "E0" + id;
        }
        return "E01";
    }

    public void cmbTypeClickOnAction(ActionEvent actionEvent) {
    }


}
