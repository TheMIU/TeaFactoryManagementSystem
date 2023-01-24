package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.TeaStockItemDAOImpl;
import lk.ijse.tfms.dto.TeaStockItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaStockBOImpl {
    TeaStockItemDAOImpl teaStockItemDAO = new TeaStockItemDAOImpl();

    public ArrayList<TeaStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.getStockItemsData();
    }

    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.getCurrentStockID();
    }

    public boolean insertNewStockItem(TeaStockItemDTO teaStockItemDTO) throws SQLException, ClassNotFoundException {
        return  teaStockItemDAO.insertNewStockItem(teaStockItemDTO);
    }

    public boolean updateStockItem(TeaStockItemDTO teaStockItemDTO) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.updateStockItem(teaStockItemDTO);
    }

    public Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException {
        return teaStockItemDAO.deleteStockItem(stockID);
    }
}
