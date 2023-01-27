package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.TeaSellingBO;
import lk.ijse.tfms.dao.PaymentDAOImpl;
import lk.ijse.tfms.dao.TeaStockItemDAOImpl;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;

public class TeaSellingBOImpl implements TeaSellingBO {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    TeaStockItemDAOImpl teaStockItemDAO = new TeaStockItemDAOImpl();

    @Override
    public boolean transaction(PaymentDTO dto, String stockID, int qtyP, int qtyOP, int qtyFOP, int qtyGFOP, int qtyTGFOP, int qtyFTGFOP, int qtyBOP, int qtyFBOP) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        // --- generate payment
        boolean b1 = paymentDAO.insertNewPayment(new Payment(dto.getPayment_ID(), dto.getDate(), dto.getReason(), dto.getAmount(), dto.getMethod(), dto.getType(), dto.getBuyerID(), dto.getEmpID(), dto.getSupID()));
        System.out.println(b1 + "b1"); // true

        // --- Update stock
        boolean b2 = teaStockItemDAO.updateAvailableQTY(stockID, qtyP, qtyOP, qtyFOP, qtyGFOP, qtyTGFOP, qtyFTGFOP, qtyBOP, qtyFBOP);
        System.out.println(b2 + "b2");

      /*  Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save results ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();*/
        if (b1 && b2) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

}
