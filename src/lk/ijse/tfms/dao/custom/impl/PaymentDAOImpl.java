package lk.ijse.tfms.dao.custom.impl;

import lk.ijse.tfms.dao.custom.PaymentDAO;
import lk.ijse.tfms.entity.Payment;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getData() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            paymentData.add(new Payment(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentData;
    }

    @Override
    public ArrayList<Payment> getPaymentDataBuyers() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where Buyer_ID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentData.add(new Payment(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentData;
    }

    @Override
    public ArrayList<Payment> getPaymentDataSup() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where Supplier_ID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentData.add(new Payment(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentData;
    }

    @Override
    public ArrayList<Payment> getPaymentDataEmp() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where EmpID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentData.add(new Payment(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentData;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM payment ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public String getBuyerName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM buyers where Buyer_ID = ?", id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    @Override
    public String getSupplierName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM other_suppliers where Supplier_ID = ?", id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    @Override
    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM employee where EmpID = ?", id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    @Override
    public boolean add(Payment entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into payment (Payment_ID, Date, Reason, Amount, Method, Type, Buyer_ID, EmpID, Supplier_ID)\n" +
                        "values (?,?,?,?,?,?,?,?,?);",
                entity.getPayment_ID(),
                entity.getDate(),
                entity.getReason(),
                entity.getAmount(),
                entity.getMethod(),
                entity.getType(),
                entity.getBuyer_ID(),
                entity.getEmpID(),
                entity.getSupplier_ID()
        );
    }

    @Override
    public boolean update(Payment entity) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update payment\n" +
                        "set Date = ? , Reason = ? , Amount = ? , Method = ? , Type = ?, Buyer_ID = ?, EmpID = ?, Supplier_ID = ?  \n" +
                        "where Payment_ID = ?;",
                entity.getDate(),
                entity.getReason(),
                entity.getAmount(),
                entity.getMethod(),
                entity.getType(),
                entity.getBuyer_ID(),
                entity.getEmpID(),
                entity.getSupplier_ID(),
                entity.getPayment_ID()
        );
        return isUpdated;
    }

    @Override
    public Boolean delete(String paymentID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from payment where Payment_ID = ?", paymentID);
        return isDeleted;
    }

    @Override
    public double getSummery(String type) throws SQLException, ClassNotFoundException {
        double tot = 0;
        ResultSet rs = CrudUtil.execute("select sum(Amount) from payment where Type = ? ", type);
        while (rs.next()) {
            tot = Double.parseDouble(rs.getString("sum(Amount)"));
        }
        return tot;
    }

}
