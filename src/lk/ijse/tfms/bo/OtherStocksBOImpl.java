package lk.ijse.tfms.bo;

import javafx.scene.control.Alert;
import lk.ijse.tfms.dao.OtherStockItemDAOImpl;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dto.OtherStockItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherStocksBOImpl {
    OtherStockItemDAOImpl otherStockItemDAO = new OtherStockItemDAOImpl();

    public ArrayList<OtherStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getStockItemsData();
    }

    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getCurrentStockID();
    }

    public String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getSupplierName(sup_id);
    }

    public String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getSupplierType(sup_id);
    }

    // transaction
    public boolean insertNewStockItemTransaction(OtherStockItemDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isInsertedToOther_suppliers_stocks = otherStockItemDAO.insertInto_OtherSuppliersStocks(
                dto.getStockID(),
                dto.getType(),
                dto.getQty(),
                dto.getPrice());

        boolean isInsertedToOther_stocks = otherStockItemDAO.insertInto_OtherStocks(
                dto.getSupplierID(),
                dto.getStockID(),
                dto.getDate());

        if (isInsertedToOther_suppliers_stocks && isInsertedToOther_stocks) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "rollback !").show();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    public boolean updateStockItem(OtherStockItemDTO otherStockItemDTO) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.updateStockItem(otherStockItemDTO);
    }

    public Boolean deleteStockTransactions(String stockID) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.deleteStockTransactions(stockID);
    }
}
