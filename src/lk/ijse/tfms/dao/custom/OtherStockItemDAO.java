package lk.ijse.tfms.dao.custom;

import lk.ijse.tfms.dao.CrudDAO;
import lk.ijse.tfms.entity.CustomEntity;
import lk.ijse.tfms.entity.OtherStocks;
import lk.ijse.tfms.entity.OtherSuppliersStocks;

import java.sql.SQLException;

public interface OtherStockItemDAO extends CrudDAO<CustomEntity> {
    public Boolean insertInto_OtherStocks(OtherSuppliersStocks entity) throws SQLException, ClassNotFoundException;
    public Boolean insertInto_OtherSuppliersStocks(OtherStocks entity) throws SQLException, ClassNotFoundException;
    public String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException;
    public String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException;
}
