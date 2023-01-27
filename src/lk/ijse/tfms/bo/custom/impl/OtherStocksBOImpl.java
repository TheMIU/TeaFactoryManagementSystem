package lk.ijse.tfms.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.tfms.bo.custom.OtherStocksBO;
import lk.ijse.tfms.dao.custom.OtherStockItemDAO;
import lk.ijse.tfms.dao.custom.impl.OtherStockItemDAOImpl;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dto.OtherStockItemDTO;
import lk.ijse.tfms.entity.CustomEntity;
import lk.ijse.tfms.entity.OtherStocks;
import lk.ijse.tfms.entity.OtherSuppliersStocks;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherStocksBOImpl implements OtherStocksBO {
    OtherStockItemDAO otherStockItemDAO = new OtherStockItemDAOImpl();

    @Override
    public ArrayList<OtherStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> stockItemsData = otherStockItemDAO.getData();
        ArrayList<OtherStockItemDTO> otherStockItemDTOS = new ArrayList<>();

        for (CustomEntity c : stockItemsData) {
            //String date, String stockID, String supplierID, String supplierName, String type, int qty, double price
            otherStockItemDTOS.add(new OtherStockItemDTO(
                    c.getOtherSuppliersStocks_date(),
                    c.getOther_stock_ID(),
                    c.getSuppliers_ID(),
                    c.getOther_supplier_name(),
                    c.getOther_supplier_Type(),
                    c.getOther_stock_qty(),
                    c.getOther_stock_price()));
        }

        return otherStockItemDTOS;
    }

    @Override
    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getCurrentID();
    }

    @Override
    public String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getSupplierName(sup_id);
    }

    @Override
    public String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.getSupplierType(sup_id);
    }

    @Override
    // transaction
    public boolean insertNewStockItemTransaction(OtherStockItemDTO dto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isInsertedToOther_suppliers_stocks = otherStockItemDAO.insertInto_OtherSuppliersStocks(new OtherStocks(
                dto.getStockID(),
                dto.getType(),
                dto.getQty(),
                dto.getPrice()));

        boolean isInsertedToOther_stocks = otherStockItemDAO.insertInto_OtherStocks(new OtherSuppliersStocks(
                dto.getSupplierID(),
                dto.getStockID(),
                dto.getDate()));

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

    @Override
    public boolean updateStockItem(OtherStockItemDTO dto) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.update(new CustomEntity(dto.getSupplierID(), dto.getStockID(), dto.getDate(), dto.getType(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public Boolean deleteStockTransactions(String stockID) throws SQLException, ClassNotFoundException {
        return otherStockItemDAO.delete(stockID);
    }
}
