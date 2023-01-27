package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.DailyCropDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface DailyCropBO extends SuperBO {
    public String getTotalKg(LocalDate now) throws SQLException, ClassNotFoundException;
    public String getSupplierName(String supID);
    public ArrayList<DailyCropDTO> getData() throws SQLException, ClassNotFoundException;
    public boolean saveDailyCrop(DailyCropDTO dto) throws SQLException, ClassNotFoundException;
    public Boolean deleteSelected(String supID) throws SQLException, ClassNotFoundException;
    public boolean update(DailyCropDTO dto) throws SQLException, ClassNotFoundException;
}
