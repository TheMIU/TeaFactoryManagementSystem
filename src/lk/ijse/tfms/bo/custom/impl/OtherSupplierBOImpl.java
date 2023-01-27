package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.OtherSupplierBO;
import lk.ijse.tfms.dao.custom.OtherSupplierDAO;
import lk.ijse.tfms.dao.custom.impl.OtherSupplierDAOImpl;
import lk.ijse.tfms.dto.OtherSupplierDTO;
import lk.ijse.tfms.entity.OtherSuppliers;

import java.sql.SQLException;
import java.util.ArrayList;

public class OtherSupplierBOImpl implements OtherSupplierBO {
    OtherSupplierDAO otherSupplierDAO = new OtherSupplierDAOImpl();

    @Override
    public ArrayList<OtherSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<OtherSupplierDTO> otherSupplierDTOS = new ArrayList<>();
        ArrayList<OtherSuppliers> supplierData = otherSupplierDAO.getData();

        for (OtherSuppliers o : supplierData) {
            otherSupplierDTOS.add(new OtherSupplierDTO(o.getSupplier_ID(), o.getSupplier_Type(), o.getName(), o.getId(), o.getContact()));
        }

        return otherSupplierDTOS;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.getCurrentID();
    }

    @Override
    public boolean insertNewSupplier(OtherSupplierDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto);
        return otherSupplierDAO.add(new OtherSuppliers(dto.getSupID(), dto.getSup_Type(), dto.getName(), dto.getID(), dto.getContact()));
    }

    @Override
    public boolean updateSupplier(OtherSupplierDTO dto) throws SQLException, ClassNotFoundException {
        //String supID, String sup_Type, String ID, String name, String contact
        return otherSupplierDAO.update(new OtherSuppliers(dto.getSupID(), dto.getSup_Type(), dto.getName(), dto.getID(), dto.getContact()));
    }

    @Override
    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.delete(supID);
    }
}
