package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.TeaSupplierDAOImpl;
import lk.ijse.tfms.dto.TeaSupplierDTO;
import lk.ijse.tfms.entity.TeaSuppliers;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeaSupplierBOImpl {
    TeaSupplierDAOImpl teaSupplierDAO = new TeaSupplierDAOImpl();

    public ArrayList<TeaSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaSuppliers> supplierData = teaSupplierDAO.getSupplierData();
        ArrayList<TeaSupplierDTO> teaSupplierDTOS = new ArrayList<>();

        for(TeaSuppliers t :supplierData){
            teaSupplierDTOS.add(new TeaSupplierDTO(t.getSuppliers_ID(),t.getName(),t.getId(),t.getAddress(),t.getMobile_No()));
        }

        return teaSupplierDTOS;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.getCurrentID();
    }

    public boolean insertNewSupplier(TeaSupplierDTO dto) throws SQLException, ClassNotFoundException {
         return teaSupplierDAO.insertNewSupplier(new TeaSuppliers(dto.getSup_id(),dto.getName(),dto.getId(),dto.getAddress(),dto.getMobile_num()));
    }

    public boolean updateSupplier(TeaSupplierDTO dto, String supID) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.updateSupplier(new TeaSuppliers(dto.getSup_id(),dto.getName(),dto.getId(),dto.getAddress(),dto.getMobile_num()), supID);
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        return teaSupplierDAO.deleteSupplier(supID);
    }
}
