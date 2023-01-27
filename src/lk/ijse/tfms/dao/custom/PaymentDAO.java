package lk.ijse.tfms.dao.custom;

import lk.ijse.tfms.dao.CrudDAO;
import lk.ijse.tfms.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentDAO extends CrudDAO<Payment> {
    public double getSummery(String type) throws SQLException, ClassNotFoundException;

    public ArrayList<Payment> getPaymentDataBuyers() throws SQLException, ClassNotFoundException;

    public ArrayList<Payment> getPaymentDataSup() throws SQLException, ClassNotFoundException;

    public ArrayList<Payment> getPaymentDataEmp() throws SQLException, ClassNotFoundException;

    public String getBuyerName(String id) throws SQLException, ClassNotFoundException;

    public String getSupplierName(String id) throws SQLException, ClassNotFoundException;

    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException;
}
