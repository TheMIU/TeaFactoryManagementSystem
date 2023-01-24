package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.BuyerDAOImpl;
import lk.ijse.tfms.dto.BuyerDTO;
import lk.ijse.tfms.entity.Buyers;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerBOImpl {
    BuyerDAOImpl buyerDAO = new BuyerDAOImpl();

    public ArrayList<BuyerDTO> getBuyerData() throws SQLException, ClassNotFoundException {
        ArrayList<BuyerDTO> buyerDTOS = new ArrayList<>();
        ArrayList<Buyers> buyers = buyerDAO.getBuyerData();

        for (Buyers b : buyers ){
           buyerDTOS.add(new BuyerDTO(b.getBuyer_ID(),b.getName(),b.getAddress(),b.getContact()));
        }
        return buyerDTOS;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return buyerDAO.getCurrentID();
    }

    public boolean insertNewBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException {
        return  buyerDAO.insertNewBuyer(new Buyers(buyerDTO.getBuyer_ID(), buyerDTO.getName(), buyerDTO.getAddress(), buyerDTO.getContact()));
    }

    public boolean updateBuyer(BuyerDTO buyerDTO, String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.updateBuyer(new Buyers(buyerDTO.getBuyer_ID(), buyerDTO.getName(), buyerDTO.getAddress(), buyerDTO.getContact()), buyerID);
    }

    public Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.deleteBuyer(buyerID);
    }
}
