package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.DailyCropDAOImpl;
import lk.ijse.tfms.dto.DailyCropDTO;
import lk.ijse.tfms.entity.DailyCrop;

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
        ArrayList<DailyCrop> data = dailyCropDAO.getData();
        ArrayList<DailyCropDTO> dailyCropDTOS = new ArrayList<>();

        for (DailyCrop d : data){
            dailyCropDTOS.add(new DailyCropDTO(d.getSuppliers_ID(),d.getDate(),d.getNetWeight()));
        }

        return dailyCropDTOS;
    }

    public boolean saveDailyCrop(DailyCropDTO dto) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.saveDailyCrop(new DailyCrop(dto.getSupID(),dto.getDate(),dto.getWeight()));
    }

    public Boolean deleteSelected(String supID) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.deleteSelected(supID);
    }

    public boolean update(DailyCropDTO dto) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.update(new DailyCrop(dto.getSupID(),dto.getDate(),dto.getWeight()));
    }
}
