package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl {

    public static ArrayList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            paymentDTOData.add(new PaymentDTO(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentDTOData;
    }

    public static ArrayList<PaymentDTO> getPaymentDataBuyers() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where Buyer_ID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentDTOData.add(new PaymentDTO(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentDTOData;
    }

    public static ArrayList<PaymentDTO> getPaymentDataSup() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where Supplier_ID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentDTOData.add(new PaymentDTO(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentDTOData;
    }

    public static ArrayList<PaymentDTO> getPaymentDataEmp() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM payment where EmpID is not null ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) ");
        while (rs.next()) {
            paymentDTOData.add(new PaymentDTO(rs.getString("Payment_ID"),
                    rs.getString("Date"),
                    rs.getString("reason"),
                    rs.getDouble("Amount"),
                    rs.getString("Method"),
                    rs.getString("Type"),
                    rs.getString("Buyer_ID"),
                    rs.getString("EmpID"),
                    rs.getString("Supplier_ID")));
        }
        return paymentDTOData;
    }

    public static String getCurrentPaymentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM payment ORDER BY CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static String getSupplierName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM other_suppliers where Supplier_ID = ?",id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    public static String getBuyerName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM buyers where Buyer_ID = ?",id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    public static String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT name FROM employee where EmpID = ?",id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    public static boolean insertNewPayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into payment (Payment_ID, Date, Reason, Amount, Method, Type, Buyer_ID, EmpID, Supplier_ID)\n" +
                        "values (?,?,?,?,?,?,?,?,?);",
                paymentDTO.getPayment_ID(),
                paymentDTO.getDate(),
                paymentDTO.getReason(),
                paymentDTO.getAmount(),
                paymentDTO.getMethod(),
                paymentDTO.getType(),
                paymentDTO.getBuyerID(),
                paymentDTO.getEmpID(),
                paymentDTO.getSupID()
        );
    }

    public static boolean updatePayment(PaymentDTO paymentDTO) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update payment\n" +
                        "set Date = ? , Reason = ? , Amount = ? , Method = ? , Type = ?, Buyer_ID = ?, EmpID = ?, Supplier_ID = ?  \n" +
                        "where Payment_ID = ?;",
                paymentDTO.getDate(),
                paymentDTO.getReason(),
                paymentDTO.getAmount(),
                paymentDTO.getMethod(),
                paymentDTO.getType(),
                paymentDTO.getBuyerID(),
                paymentDTO.getEmpID(),
                paymentDTO.getSupID(),
                paymentDTO.getPayment_ID()
        );
        return isUpdated;
    }

    public static Boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from payment where Payment_ID = ?",paymentID);
        return isDeleted;
    }

    public static double getSummery(String type) throws SQLException, ClassNotFoundException {
        double tot = 0;
        ResultSet rs =  CrudUtil.execute("select sum(Amount) from payment where Type = ? ",type);
        while (rs.next()) {
            tot = Double.parseDouble(rs.getString("sum(Amount)")) ;
        }
       return tot;
    }

}
