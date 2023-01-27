package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.BuyerBO;
import lk.ijse.tfms.dao.BuyerDAO;
import lk.ijse.tfms.dao.BuyerDAOImpl;
import lk.ijse.tfms.dto.BuyerDTO;
import lk.ijse.tfms.entity.Buyers;

import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerBOImpl implements BuyerBO {
    BuyerDAO buyerDAO = new BuyerDAOImpl();

    @Override
    public ArrayList<BuyerDTO> getBuyerData() throws SQLException, ClassNotFoundException {
        ArrayList<BuyerDTO> buyerDTOS = new ArrayList<>();
        ArrayList<Buyers> buyers = buyerDAO.getData();

        for (Buyers b : buyers ){
           buyerDTOS.add(new BuyerDTO(b.getBuyer_ID(),b.getName(),b.getAddress(),b.getContact()));
        }
        return buyerDTOS;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return buyerDAO.getCurrentID();
    }

    @Override
    public boolean insertNewBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException {
        return  buyerDAO.add(new Buyers(buyerDTO.getBuyer_ID(), buyerDTO.getName(), buyerDTO.getAddress(), buyerDTO.getContact()));
    }

    @Override
    public boolean updateBuyer(BuyerDTO buyerDTO, String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.update(new Buyers(buyerDTO.getBuyer_ID(), buyerDTO.getName(), buyerDTO.getAddress(), buyerDTO.getContact()), buyerID);
    }

    @Override
    public Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException {
        return buyerDAO.delete(buyerID);
    }
}
