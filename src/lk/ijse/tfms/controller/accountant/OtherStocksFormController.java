package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.bo.custom.OtherStocksBO;
import lk.ijse.tfms.bo.custom.impl.OtherStocksBOImpl;
import lk.ijse.tfms.dto.OtherStockItemDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class OtherStocksFormController {

    public JFXTextField txtSearch;
    public JFXButton btnSearchID;
    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton btnteaCollecting;

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
    private JFXButton btnManageSuppliers;

    @FXML
    private JFXButton btnEnterDailyCrop;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtSupID;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    private JFXTextField txtSupType;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<OtherStockItemDTO> tblStock;
    public TableColumn colDate;
    public TableColumn colStockID;
    public TableColumn colSupID;
    public TableColumn colSupName;
    public TableColumn colType;
    public TableColumn colQty;
    public TableColumn colPrice;

    @FXML
    private JFXRadioButton rbAll;

    @FXML
    private ToggleGroup stockSelect;

    @FXML
    private JFXRadioButton rbFertilizer;

    @FXML
    private JFXRadioButton rbBag;

    @FXML
    private JFXRadioButton rbFirewood;

    OtherStocksBO otherStocksBO = new OtherStocksBOImpl();

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

    public void suppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }


    //========================== Beginning ====================

    public void initialize() {
        //associate table
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        // other stocks table
        colStockID.setCellValueFactory(new PropertyValueFactory<>("stockID"));
        //other_suppliers table
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        btnSearchID.setDisable(true);

        txtSupName.setEditable(false);
        txtSupType.setEditable(false);
        txtStockID.setEditable(false);
        txtSupID.setEditable(false);

        tblStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadStockData(newValue);
            makeEditableTxtField(false);
        });

        loadStockData("");

        //---Radio buttons
        stockSelect.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) stockSelect.getSelectedToggle();
                if (rb != null) {
                    String rbValue = rb.getText();
                    if(rbValue.equals("All Stocks")){
                        loadStockData("");
                    }else if(rbValue.equals("Fertilizer Stock")){
                        loadStockData2("fertilizer");
                    }else if(rbValue.equals("Firewood Stock")){
                        loadStockData2("firewood");
                    }else if(rbValue.equals("Bags Stock")){
                        loadStockData2("bags");
                    }
                }
            }
        });
    }

    private void setData(OtherStockItemDTO newValue) {
        txtDate.setText(newValue.getDate());
        txtSupID.setText(newValue.getSupplierID());
        txtSupName.setText(newValue.getSupplierName());
        txtStockID.setText(newValue.getStockID());
        txtSupType.setText(newValue.getType());
        txtQty.setText(String.valueOf(newValue.getQty()));
        txtPrice.setText(String.valueOf(newValue.getPrice()));
    }

    private void loadStockData(String SearchID) {
        ObservableList<OtherStockItemDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<OtherStockItemDTO> otherStockItemDTOData = otherStocksBO.getStockItemsData();
            for (OtherStockItemDTO s : otherStockItemDTOData) {
                if (s.getType().contains(SearchID) || s.getSupplierName().contains(SearchID) || s.getDate().toString().contains(SearchID)) {
                    OtherStockItemDTO oti = new OtherStockItemDTO(s.getDate(), s.getStockID(), s.getSupplierID(), s.getSupplierName(), s.getType(), s.getQty(), s.getPrice());
                    list.add(oti);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblStock.setItems(list);
    }

    private void loadStockData2(String type) {
        ObservableList<OtherStockItemDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<OtherStockItemDTO> otherStockItemDTOData = otherStocksBO.getStockItemsData();
            for (OtherStockItemDTO s : otherStockItemDTOData) {
                if (s.getType().equals(type)) {
                    OtherStockItemDTO oti = new OtherStockItemDTO(s.getDate(), s.getStockID(), s.getSupplierID(), s.getSupplierName(), s.getType(), s.getQty(), s.getPrice());
                    list.add(oti);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblStock.setItems(list);
    }

    //========================== Click On  Actions====================

    //-----------------------EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtStockID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
            btnSearchID.setDisable(false);

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Stock Item Not selected !").show();
        }
    }

    //-----------------------New Item Button---------------------
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        btnSearchID.setDisable(false);

        String nextID = generateNextStockItemID(otherStocksBO.getCurrentStockID());
        txtStockID.setText(nextID);
        txtSupName.requestFocus();
        txtDate.setText(LocalDate.now().toString());
    }

    boolean supplierFound = false;

    //-----------------------Search & check Supplier ID---------------------
    public void btnSearchIDOnAction(ActionEvent actionEvent) {
        String Sup_id = txtSupID.getText();
        try {
            String supName = otherStocksBO.getSupplierName(Sup_id);
            String supType = otherStocksBO.getSupplierType(Sup_id);
            if (!(supName.equals("Not found") || supType.equals("Not found"))) {
                txtSupName.setText(supName);
                txtSupType.setText(supType);
                supplierFound = true;
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong Supplier !").show();
                supplierFound = false;
            }
            /*System.out.println(supplierFound);
            System.out.println(supName+"   "+ supType);*/
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtDate.getText().equals("") || txtQty.getText().equals("") || txtPrice.getText().equals("")) {
            if (btnSave.getText().equals("Save")) {
                String date = txtDate.getText();
                String stockID = txtStockID.getText();
                String supID = txtSupID.getText();
                String sup_Name = txtSupName.getText();
                String type = txtSupType.getText();

                try {
                    int qty = Integer.parseInt(txtQty.getText());
                    double price = Double.parseDouble(txtPrice.getText());
                    if (supplierFound) {
                        OtherStockItemDTO otherStockItemDTO = new OtherStockItemDTO(date, stockID, supID, sup_Name, type, qty, price);
                        boolean isInserted = otherStocksBO.insertNewStockItemTransaction(otherStockItemDTO);
                        if (isInserted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                            btnNewOnAction(actionEvent);
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Wrong Supplier !").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added !\n" + e).show();
                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
                }
            }

            if (btnSave.getText().equals("Update")) {
                String date = txtDate.getText();
                String stockID = txtStockID.getText();
                String supID = txtSupID.getText();
                String sup_Name = txtSupName.getText();
                String type = txtSupType.getText();

                try {
                    int qty = Integer.parseInt(txtQty.getText());
                    double price = Double.parseDouble(txtPrice.getText());

                    OtherStockItemDTO otherStockItemDTO = new OtherStockItemDTO(date, stockID, supID, sup_Name, type, qty, price);

                    boolean isUpdated = otherStocksBO.updateStockItem(otherStockItemDTO);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                    }
                } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Updated !"+e).show();
                }
                btnCancelOnAction(actionEvent);
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Error data !").show();
        }

        loadStockData("");
    }

    //-----------------------Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        btnSearchID.setDisable(true);
        clearFields();
        txtSupID.setText("");
        loadStockData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String stockID = txtStockID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = otherStocksBO.deleteStockTransactions(stockID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadStockData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadStockData("");
        clearFields();
        txtSupID.setText("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        //txtSupID.setEditable(b);
        txtPrice.setEditable(b);
        txtDate.setEditable(b);
        txtQty.setEditable(b);
    }

    private void clearFields() {
        txtSupID.clear();
        txtSupType.clear();
        txtSupName.clear();
        txtQty.clear();
        txtPrice.clear();
        txtStockID.clear();
        txtDate.clear();
    }

    private String generateNextStockItemID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("I0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "I0" + id;
        }
        return "I01";
    }



}
