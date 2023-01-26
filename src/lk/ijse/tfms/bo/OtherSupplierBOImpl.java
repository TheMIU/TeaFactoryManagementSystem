package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.OtherSupplierDAOImpl;
import lk.ijse.tfms.dto.OtherSupplierDTO;
import lk.ijse.tfms.entity.OtherSuppliers;

import java.sql.SQLException;
import java.util.ArrayList;

public class OtherSupplierBOImpl {
    OtherSupplierDAOImpl otherSupplierDAO = new OtherSupplierDAOImpl();

    public ArrayList<OtherSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<OtherSupplierDTO> otherSupplierDTOS = new ArrayList<>();
        ArrayList<OtherSuppliers> supplierData = otherSupplierDAO.getSupplierData();

        for(OtherSuppliers o : supplierData){
            otherSupplierDTOS.add(new OtherSupplierDTO(o.getSupplier_ID(),o.getSupplier_Type(),o.getName(),o.getId(),o.getContact()));
        }

        return otherSupplierDTOS;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.getCurrentID();
    }

    public boolean insertNewSupplier(OtherSupplierDTO dto) throws SQLException, ClassNotFoundException {
        System.out.println(dto);
        return otherSupplierDAO.insertNewSupplier(new OtherSuppliers(dto.getSupID(),dto.getSup_Type(),dto.getName(),dto.getID(),dto.getContact()));
    }

    public boolean updateSupplier(OtherSupplierDTO dto, String supID) throws SQLException, ClassNotFoundException {
        //String supID, String sup_Type, String ID, String name, String contact
        return otherSupplierDAO.updateSupplier(new OtherSuppliers(dto.getSupID(),dto.getSup_Type(),dto.getName(),dto.getID(),dto.getContact()), supID);
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.deleteSupplier(supID);
    }
}
