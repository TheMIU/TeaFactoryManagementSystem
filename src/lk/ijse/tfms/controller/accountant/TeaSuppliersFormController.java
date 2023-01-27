package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.tfms.bo.custom.TeaSupplierBO;
import lk.ijse.tfms.bo.custom.impl.TeaSupplierBOImpl;
import lk.ijse.tfms.dto.TeaSupplierDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TeaSuppliersFormController {
    public JFXTextField txtSupID;
    public JFXTextField txtSupName;
    public JFXTextField txtID;
    public JFXTextField txtAddress;
    public JFXTextField txtMobile;
    public JFXTextField txtSearchByID;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TableView<TeaSupplierDTO> tblSupplier;
    public TableColumn<TeaSupplierDTO, String> SupID;
    public TableColumn<TeaSupplierDTO, String> Name;
    public TableColumn<TeaSupplierDTO, String> ID;
    public TableColumn<TeaSupplierDTO, String> Address;
    public TableColumn<TeaSupplierDTO, String> MobileNo;
    public JFXButton btnEdit;
    public JFXButton btnNew;
    public JFXButton btnCancel;

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

    TeaSupplierBO teaSupplierBO = new TeaSupplierBOImpl();

    //====================Navigation==========================
    public void homeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ACCOUNTANT_DASHBOARD, pane);
    }

    public void teaStocksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_STOCKS, pane);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.PAYMENT, pane);
    }

    public void dailyCropOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DAILY_CROP, pane);
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEES, pane);
    }

    public void btnBuyersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BUYERS, pane);
    }

    public void otherSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OTHER_SUPPLIERS, pane);
    }

    //========================== Beginning ====================

    public void initialize() {
        SupID.setCellValueFactory(new PropertyValueFactory<>("sup_id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        Address.setCellValueFactory(new PropertyValueFactory<>("address"));
        MobileNo.setCellValueFactory(new PropertyValueFactory<>("mobile_num"));

        makeEditableTxtField(false);
        txtSupID.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearchByID.textProperty().addListener((observable, oldValue, newValue) -> {
            loadSupplierData(newValue);
            makeEditableTxtField(false);
        });

        loadSupplierData("");
    }

    private void setData(TeaSupplierDTO newValue) {
        txtSupID.setText(newValue.getSup_id());
        txtSupName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtID.setText(newValue.getId());
        txtMobile.setText(newValue.getMobile_num());
    }

    private void loadSupplierData(String SearchID) {
        ObservableList<TeaSupplierDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<TeaSupplierDTO> teaSupplierDTOData = teaSupplierBO.getSupplierData();
            for (TeaSupplierDTO t : teaSupplierDTOData) {
                if (t.getSup_id().contains(SearchID) || t.getName().contains(SearchID) || t.getAddress().contains(SearchID)) {
                    TeaSupplierDTO ts = new TeaSupplierDTO(t.getSup_id(), t.getName(), t.getId(), t.getAddress(), t.getMobile_num());
                    list.add(ts);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblSupplier.setItems(list);
    }

    //========================== Click On  Actions====================
    //-----------------------EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtSupID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier ID Not selected !").show();
        }
    }

    //-----------------------New Supplier Button---------------------
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");

        String nextID = generateNextSup_ID(teaSupplierBO.getCurrentID());
        txtSupID.setText(nextID);
        txtSupName.requestFocus();
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save")) {
            String supID = txtSupID.getText();
            String name = txtSupName.getText();
            String id = txtID.getText();
            String address = txtAddress.getText();
            String mobileNo = txtMobile.getText();

            try {
                TeaSupplierDTO teaSupplierDTO = new TeaSupplierDTO(supID, name, id, address, mobileNo);
                boolean isInserted = teaSupplierBO.insertNewSupplier(teaSupplierDTO);
                if (isInserted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Not Added !").show();
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
            }
        }

        if (btnSave.getText().equals("Update")) {
            String supID = txtSupID.getText();
            String name = txtSupName.getText();
            String id = txtID.getText();
            String address = txtAddress.getText();
            String mobileNo = txtMobile.getText();

            try {
                TeaSupplierDTO teaSupplierDTO = new TeaSupplierDTO(supID, name, id, address, mobileNo);
                boolean isUpdated = teaSupplierBO.updateSupplier(teaSupplierDTO, supID);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                }
            } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Not Updated !").show();
            }
        }
        loadSupplierData("");
    }

    //-----------------------Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        clearFields();
        txtSupID.setText(null);
        loadSupplierData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supID = txtSupID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = teaSupplierBO.deleteSupplier(supID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadSupplierData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadSupplierData("");
        clearFields();
        txtSupID.setText(null);
    }

    //===================================================
    private void makeEditableTxtField(boolean b) {
        txtSupName.setEditable(b);
        txtAddress.setEditable(b);
        txtMobile.setEditable(b);
        txtID.setEditable(b);
    }

    private void clearFields() {
        txtSupName.clear();
        txtID.clear();
        txtMobile.clear();
        txtAddress.clear();
    }

    private String generateNextSup_ID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("TS0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "TS0" + id;
        }
        return "TS01";
    }
}
