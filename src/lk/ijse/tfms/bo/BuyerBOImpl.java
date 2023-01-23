package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.BuyerDAOImpl;
import lk.ijse.tfms.dto.BuyerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerBOImpl {
    BuyerDAOImpl buyerDAO = new BuyerDAOImpl();

    public ArrayList<BuyerDTO> getBuyerData() throws SQLException, ClassNotFoundException {
        return  buyerDAO.getBuyerData();
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return buyerDAO.getCurrentID();
    }

    public boolean insertNewBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException {
        return  buyerDAO.insertNewBuyer(new BuyerDTO(buyerDTO.getBuyer_ID(), buyerDTO.getName(), buyerDTO.getAddress(), buyerDTO.getContact()));
    }

    public boolean updateBuyer(BuyerDTO buyerDTO, String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.updateBuyer(buyerDTO, buyerID);
    }

    public Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.deleteBuyer(buyerID);
    }
}
