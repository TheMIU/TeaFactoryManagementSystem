package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.PaymentBO;
import lk.ijse.tfms.dao.custom.PaymentDAO;
import lk.ijse.tfms.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.tfms.dto.PaymentDTO;
import lk.ijse.tfms.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public ArrayList<PaymentDTO> getPaymentData() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentData = paymentDAO.getData();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : paymentData) {
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyer_ID(), p.getEmpID(), p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    @Override
    public ArrayList<PaymentDTO> getPaymentDataSup() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataSup = paymentDAO.getPaymentDataSup();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : paymentDataSup) {
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyer_ID(), p.getEmpID(), p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    @Override
    public ArrayList<PaymentDTO> getPaymentDataBuyers() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataBuyers = paymentDAO.getPaymentDataBuyers();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : paymentDataBuyers) {
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyer_ID(), p.getEmpID(), p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    @Override
    public ArrayList<PaymentDTO> getPaymentDataEmp() throws SQLException, ClassNotFoundException {
        ArrayList<Payment> paymentDataEmp = paymentDAO.getPaymentDataEmp();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : paymentDataEmp) {
            paymentDTOS.add(new PaymentDTO(p.getPayment_ID(), p.getDate(), p.getReason(), p.getAmount(), p.getMethod(), p.getType(), p.getBuyer_ID(), p.getEmpID(), p.getSupplier_ID()));
        }

        return paymentDTOS;
    }

    @Override
    public String getCurrentPaymentID() throws SQLException, ClassNotFoundException {
        return paymentDAO.getCurrentID();
    }

    public String getSupplierName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getSupplierName(id);
    }

    @Override
    public String getEmployeeName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getEmployeeName(id);
    }

    public String getBuyerName(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.getBuyerName(id);
    }

    @Override
    public boolean insertNewPayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.add(new Payment(dto.getPayment_ID(), dto.getDate(), dto.getReason(), dto.getAmount(), dto.getMethod(), dto.getType(), dto.getBuyerID(), dto.getEmpID(), dto.getSupID()));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(dto.getPayment_ID(), dto.getDate(), dto.getReason(), dto.getAmount(), dto.getMethod(), dto.getType(), dto.getBuyerID(), dto.getEmpID(), dto.getSupID()));
    }

    @Override
    public Boolean deletePayment(String paymentID) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(paymentID);
    }

    @Override
    public double getSummery(String credit) throws SQLException, ClassNotFoundException {
        return paymentDAO.getSummery("credit");
    }
}
