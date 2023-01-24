package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.TeaSupplierDAOImpl;
import lk.ijse.tfms.dto.TeaSupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaSupplierBOImpl {
    TeaSupplierDAOImpl teaSupplierDAO = new TeaSupplierDAOImpl();

    public ArrayList<TeaSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.getSupplierData();
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.getCurrentID();
    }

    public boolean insertNewSupplier(TeaSupplierDTO teaSupplierDTO) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.insertNewSupplier(teaSupplierDTO);
    }

    public boolean updateSupplier(TeaSupplierDTO teaSupplierDTO, String supID) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.updateSupplier(teaSupplierDTO, supID);
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.deleteSupplier(supID);
    }
}
