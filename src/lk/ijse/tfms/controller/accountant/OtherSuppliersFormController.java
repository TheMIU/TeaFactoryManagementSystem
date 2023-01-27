package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.bo.BOFactory;
import lk.ijse.tfms.bo.custom.DashboardNotesBO;
import lk.ijse.tfms.bo.custom.OtherSupplierBO;
import lk.ijse.tfms.bo.custom.impl.OtherSupplierBOImpl;
import lk.ijse.tfms.dto.OtherSupplierDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class OtherSuppliersFormController {

    public AnchorPane pane;
    public JFXButton btnNew;
    public JFXTextField txtSearchByID;
    public JFXButton btnEdit;
    public JFXTextField txtID;
    public JFXTextField txtMobile;
    public JFXTextField txtSupID;
    public JFXTextField txtSupName;
    public JFXComboBox<String> cmbType;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXButton btnDelete;
    public TableView<OtherSupplierDTO> tblSupplier;
    public TableColumn colSupID;
    public TableColumn colType;
    public TableColumn colName;
    public TableColumn colID;
    public TableColumn colMobileNo;

    OtherSupplierBO otherSupplierBO = (OtherSupplierBO) BOFactory.getBoFactory().getBO(BOFactory.Type.OTHER_SUPPLIERS);

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

    public void teaSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEES, pane);
    }

    public void btnBuyersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.BUYERS, pane);
    }


    //========================== Beginning ====================

    public void initialize() {
        colSupID.setCellValueFactory(new PropertyValueFactory<>("SupID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("Sup_Type"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colMobileNo.setCellValueFactory(new PropertyValueFactory<>("contact"));

        makeEditableTxtField(false);
        txtSupID.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        cmbType.setDisable(true);
        cmbType.setPromptText("Select type");

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

    private void setData(OtherSupplierDTO newValue) {
        txtSupID.setText(newValue.getSupID());
        cmbType.setPromptText(newValue.getSup_Type());
        txtSupName.setText(newValue.getName());
        txtID.setText(newValue.getID());
        txtMobile.setText(newValue.getContact());
    }

    private void loadSupplierData(String SearchID) {
        ObservableList<OtherSupplierDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<OtherSupplierDTO> otherSupplierDTOData = otherSupplierBO.getSupplierData();
            for (OtherSupplierDTO o : otherSupplierDTOData) {
                if (o.getSupID().contains(SearchID) || o.getName().contains(SearchID) || o.getSup_Type().contains(SearchID)) {
                    OtherSupplierDTO ts = new OtherSupplierDTO(o.getSupID(), o.getSup_Type(), o.getName(), o.getID(), o.getContact());
                    list.add(ts);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblSupplier.setItems(list);

        //--------------------
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("Firewood", "Bags", "Fertilizer");
        cmbType.setItems(list2);
    }

    //========================== Click On  Actions====================

    //-----------------------combo box supplier type---------------------
    public void cmbTypeClickOnAction(ActionEvent actionEvent) {
    }

    //-----------------------EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtSupID.getText().equals("")) {
            cmbType.setDisable(false);
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

        cmbType.setDisable(false);
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");

        String nextID = generateNextSup_ID(otherSupplierBO.getCurrentID());
        txtSupID.setText(nextID);
        txtSupName.requestFocus();
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (! txtSupName.getText().equals("") || txtID.getText().equals("") || txtMobile.getText().equals("")){
            if(cmbType.getValue()!= null){
                if (btnSave.getText().equals("Save")) {
                    String supID = txtSupID.getText();
                    String type = cmbType.getValue().toString();
                    String name = txtSupName.getText();
                    String id = txtID.getText();
                    String mobileNo = txtMobile.getText();

                    try {
                        OtherSupplierDTO otherSupplierDTO = new OtherSupplierDTO(supID, type, id, name, mobileNo);

                        boolean isInserted = otherSupplierBO.insertNewSupplier(otherSupplierDTO);
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
                    String supID = txtSupID.getText();
                    String name = txtSupName.getText();
                    String id = txtID.getText();
                    String type = cmbType.getValue().toString();
                    String mobileNo = txtMobile.getText();

                    try {
                        OtherSupplierDTO otherSupplierDTO = new OtherSupplierDTO(supID, type, id, name, mobileNo);

                        boolean isUpdated = otherSupplierBO.updateSupplier(otherSupplierDTO);

                        if (isUpdated) {
                            cmbType.setDisable(true);
                            new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                        }
                    } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                        new Alert(Alert.AlertType.ERROR, "Not Updated !").show();
                    }
                    btnCancelOnAction(actionEvent);
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Select Type !").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Fill data !").show();
        }

        //------add combobox new item to list -----
       /* String value = cmbType.getValue();
        cmbType.getItems().add(value);*/

        loadSupplierData("");
    }

    //-----------------------Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        cmbType.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        clearFields();
        txtSupID.setText("");
        loadSupplierData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supID = txtSupID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = otherSupplierBO.deleteSupplier(supID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadSupplierData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadSupplierData("");
        clearFields();
        txtSupID.setText("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        txtSupName.setEditable(b);
        cmbType.setEditable(b);
        txtMobile.setEditable(b);
        txtID.setEditable(b);
    }

    private void clearFields() {
        txtSupName.clear();
        txtID.clear();
        txtMobile.clear();
        txtSupID.clear();
    }

    private String generateNextSup_ID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("S0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "S0" + id;
        }
        return "S01";
    }


}
