package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.PaymentDAOImpl;
import lk.ijse.tfms.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    public ArrayList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException {
        return  paymentDAO.getPaymentData();
    }

    public ArrayList<PaymentDTO> getPaymentDataSup() throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentDataSup();
    }

    public ArrayList<PaymentDTO> getPaymentDataBuyers() throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentDataBuyers();
    }

    public ArrayList<PaymentDTO> getPaymentDataEmp() throws SQLException, ClassNotFoundException {
        return paymentDAO.getPaymentDataEmp();
    }

    public String getCurrentPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentPaymentID();
    }

    public String getSupplierName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getSupplierName(id);
    }

    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getEmployeeName(id);
    }

    public String getBuyerName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getBuyerName(id);
    }

    public boolean insertNewPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return  paymentDAO.insertNewPayment(paymentDTO);
    }

    public boolean updatePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return paymentDAO.updatePayment(paymentDTO);
    }

    public Boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException {
        return paymentDAO.deletePayment(paymentID);
    }
}
