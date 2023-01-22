package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.tfms.dto.DailyCropDTO;
import lk.ijse.tfms.dao.DailyCropDAOImpl;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class EnterDailyCropFormController {
    public AnchorPane pane;
    public JFXTextField txtSupID;
    public JFXTextField txtSupName;
    public JFXTextField txtWeight;
    public JFXTextField txtDate;
    public JFXButton btnSearchID;
    public JFXButton btnSave;
    public JFXButton btnCancel;
    public TableView<DailyCropDTO> tblDailyCrop;
    public TableColumn colDate;
    public TableColumn colSupID;
    public TableColumn colNetWeight;
    public JFXTextField txtWeight2;
    public JFXTextField txtSupID2;
    public JFXTextField txtName2;
    public JFXTextField txtDate2;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnCancel2;
    public JFXButton btnEdit;
    public JFXTextField txtSearch;
    public Label dateLbl;
    public Label lblKg;
    @FXML
    private JFXButton btnHome;
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

    public void teaSuplliersOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.TEA_SUPPLIERS, pane);
    }


    //========================== Beginning ====================
    public void initialize() throws SQLException, ClassNotFoundException {
        dateLbl.setText(LocalDate.now()+" Collected");
        lblKg.setText(DailyCropDAOImpl.getTotalKg(LocalDate.now())+" kg");
        txtDate.setText(LocalDate.now().toString());
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colNetWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel2.setDisable(true);
        btnUpdate.setDisable(true);
        btnEdit.setDisable(true);
        txtName2.setDisable(true);

        tblDailyCrop.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                btnDelete.setDisable(true);
                btnCancel2.setDisable(true);
                btnUpdate.setDisable(true);
                btnEdit.setDisable(false);
                txtName2.setText(DailyCropDAOImpl.getSupplierName(newValue.getSupID()));
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadData(newValue);
            makeEditableTxtField(false);
        });

        loadData("");
    }

    private void setData(DailyCropDTO newValue) {
        txtDate2.setText(newValue.getDate());
        txtWeight2.setText(String.valueOf(newValue.getWeight()));
        txtSupID2.setText(newValue.getSupID());
    }

    private void loadData(String SearchID) {
        ObservableList<DailyCropDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<DailyCropDTO> cropData = DailyCropDAOImpl.getData();
            for (DailyCropDTO d : cropData) {
                if (d.getDate().contains(SearchID) || d.getSupID().contains(SearchID)) {
                    DailyCropDTO dc = new DailyCropDTO( d.getDate(),d.getSupID(), d.getWeight());
                    list.add(dc);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblDailyCrop.setItems(list);
        try {
            lblKg.setText(DailyCropDAOImpl.getTotalKg(LocalDate.now())+" kg");
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.INFORMATION, "Enter Today daily crop!").show();
        }
    }

    //========================== Click On  Actions====================
    //-----------------------Search Button---------------------
    public void btnSearchIDOnAction(ActionEvent actionEvent) {
        String supID = txtSupID.getText();
        String name = DailyCropDAOImpl.getSupplierName(supID);
        if(!name.equals("no")){
            txtSupName.setText(name);
        }else {
            txtSupName.clear();
            new Alert(Alert.AlertType.ERROR, " Wrong ID !").show();
        }
    }

    //-----------------------Save Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save")) {
            String date = txtDate.getText();
            String supID = txtSupID.getText();
            try {
                double weight = Double.parseDouble(txtWeight.getText());

                DailyCropDTO dailyCropDTO = new DailyCropDTO(supID,date,weight);
                boolean isInserted = DailyCropDAOImpl.saveDailyCrop(dailyCropDTO);
                if (isInserted) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Not Added !\n"+e).show();
            } catch (NumberFormatException e) {
                new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
            }

            loadData("");
        }
    }

    //-----------------------Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtSupID.clear();
        txtWeight.clear();
        txtSupName.clear();
    }

    public void btnCancel2OnAction(ActionEvent actionEvent) {
        clearFields();
        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel2.setDisable(true);
        btnUpdate.setDisable(true);
        btnEdit.setDisable(true);
    }

    //-----------------------delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String supID = txtSupID2.getText();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = DailyCropDAOImpl.deleteSelected(supID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadData("");
        clearFields();
    }

    //-----------------------Edit Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtSupID2.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel2.setDisable(false);
            btnUpdate.setDisable(false);

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Not selected !").show();
        }
    }

    //-----------------------update Button---------------------
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String sid = txtSupID2.getText();
        String date = txtDate2.getText();

        try {
            double weight = Double.parseDouble(txtWeight2.getText());

            DailyCropDTO dc = new DailyCropDTO(sid,date,weight);

            boolean isUpdated = DailyCropDAOImpl.update(dc);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Not Updated !\n wrong input(s)").show();
            }
        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Not Updated !"+e).show();
        }
        btnCancelOnAction(actionEvent);
        loadData("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        txtSupID2.setEditable(b);
        txtWeight2.setEditable(b);
        txtDate2.setEditable(b);
    }

    private void clearFields() {
        txtName2.clear();
        txtSupID2.clear();
        txtWeight2.clear();
        txtDate2.clear();
    }

}
