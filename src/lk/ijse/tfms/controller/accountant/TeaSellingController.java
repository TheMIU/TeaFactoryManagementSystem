package lk.ijse.tfms.controller.accountant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import lk.ijse.tfms.bo.PaymentBOImpl;
import lk.ijse.tfms.bo.TeaSellingBOImpl;
import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TeaSellingController {

    public GridPane grid;
    @FXML
    private Label lbl1P;

    @FXML
    private Label lbl2OP;

    @FXML
    private Label lbl3FOP;

    @FXML
    private Label lbl4GFOP;

    @FXML
    private Label lbl5TGFOP;

    @FXML
    private Label lbl6FTGFOP;

    @FXML
    private Label lbl7BOP;

    @FXML
    private Label lbl8FBOP;

    @FXML
    private JFXTextField txtP;

    @FXML
    private JFXTextField txtOP;

    @FXML
    private JFXTextField txtFOP;

    @FXML
    private JFXTextField txtGFOP;

    @FXML
    private JFXTextField txtTGFOP;

    @FXML
    private JFXTextField txtFTGFOP;

    @FXML
    private JFXTextField txtBOP;

    @FXML
    private JFXTextField txtFBOP;

    @FXML
    private Label lbl1price;

    @FXML
    private Label lbl2OPprice;

    @FXML
    private Label lbl3FOPprice;

    @FXML
    private Label lbl4GFOPprice;

    @FXML
    private Label lbl5TGFOPprice;

    @FXML
    private Label lbl6FTGOPprice;

    @FXML
    private Label lbl7BOPprice;

    @FXML
    private Label lbl8FBOPprice;

    @FXML
    private Label r1;

    @FXML
    private Label r2;

    @FXML
    private Label r3;

    @FXML
    private Label r4;

    @FXML
    private Label r5;

    @FXML
    private Label r6;

    @FXML
    private Label r7;

    @FXML
    private Label r8;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblTotal1;

    @FXML
    private JFXTextField txtStockID;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXButton btnSearchStock;

    @FXML
    private JFXTextField txtBuyerID;

    @FXML
    private JFXTextField txtBuyerName;

    @FXML
    private JFXButton btnSearchBuyer;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnCancel;

    PaymentBOImpl paymentBO = new PaymentBOImpl();
    TeaSellingBOImpl teaSellingBO = new TeaSellingBOImpl();


    public void initialize() {
        grid.setDisable(true);
        txtStockID.requestFocus();
        txtDate.setText(LocalDate.now().toString());

        lbl1price.setText("250.00");
        lbl2OPprice.setText("300.00");
        lbl3FOPprice.setText("350.00");
        lbl4GFOPprice.setText("400.00");
        lbl5TGFOPprice.setText("450.00");
        lbl6FTGOPprice.setText("500.00");
        lbl7BOPprice.setText("550.00");
        lbl8FBOPprice.setText("600.00");

        // validate number inputs
        txtP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r1.setText(String.valueOf(Double.parseDouble(newValue) * 250));
                } else {
                    r1.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r2.setText(String.valueOf(Double.parseDouble(newValue) * 300));
                } else {
                    r2.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtFOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r3.setText(String.valueOf(Double.parseDouble(newValue) * 350));
                } else {
                    r3.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtGFOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r4.setText(String.valueOf(Double.parseDouble(newValue) * 400));
                } else {
                    r4.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtTGFOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r5.setText(String.valueOf(Double.parseDouble(newValue) * 450));
                } else {
                    r5.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtFTGFOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r6.setText(String.valueOf(Double.parseDouble(newValue) * 500));
                } else {
                    r6.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtBOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r7.setText(String.valueOf(Double.parseDouble(newValue) * 550));
                } else {
                    r7.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });

        txtFBOP.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals("")) {
                    r8.setText(String.valueOf(Double.parseDouble(newValue) * 600));
                } else {
                    r8.setText("0");
                }
                lblTotal.setText(String.valueOf(getTotal()));
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Error" + e).show();
            }
        });
    }

    private double getTotal() {
        double total = Double.parseDouble(r1.getText()) +
                Double.parseDouble(r2.getText()) +
                Double.parseDouble(r3.getText()) +
                Double.parseDouble(r4.getText()) +
                Double.parseDouble(r5.getText()) +
                Double.parseDouble(r6.getText()) +
                Double.parseDouble(r7.getText()) +
                Double.parseDouble(r8.getText()
                );
        return total;
    }

    //================ Search stock id & load labels ====================
    public boolean stockIdValid = false;
    public boolean buyerIdValid = false;

    @FXML
    void btnSearchStockOnAction(ActionEvent event) {
        String stockID = txtStockID.getText();
        stockIdValid = checkValidity(stockID);
        if (stockIdValid) {
            grid.setDisable(false);
        } else {
            grid.setDisable(true);
        }
        loadPriceTable(stockID);
        txtBuyerID.requestFocus();

        txtP.setDisable(lbl1P.getText().equals("Empty"));
        txtOP.setDisable(lbl2OP.getText().equals("Empty"));
        txtFOP.setDisable(lbl3FOP.getText().equals("Empty"));
        txtGFOP.setDisable(lbl4GFOP.getText().equals("Empty"));
        txtTGFOP.setDisable(lbl5TGFOP.getText().equals("Empty"));
        txtFTGFOP.setDisable(lbl6FTGFOP.getText().equals("Empty"));
        txtBOP.setDisable(lbl7BOP.getText().equals("Empty"));
        txtFBOP.setDisable(lbl8FBOP.getText().equals("Empty"));
    }

    private void loadPriceTable(String stockID) {
        lbl1P.setText(setQtyByType("P", stockID));
        lbl2OP.setText(setQtyByType("OP", stockID));
        lbl3FOP.setText(setQtyByType("FOP", stockID));
        lbl4GFOP.setText(setQtyByType("GFOP", stockID));
        lbl5TGFOP.setText(setQtyByType("TGFOP", stockID));
        lbl6FTGFOP.setText(setQtyByType("FTGFOP", stockID));
        lbl7BOP.setText(setQtyByType("BOP", stockID));
        lbl8FBOP.setText(setQtyByType("FBOP", stockID));
    }

    private String setQtyByType(String type, String stockID) {
        switch (type) {
            case "P":
                return setQty("P", stockID);
            case "OP":
                return setQty("OP", stockID);
            case "FOP":
                return setQty("FOP", stockID);
            case "GFOP":
                return setQty("GFOP", stockID);
            case "TGFOP":
                return setQty("TGFOP", stockID);
            case "FTGFOP":
                return setQty("FTGFOP", stockID);
            case "BOP":
                return setQty("BOP", stockID);
            case "FBOP":
                return setQty("FBOP", stockID);
        }
        return "Error";
    }

    private String setQty(String type, String stockID) {
        String stockQTY = "Empty";
        try {
            ResultSet resultSet = CrudUtil.execute("select AvailableQty from tea_stock where Type = ? && Stock_ID = ?", type, stockID);
            while (resultSet.next()) {
                stockQTY = resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error" + e).show();
        }
        return stockQTY;
    }

    //============================================

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtP.clear();
        txtOP.clear();
        txtFOP.clear();
        txtGFOP.clear();
        txtTGFOP.clear();
        txtFTGFOP.clear();
        txtBOP.clear();
        txtFBOP.clear();
    }

    @FXML
    void btnSearchBuyerOnAction(ActionEvent event) {
        String buyerID = txtBuyerID.getText();
        String buyerName = "null";

        try {
            ResultSet resultSet = CrudUtil.execute("select Name from buyers where Buyer_ID = ?", buyerID);
            while (resultSet.next()) {
                buyerName = resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error" + e).show();
        }

        if (!buyerName.equals("null")) {
            txtBuyerName.setText(buyerName);
            buyerIdValid = true;
        } else {
            new Alert(Alert.AlertType.ERROR, "Wrong buyer!").show();
        }

    }

    private boolean checkValidity(String stockID) {
        try {
            ResultSet resultSet = CrudUtil.execute("select Stock_ID from tea_stock where Stock_ID  = ? limit 1", stockID);
            if (!resultSet.next()) {
                new Alert(Alert.AlertType.ERROR, "Wrong Stock ID!").show();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Error 2" + e).show();
        }
        return true;
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String paymentID = PaymentFormController.generateNextPaymentID(paymentBO.getCurrentPaymentID());
        String date = txtDate.getText();
        double amount = getTotal();
        String buyerID = txtBuyerID.getText();
        String stockID = txtStockID.getText();

        int qtyP;
        qtyP = !txtP.getText().equals("") ? Integer.parseInt(txtP.getText()) : 0;
        int qtyOP;
        qtyOP = !txtOP.getText().equals("") ? Integer.parseInt(txtOP.getText()) : 0;
        int qtyBOP;
        qtyBOP = !txtBOP.getText().equals("") ? Integer.parseInt(txtBOP.getText()) : 0;
        int qtyFBOP;
        qtyFBOP = !txtFBOP.getText().equals("") ? Integer.parseInt(txtFBOP.getText()) : 0;
        int qtyGFOP;
        qtyGFOP = !txtGFOP.getText().equals("") ? Integer.parseInt(txtGFOP.getText()) : 0;
        int qtyTGFOP;
        qtyTGFOP = !txtTGFOP.getText().equals("") ? Integer.parseInt(txtTGFOP.getText()) : 0;
        int qtyFTGFOP;
        qtyFTGFOP = !txtFTGFOP.getText().equals("") ? Integer.parseInt(txtFTGFOP.getText()) : 0;
        int qtyFOP;
        qtyFOP = !txtFOP.getText().equals("") ? Integer.parseInt(txtFOP.getText()) : 0;

        // Transaction
        if (buyerIdValid && stockIdValid && amount > 0) {

            // tea sell transaction
            boolean b = teaSellingBO.transaction(new PaymentDTO(paymentID, date, "tea sell", amount, "cash", "credit", buyerID, null, null),
                    stockID, qtyP, qtyOP, qtyFOP, qtyGFOP, qtyTGFOP, qtyFTGFOP, qtyBOP, qtyFBOP);
            System.out.println(b);
            if (b) {
                btnClearOnAction(event);
                new Alert(Alert.AlertType.INFORMATION, "Order Successes!").show();
            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } else {
            if (!buyerIdValid) {
                new Alert(Alert.AlertType.WARNING, "Try Again! \nWrong Buyer ID ").show();
            } else if (!stockIdValid) {
                new Alert(Alert.AlertType.WARNING, "Try Again! \nWrong Stock ID ").show();
            } else if (!(amount > 0)) {
                new Alert(Alert.AlertType.WARNING, "Fill order data !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }
        }
        loadPriceTable(stockID);
    }

}
