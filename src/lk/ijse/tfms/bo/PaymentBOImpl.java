package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.PaymentDAOImpl;
import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();

    public ArrayList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = paymentDAO.getPaymentData();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p : paymentData){
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(),p.getDate(),p.getReason(),p.getAmount(),p.getMethod(),p.getType(),p.getBuyer_ID(),p.getEmpID(),p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    public ArrayList<PaymentDTO> getPaymentDataSup() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataSup = paymentDAO.getPaymentDataSup();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p : paymentDataSup){
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(),p.getDate(),p.getReason(),p.getAmount(),p.getMethod(),p.getType(),p.getBuyer_ID(),p.getEmpID(),p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    public ArrayList<PaymentDTO> getPaymentDataBuyers() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataBuyers = paymentDAO.getPaymentDataBuyers();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p : paymentDataBuyers){
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(),p.getDate(),p.getReason(),p.getAmount(),p.getMethod(),p.getType(),p.getBuyer_ID(),p.getEmpID(),p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    public ArrayList<PaymentDTO> getPaymentDataEmp() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataEmp = paymentDAO.getPaymentDataEmp();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for(Payment p : paymentDataEmp){
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(),p.getDate(),p.getReason(),p.getAmount(),p.getMethod(),p.getType(),p.getBuyer_ID(),p.getEmpID(),p.getSupplier_ID()));
        }

        return paymentDTOS;
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

    public boolean insertNewPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return  paymentDAO.insertNewPayment(new Payment(dto.getPayment_ID(),dto.getDate(),dto.getReason(),dto.getAmount(),dto.getMethod(),dto.getType(),dto.getBuyerID(),dto.getEmpID(),dto.getSupID()));
    }

    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.updatePayment(new Payment(dto.getPayment_ID(),dto.getDate(),dto.getReason(),dto.getAmount(),dto.getMethod(),dto.getType(),dto.getBuyerID(),dto.getEmpID(),dto.getSupID()));
    }

    public Boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException {
        return paymentDAO.deletePayment(paymentID);
    }
}
