package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.OtherStockItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OtherStocksBO extends SuperBO {
    public ArrayList<OtherStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException;
    public String getCurrentStockID() throws SQLException, ClassNotFoundException;
    public String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException;
    public String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException;
    public boolean insertNewStockItemTransaction(OtherStockItemDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateStockItem(OtherStockItemDTO dto) throws SQLException, ClassNotFoundException;
    public Boolean deleteStockTransactions(String stockID) throws SQLException, ClassNotFoundException;
}
