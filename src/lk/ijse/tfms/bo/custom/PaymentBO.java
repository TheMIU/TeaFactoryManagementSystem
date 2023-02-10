package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException;

    public ArrayList<PaymentDTO> getPaymentDataSup() throws SQLException, ClassNotFoundException;

    public ArrayList<PaymentDTO> getPaymentDataBuyers() throws SQLException, ClassNotFoundException;

    public ArrayList<PaymentDTO> getPaymentDataEmp() throws SQLException, ClassNotFoundException;

    public String getCurrentPaymentID() throws SQLException, ClassNotFoundException;

    public String getSupplierName(String id) throws SQLException, ClassNotFoundException;

    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException;

    public String getBuyerName(String id) throws SQLException, ClassNotFoundException;

    public boolean insertNewPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException;

    public Boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException;

    double getSummery(String type) throws SQLException, ClassNotFoundException;
}
