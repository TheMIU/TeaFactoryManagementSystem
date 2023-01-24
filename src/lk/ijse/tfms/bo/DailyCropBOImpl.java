package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.DailyCropDAOImpl;
import lk.ijse.tfms.dto.DailyCropDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyCropBOImpl {
    DailyCropDAOImpl dailyCropDAO = new DailyCropDAOImpl();

    public String getTotalKg(LocalDate now) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getTotalKg(LocalDate.now());
    }

    public String getSupplierName(String supID) {
       return dailyCropDAO.getSupplierName(supID);
    }

    public ArrayList<DailyCropDTO> getData() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getData();
    }

    public boolean saveDailyCrop(DailyCropDTO dailyCropDTO) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.saveDailyCrop(dailyCropDTO);
    }

    public Boolean deleteSelected(String supID) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.deleteSelected(supID);
    }

    public boolean update(DailyCropDTO dc) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.update(dc);
    }
}
