package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.PaymentDTO;

import java.sql.SQLException;

public interface TeaSellingBO extends SuperBO {
    public boolean transaction(PaymentDTO dto, String stockID, int qtyP, int qtyOP, int qtyFOP, int qtyGFOP, int qtyTGFOP, int qtyFTGFOP, int qtyBOP, int qtyFBOP) throws SQLException, ClassNotFoundException;
}
