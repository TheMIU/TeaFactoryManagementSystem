package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.OtherSupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OtherSupplierBO extends SuperBO {
    public ArrayList<OtherSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException;
    public String getCurrentID() throws SQLException, ClassNotFoundException;
    public boolean insertNewSupplier(OtherSupplierDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateSupplier(OtherSupplierDTO dto) throws SQLException, ClassNotFoundException;
    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException;
}
