package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.TeaStockItemDAOImpl;
import lk.ijse.tfms.dto.TeaStockItemDTO;
import lk.ijse.tfms.entity.TeaStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaStockBOImpl {
    TeaStockItemDAOImpl teaStockItemDAO = new TeaStockItemDAOImpl();

    public ArrayList<TeaStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaStock> stockItemsData = teaStockItemDAO.getStockItemsData();
        ArrayList<TeaStockItemDTO> teaStockItemDTOS = new ArrayList<>();

        for (TeaStock t : stockItemsData){
            teaStockItemDTOS.add(new TeaStockItemDTO(t.getStock_ID(),t.getType(),t.getInput_Date(),t.getOne_bag_Weight(),t.getQty(),t.getAvailableQty()));
        }
        return teaStockItemDTOS;
    }

    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.getCurrentStockID();
    }

    public boolean insertNewStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException {
        return  teaStockItemDAO.insertNewStockItem(new TeaStock(dto.getStockID(),dto.getType(),dto.getDate(),dto.getWeight(),dto.getQty(),dto.getAvailableQty()));
    }

    public boolean updateStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.updateStockItem(new TeaStock(dto.getStockID(),dto.getType(),dto.getDate(),dto.getWeight(),dto.getQty(),dto.getAvailableQty()));
    }

    public Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.deleteStockItem(stockID);
    }
}
