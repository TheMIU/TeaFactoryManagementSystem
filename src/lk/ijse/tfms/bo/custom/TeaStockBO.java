package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.TeaStockItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeaStockBO extends SuperBO {
    public ArrayList<TeaStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException;

    public String getCurrentStockID() throws SQLException, ClassNotFoundException;

    public boolean insertNewStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateStockItem(TeaStockItemDTO dto) throws SQLException, ClassNotFoundException;

    public Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException;
}
