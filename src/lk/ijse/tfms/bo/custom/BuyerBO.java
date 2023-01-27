package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.dto.BuyerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BuyerBO {
    public ArrayList<BuyerDTO> getBuyerData() throws SQLException, ClassNotFoundException;

    public String getCurrentID() throws SQLException, ClassNotFoundException;

    public boolean insertNewBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException;

    public Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException;
}
