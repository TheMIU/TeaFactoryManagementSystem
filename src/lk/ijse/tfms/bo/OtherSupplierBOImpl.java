package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.OtherSupplierDAOImpl;
import lk.ijse.tfms.dto.OtherSupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OtherSupplierBOImpl {
    OtherSupplierDAOImpl otherSupplierDAO = new OtherSupplierDAOImpl();

    public ArrayList<OtherSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.getSupplierData();
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.getCurrentID();
    }

    public boolean insertNewSupplier(OtherSupplierDTO otherSupplierDTO) throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.insertNewSupplier(otherSupplierDTO);
    }

    public boolean updateSupplier(OtherSupplierDTO otherSupplierDTO, String supID) throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.updateSupplier(otherSupplierDTO, supID);
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return otherSupplierDAO.deleteSupplier(supID);
    }
}
