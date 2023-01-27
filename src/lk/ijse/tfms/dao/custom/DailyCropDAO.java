package lk.ijse.tfms.dao.custom;

import lk.ijse.tfms.dao.CrudDAO;
import lk.ijse.tfms.entity.DailyCrop;

import java.sql.SQLException;
import java.time.LocalDate;

public interface DailyCropDAO extends CrudDAO<DailyCrop> {
    public String getSupplierName(String supID);

    public String getTotalKg(LocalDate now) throws SQLException, ClassNotFoundException;

    public String getTotalKg() throws SQLException, ClassNotFoundException;

    public String getProductionKg() throws SQLException, ClassNotFoundException;

    public String getBagsCountKg() throws SQLException, ClassNotFoundException;

}
