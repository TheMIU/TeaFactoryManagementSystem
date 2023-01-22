package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.tfms.dao.TeaStockItemDAOImpl;
import lk.ijse.tfms.dto.TeaStockItemDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class TeaStockFormController {
    public TableView<TeaStockItemDTO> tblStock;
    public TableColumn colStockID;
    public TableColumn colType;
    public TableColumn colInDate;
    public TableColumn colOneBagWeight;
    public TableColumn colQty;
    public TableColumn colAvailableQty;
    public JFXButton btnSell;

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
    private JFXTextField txtStockID;
    @FXML
    private JFXTextField txtWeight;
    @FXML
    private JFXTextField txtType;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXTextField txtDate;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXRadioButton rbAll;
    @FXML
    private ToggleGroup type;
    @FXML
    private JFXRadioButton rbFertilizer;
    @FXML
    private JFXRadioButton rbBag;
    @FXML
    private JFXRadioButton rbFirewood;
    @FXML
    private JFXRadioButton rbBag1;
    @FXML
    private JFXRadioButton rbBag11;
    @FXML
    private JFXRadioButton rbBag111;
    @FXML
    private JFXRadioButton rbBag1111;
    @FXML
    private JFXRadioButton rbAll1;
    @FXML
    private JFXTextField txtSearch;

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

    public void OtherStocksOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.OTHER_STOCKS, pane);
    }

    public void suppliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }


    //========================== Beginning ====================
    public void initialize() {
        colStockID.setCellValueFactory(new PropertyValueFactory<>("stockID"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colInDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOneBagWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAvailableQty.setCellValueFactory(new PropertyValueFactory<>("AvailableQty"));

        txtStockID.setEditable(false);
        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);

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
        type.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) type.getSelectedToggle();
                if (rb != null) {
                    String rbValue = rb.getText();

                    if(rbValue.equals("All")){
                        loadStockData("");
                    }else if(rbValue.equals("P")){
                        loadStockData2("P");
                    }else if(rbValue.equals("OP")){
                        loadStockData2("OP");
                    }else if(rbValue.equals("FOP")){
                        loadStockData2("FOP");
                    }else if(rbValue.equals("GFOP")){
                        loadStockData2("GFOP");
                    }else if(rbValue.equals("TGFOP")){
                        loadStockData2("TGFOP");
                    }else if(rbValue.equals("FTGFOP")){
                        loadStockData2("FTGFOP");
                    }else if(rbValue.equals("BOP")){
                        loadStockData2("BOP");
                    }else if(rbValue.equals("FBOP")){
                        loadStockData2("FBOP");
                    }
                }
            }
        });
    }

    private void setData(TeaStockItemDTO newValue) {
        txtDate.setText(newValue.getDate());
        txtWeight.setText(String.valueOf(newValue.getWeight()));
        txtStockID.setText(newValue.getStockID());
        txtType.setText(newValue.getType());
        txtQty.setText(String.valueOf(newValue.getQty()));
    }

    public void loadStockData(String SearchID) {
        ObservableList<TeaStockItemDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<TeaStockItemDTO> teaStockItemDTOData = TeaStockItemDAOImpl.getStockItemsData();
            for (TeaStockItemDTO t : teaStockItemDTOData) {
                if (t.getType().contains(SearchID) || String.valueOf(t.getWeight()).contains(SearchID) || t.getDate().contains(SearchID)) {
                    TeaStockItemDTO tsi = new TeaStockItemDTO(t.getStockID(), t.getType(), t.getDate(), t.getWeight(), t.getQty(), t.getAvailableQty());
                    list.add(tsi);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblStock.setItems(list);
    }

    public void loadStockData2(String rbType) {
        ObservableList<TeaStockItemDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<TeaStockItemDTO> teaStockItemDTOData = TeaStockItemDAOImpl.getStockItemsData();
            for (TeaStockItemDTO t : teaStockItemDTOData) {
                if (t.getType().equals(rbType)) {
                    TeaStockItemDTO tsi = new TeaStockItemDTO(t.getStockID(), t.getType(), t.getDate(), t.getWeight(), t.getQty(), t.getAvailableQty());
                    list.add(tsi);
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

        String nextID = generateNextStockItemID(TeaStockItemDAOImpl.getCurrentStockID());
        txtStockID.setText(nextID);
        txtType.requestFocus();
    }

    //-----------------------Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtDate.getText().equals("") || txtQty.getText().equals("") || txtDate.getText().equals("")) {
            if (btnSave.getText().equals("Save")) {
                String stockID = txtStockID.getText();
                String type = txtType.getText();
                String date = txtDate.getText();

                try {
                    double weight = Double.parseDouble(txtWeight.getText());
                    int qty = Integer.parseInt(txtQty.getText());

                    TeaStockItemDTO teaStockItemDTO = new TeaStockItemDTO(stockID, type, date, weight, qty);

                    boolean isInserted = TeaStockItemDAOImpl.insertNewStockItem(teaStockItemDTO);
                    if (isInserted) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                        btnNewOnAction(actionEvent);
                    }

                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added !\n" + e).show();
                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
                }
            }

            if (btnSave.getText().equals("Update")) {
                String stockID = txtStockID.getText();
                String type = txtType.getText();
                String date = txtDate.getText();

                try {
                    double weight = Double.parseDouble(txtWeight.getText());
                    int qty = Integer.parseInt(txtQty.getText());

                    TeaStockItemDTO teaStockItemDTO = new TeaStockItemDTO(stockID, type, date, weight, qty);

                    boolean isUpdated = TeaStockItemDAOImpl.updateStockItem(teaStockItemDTO);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
                    }
                } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Updated !" + e).show();
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
        clearFields();
        txtStockID.setText("");
        loadStockData("");
        makeEditableTxtField(false);
    }

    //-----------------------Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String stockID = txtStockID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = TeaStockItemDAOImpl.deleteStockItem(stockID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadStockData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadStockData("");
        clearFields();
        txtStockID.setText("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        txtType.setEditable(b);
        txtDate.setEditable(b);
        txtQty.setEditable(b);
        txtWeight.setEditable(b);
    }

    private void clearFields() {
        txtStockID.clear();
        txtType.clear();
        txtQty.clear();
        txtWeight.clear();
        txtDate.clear();
    }

    private String generateNextStockItemID(String currentID) {
        if (currentID != null) {
            String[] ids = currentID.split("TI0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "TI0" + id;
        }
        return "TI01";
    }

    //=======================================================
    public void sellOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/accountant/TeaSelling.fxml"))));
        stage.show();
        stage.setResizable(false);
        stage.setTitle("Tea Selling");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    Navigation.navigate(Routes.TEA_STOCKS, pane);
                } catch (IOException e) {
                    new Alert(Alert.AlertType.INFORMATION, "Error UI!").show();
                }
            }
        });
    }
}
