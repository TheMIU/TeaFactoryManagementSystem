package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.tfms.bo.BuyerBOImpl;
import lk.ijse.tfms.dao.BuyerDAOImpl;
import lk.ijse.tfms.dto.BuyerDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class BuyersFormController {
    public AnchorPane pane;
    public JFXButton btnNew;
    public JFXButton btnEdit;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public JFXButton btnDelete;

    public JFXTextField txtSearch;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtBuyerID;
    public JFXTextField txtName;

    public TableView<BuyerDTO> tblBuyer;
    public TableColumn<BuyerDTO, String> colName;
    public TableColumn<BuyerDTO, String> colBuyerID;
    public TableColumn<BuyerDTO, String> colAddress;
    public TableColumn<BuyerDTO, String> colContact;

    BuyerBOImpl buyerBO = new BuyerBOImpl();

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

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.EMPLOYEES, pane);
    }

    public void otherSuppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OTHER_SUPPLIERS, pane);
    }

    //========================== Beginning ====================

    public void initialize() {
        colBuyerID.setCellValueFactory(new PropertyValueFactory<>("Buyer_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

        makeEditableTxtField(false);
        txtBuyerID.setEditable(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

        tblBuyer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadSupplierData(newValue);
            makeEditableTxtField(false);
        });

        loadSupplierData("");
    }

    private void setData(BuyerDTO newValue) {
        txtBuyerID.setText(newValue.getBuyer_ID());
        txtName.setText(newValue.getName());
        txtAddress.setText(newValue.getAddress());
        txtContact.setText(newValue.getContact());
    }

    public void loadSupplierData(String SearchID) {
        try {
            ObservableList<BuyerDTO> list = FXCollections.observableArrayList();
            ArrayList<BuyerDTO> buyerDTOData = buyerBO.getBuyerData();
            for (BuyerDTO b : buyerDTOData) {
                if (b.getBuyer_ID().contains(SearchID) || b.getName().contains(SearchID) || b.getAddress().contains(SearchID)) {
                    BuyerDTO buyerDTO = new BuyerDTO(b.getBuyer_ID(), b.getName(), b.getAddress(), b.getContact());
                    list.add(buyerDTO);
                }
            }
            tblBuyer.setItems(list);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    //========================== Click On  Actions====================

    //-----------------------EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtBuyerID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Buyer ID Not selected !").show();
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

        String nextID = generateNextSup_ID(buyerBO.getCurrentID());
        txtBuyerID.setText(nextID);
        txtName.requestFocus();
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtName.getText().equals("") || txtBuyerID.getText().equals("") || txtContact.getText().equals("")) {

            if (btnSave.getText().equals("Save")) {
                String buyerID = txtBuyerID.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String contact = txtContact.getText();

                try {
                    boolean isInserted = buyerBO.insertNewBuyer(new BuyerDTO(buyerID, name, address, contact));
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
                String buyerID = txtBuyerID.getText();
                String name = txtName.getText();
                String address = txtAddress.getText();
                String contact = txtContact.getText();

                try {
                    BuyerDTO buyerDTO = new BuyerDTO(buyerID, name, address, contact);
                    boolean isUpdated = buyerBO.updateBuyer(buyerDTO, buyerID);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                    }
                } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Updated !").show();
                }
                btnCancelOnAction(actionEvent);
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Fill data !").show();
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
        txtBuyerID.setText("");
        loadSupplierData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String BuyerID = txtBuyerID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = buyerBO.deleteBuyer(BuyerID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadSupplierData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadSupplierData("");
        clearFields();
        txtBuyerID.setText("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        txtName.setEditable(b);
        txtContact.setEditable(b);
        txtAddress.setEditable(b);
    }

    private void clearFields() {
        txtName.clear();
        txtBuyerID.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    private String generateNextSup_ID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("B0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "B0" + id;
        }
        return "B01";
    }
}
