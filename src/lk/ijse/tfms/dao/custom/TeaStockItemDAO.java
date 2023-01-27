package lk.ijse.tfms.dao.custom;

import lk.ijse.tfms.dao.CrudDAO;
import lk.ijse.tfms.entity.TeaStock;

import java.sql.SQLException;

public interface TeaStockItemDAO extends CrudDAO<TeaStock> {
    public boolean updateAvailableQTY(String stockID, int qtyP, int qtyOP, int qtyFOP, int qtyGFOP, int qtyTGFOP, int qtyFTGFOP, int qtyBOP, int qtyFBOP) throws SQLException, ClassNotFoundException;
}
