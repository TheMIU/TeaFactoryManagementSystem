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
import lk.ijse.tfms.bo.BOFactory;
import lk.ijse.tfms.bo.custom.PaymentBO;
import lk.ijse.tfms.bo.custom.impl.PaymentBOImpl;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.util.Navigation;
import lk.ijse.tfms.util.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class PaymentFormController {
    public JFXButton btnSearch;
    public JFXTextField txtReason;
    public ToggleGroup type;

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
    private JFXTextField txtPaymentID;

    @FXML
    private JFXTextField txtMethod;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TableView<PaymentDTO> tblPayment;
    public TableColumn colPaymentID;
    public TableColumn colDate;
    public TableColumn colAmount;
    public TableColumn colMethod;
    public TableColumn colType;
    public TableColumn colBuyerID;
    public TableColumn colEmpID;
    public TableColumn colSupID;
    public TableColumn colReason;

    @FXML
    private JFXRadioButton rbBuyer;

    @FXML
    private JFXRadioButton rbEmployee;

    @FXML
    private JFXRadioButton rbAll;

    @FXML
    private JFXRadioButton rbSupplier;

    @FXML
    private JFXRadioButton rbOther;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnSave1;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.Type.PAYMENT);

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

    public void initialize() {
        colPaymentID.setCellValueFactory(new PropertyValueFactory<>("payment_ID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colBuyerID.setCellValueFactory(new PropertyValueFactory<>("buyerID"));
        colSupID.setCellValueFactory(new PropertyValueFactory<>("supID"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));

        makeEditableTxtField(false);
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(true);
        btnSearch.setDisable(true);
        txtPaymentID.setEditable(false);

        tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setData(newValue);
                setName(newValue.getSupID(), newValue.getEmpID(), newValue.getBuyerID());
                btnDelete.setDisable(true);
                btnCancel.setDisable(true);
                btnSave.setDisable(true);
                btnEdit.setDisable(false);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            loadData(newValue);
            makeEditableTxtField(false);
        });
        loadData("");

        //---Radio buttons
        type.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) {
                RadioButton rb = (RadioButton) type.getSelectedToggle();
                if (rb != null) {
                    String rbValue = rb.getText();
                    if (rbValue.equals("All")) {
                        loadData("");
                        colSupID.setVisible(true);
                        colEmpID.setVisible(true);
                        colBuyerID.setVisible(true);
                    } else if (rbValue.equals("Supplier")) {
                        colSupID.setVisible(true);
                        colEmpID.setVisible(false);
                        colBuyerID.setVisible(false);
                        loadData(1); // supplier only
                    } else if (rbValue.equals("Buyer")) {
                        colBuyerID.setVisible(true);
                        colEmpID.setVisible(false);
                        colSupID.setVisible(false);
                        loadData(2); // buyer only
                    } else if (rbValue.equals("Employee")) {
                        colEmpID.setVisible(true);
                        colBuyerID.setVisible(false);
                        colSupID.setVisible(false);
                        loadData(3); // Emp only
                    }
                }
            }
        });
    }

    private void setName(String supID, String empID, String buyerID) {
       /* System.out.println(supID);
        System.out.println(empID);
        System.out.println(buyerID);*/
    }

    private void setData(PaymentDTO newValue) {
        txtPaymentID.setText(newValue.getPayment_ID());
        txtDate.setText(newValue.getDate());
        txtAmount.setText(String.valueOf(newValue.getAmount()));
        txtMethod.setText(newValue.getMethod());
        txtType.setText(newValue.getType());
        txtReason.setText(newValue.getReason());

        if (newValue.getBuyerID() != null) {
            txtID.setText(newValue.getBuyerID());
        } else if (newValue.getEmpID() != null) {
            txtID.setText(newValue.getEmpID());
        } else if (newValue.getSupID() != null) {
            txtID.setText(newValue.getSupID());
        }
    }

    private void loadData(String SearchID) {
        ObservableList<PaymentDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<PaymentDTO> paymentDTOData = paymentBO.getPaymentData();
            for (PaymentDTO p : paymentDTOData) {
                if (p.getPayment_ID().contains(SearchID) || p.getType().contains(SearchID)) {
                    PaymentDTO pay = new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyerID(), p.getEmpID(), p.getSupID());
                    list.add(pay);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        tblPayment.setItems(list);
    }

    private void loadData(int person) {
        ObservableList<PaymentDTO> list = FXCollections.observableArrayList();
        try {
            ArrayList<PaymentDTO> paymentDTOData = paymentBO.getPaymentData();
            if (person == 1) {
                paymentDTOData = paymentBO.getPaymentDataSup();
            } else if (person == 2) {
                paymentDTOData = paymentBO.getPaymentDataBuyers();
            } else if (person == 3) {
                paymentDTOData = paymentBO.getPaymentDataEmp();
            }
            for (PaymentDTO p : paymentDTOData) {
                PaymentDTO pay = new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyerID(), p.getEmpID(), p.getSupID());
                list.add(pay);
            }
        } catch (SQLException |
                ClassNotFoundException e) {
            System.out.println(e);
        }
        tblPayment.setItems(list);
    }


    //========================== Click On  Actions====================

    public void btnReportOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/tfms/view/reports/PaymentReport.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }

    }


    // EDIT Button---------------------
    public void btnEditOnAction(ActionEvent actionEvent) {
        if (!txtPaymentID.getText().equals("")) {
            btnDelete.setDisable(false);
            btnCancel.setDisable(false);
            btnSave.setDisable(false);
            btnSave.setText("Update");
            btnSearch.setDisable(false);

            makeEditableTxtField(true);

        } else {
            new Alert(Alert.AlertType.ERROR, "Stock Item Not selected !").show();
        }
    }

    // New Item Button---------------------
    public void btnNewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        makeEditableTxtField(true);
        clearFields();

        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnCancel.setDisable(false);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        btnSearch.setDisable(false);

        String nextID = generateNextPaymentID(paymentBO.getCurrentPaymentID());
        txtPaymentID.setText(nextID);
        txtDate.setText(LocalDate.now().toString());
        txtDate.requestFocus();
    }

    boolean nameFound = false;

    //Search & check ID---------------------
    public void btnSearchOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        try {
            String[] idArray = id.split("");

            if (idArray[0].equals("S")) {
                String name = paymentBO.getSupplierName(id);
                if (!name.equals("Not found")) {
                    txtName.setText(name);
                    nameFound = true;
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong ID !").show();
                    txtName.setText(null);
                    nameFound = false;
                }
            } else if (idArray[0].equals("E")) {
                String name = paymentBO.getEmployeeName(id);
                if (!name.equals("Not found")) {
                    txtName.setText(name);
                    nameFound = true;
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong ID !").show();
                    txtName.setText(null);
                    nameFound = false;
                }
            } else if (idArray[0].equals("B")) {
                String name = paymentBO.getBuyerName(id);
                if (!name.equals("Not found")) {
                    txtName.setText(name);
                    nameFound = true;
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong ID !").show();
                    txtName.setText(null);
                    nameFound = false;
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong ID !").show();
                txtName.setText(null);
                nameFound = false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    // Save / Update Button---------------------
    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!txtDate.getText().equals("") || txtAmount.getText().equals("") || txtMethod.getText().equals("") || txtType.getText().equals("") || txtID.getText().equals("")) {
            if (btnSave.getText().equals("Save")) {
                String paymentID = txtPaymentID.getText();
                String date = txtDate.getText();
                String reason = txtReason.getText();
                String method = txtMethod.getText();
                String type = txtType.getText();

                String id = txtID.getText();
                String[] idArray = id.split("");

                try {
                    double amount = Double.parseDouble(txtAmount.getText());
                    if (nameFound) {
                        PaymentDTO paymentDTO = null;
                        if (idArray[0].equals("S")) {
                            paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, null, null, id);
                        } else if (idArray[0].equals("E")) {
                            paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, null, id, null);
                        } else if (idArray[0].equals("B")) {
                            paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, id, null, null);
                        }

                        boolean isInserted = paymentBO.insertNewPayment(paymentDTO);
                        if (isInserted) {
                            new Alert(Alert.AlertType.CONFIRMATION, "Added !").show();
                            btnNewOnAction(actionEvent);
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Wrong ID !").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added !\n" + e).show();
                } catch (NumberFormatException e) {
                    new Alert(Alert.AlertType.ERROR, "Not Added ! \nwrong input(s)").show();
                }
            }
            if (btnSave.getText().equals("Update")) {
                String paymentID = txtPaymentID.getText();
                String date = txtDate.getText();
                String reason = txtReason.getText();
                String method = txtMethod.getText();
                String type = txtType.getText();

                String id = txtID.getText();
                String[] idArray = id.split("");
                try {
                    double amount = Double.parseDouble(txtAmount.getText());
                    PaymentDTO paymentDTO = null;
                    if (idArray[0].equals("S")) {
                        paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, null, null, id);
                    } else if (idArray[0].equals("E")) {
                        paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, null, id, null);
                    } else if (idArray[0].equals("B")) {
                        paymentDTO = new PaymentDTO(paymentID, date, reason, amount, method, type, id, null, null);
                    }
                    boolean isUpdated = paymentBO.updatePayment(paymentDTO);
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

        loadData("");
    }

    // Cancel Button---------------------
    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnDelete.setDisable(true);
        btnCancel.setDisable(true);
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        btnSearch.setDisable(true);
        clearFields();
        txtPaymentID.setText("");
        loadData("");
        makeEditableTxtField(false);
    }

    // Delete Button---------------------
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String paymentID = txtPaymentID.getText();

        Alert alert = new Alert(Alert.AlertType.WARNING, "Deleted Selected ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            Boolean isDeleted = paymentBO.deletePayment(paymentID);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                loadData("");
            } else new Alert(Alert.AlertType.WARNING, "Try Again!").show();
        }

        loadData("");
        clearFields();
        txtPaymentID.setText("");
    }

    //===================================================

    private void makeEditableTxtField(boolean b) {
        txtReason.setEditable(b);
        txtDate.setEditable(b);
        txtAmount.setEditable(b);
        txtMethod.setEditable(b);
        txtType.setEditable(b);
        txtID.setEditable(b);
        txtName.setEditable(b);
    }

    private void clearFields() {
        txtDate.clear();
        txtReason.clear();
        txtAmount.clear();
        txtMethod.clear();
        txtType.clear();
        txtID.clear();
        txtName.clear();
    }


    public static String generateNextPaymentID(String currentPaymentID) {
        if (currentPaymentID != null) {
            String[] ids = currentPaymentID.split("P0");
            int id = Integer.parseInt(ids[1]);
            id += 1;

            return "P0" + id;
        }
        return "P01";
    }


}
