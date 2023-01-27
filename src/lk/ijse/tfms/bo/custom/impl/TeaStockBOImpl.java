package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.TeaStockBO;
import lk.ijse.tfms.dao.custom.TeaStockItemDAO;
import lk.ijse.tfms.dao.custom.impl.TeaStockItemDAOImpl;
import lk.ijse.tfms.dto.TeaStockItemDTO;
import lk.ijse.tfms.entity.TeaStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaStockBOImpl implements TeaStockBO {
    TeaStockItemDAO teaStockItemDAO = new TeaStockItemDAOImpl();

    @Override
    public ArrayList<TeaStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaStock> stockItemsData = teaStockItemDAO.getData();
        ArrayList<TeaStockItemDTO> teaStockItemDTOS = new ArrayList<>();

        for (TeaStock t : stockItemsData) {
            teaStockItemDTOS.add(new TeaStockItemDTO(t.getStock_ID(), t.getType(), t.getInput_Date(), t.getOne_bag_Weight(), t.getQty(), t.getAvailableQty()));
        }
        return teaStockItemDTOS;
    }

    @Override
    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.getCurrentID();
    }

    @Override
    public boolean insertNewStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.add(new TeaStock(dto.getStockID(), dto.getType(), dto.getDate(), dto.getWeight(), dto.getQty(), dto.getAvailableQty()));
    }

    @Override
    public boolean updateStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.update(new TeaStock(dto.getStockID(), dto.getType(), dto.getDate(), dto.getWeight(), dto.getQty(), dto.getAvailableQty()));
    }

    @Override
    public Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.delete(stockID);
    }
}
