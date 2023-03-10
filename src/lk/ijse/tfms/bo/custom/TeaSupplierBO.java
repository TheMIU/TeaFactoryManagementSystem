package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.TeaSupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeaSupplierBO extends SuperBO {
    public ArrayList<TeaSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException;

    public String getCurrentID() throws SQLException, ClassNotFoundException;

    public boolean insertNewSupplier(TeaSupplierDTO dto) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(TeaSupplierDTO dto) throws SQLException, ClassNotFoundException;

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException;
}
