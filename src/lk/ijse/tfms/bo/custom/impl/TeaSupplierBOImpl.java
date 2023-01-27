package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.TeaSupplierBO;
import lk.ijse.tfms.dao.custom.TeaSupplierDAO;
import lk.ijse.tfms.dao.custom.impl.TeaSupplierDAOImpl;
import lk.ijse.tfms.dto.TeaSupplierDTO;
import lk.ijse.tfms.entity.TeaSuppliers;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaSupplierBOImpl implements TeaSupplierBO {
    TeaSupplierDAO teaSupplierDAO = new TeaSupplierDAOImpl();

    @Override
    public ArrayList<TeaSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaSuppliers> supplierData = teaSupplierDAO.getData();
        ArrayList<TeaSupplierDTO> teaSupplierDTOS = new ArrayList<>();

        for (TeaSuppliers t : supplierData) {
            teaSupplierDTOS.add(new TeaSupplierDTO(t.getSuppliers_ID(), t.getName(), t.getId(), t.getAddress(), t.getMobile_No()));
        }

        return teaSupplierDTOS;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.getCurrentID();
    }

    @Override
    public boolean insertNewSupplier(TeaSupplierDTO dto) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.add(new TeaSuppliers(dto.getSup_id(), dto.getName(), dto.getId(), dto.getAddress(), dto.getMobile_num()));
    }

    @Override
    public boolean updateSupplier(TeaSupplierDTO dto) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.update(new TeaSuppliers(dto.getSup_id(), dto.getName(), dto.getId(), dto.getAddress(), dto.getMobile_num()));
    }

    @Override
    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.delete(supID);
    }
}
