package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.DailyCropBO;
import lk.ijse.tfms.dao.FactoryDAO;
import lk.ijse.tfms.dao.custom.DailyCropDAO;
import lk.ijse.tfms.dao.custom.impl.DailyCropDAOImpl;
import lk.ijse.tfms.dto.DailyCropDTO;
import lk.ijse.tfms.entity.DailyCrop;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyCropBOImpl implements DailyCropBO {
    DailyCropDAO dailyCropDAO = (DailyCropDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.DAILY_CROP);

    @Override
    public String getTotalKg(LocalDate now) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getTotalKg(LocalDate.now());
    }

    @Override
    public String getSupplierName(String supID) {
        return dailyCropDAO.getSupplierName(supID);
    }

    @Override
    public ArrayList<DailyCropDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<DailyCrop> data = dailyCropDAO.getData();
        ArrayList<DailyCropDTO> dailyCropDTOS = new ArrayList<>();

        for (DailyCrop d : data) {
            dailyCropDTOS.add(new DailyCropDTO(d.getSuppliers_ID(), d.getDate(), d.getNetWeight()));
        }

        return dailyCropDTOS;
    }

    @Override
    public boolean saveDailyCrop(DailyCropDTO dto) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.add(new DailyCrop(dto.getSupID(), dto.getDate(), dto.getWeight()));
    }

    @Override
    public Boolean deleteSelected(String supID) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.delete(supID);
    }

    @Override
    public boolean update(DailyCropDTO dto) throws SQLException, ClassNotFoundException {
        return dailyCropDAO.update(new DailyCrop(dto.getSupID(), dto.getDate(), dto.getWeight()));
    }
}
