package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.OtherSupplierDTO;
import lk.ijse.tfms.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherSupplierDAOImpl {
    public ArrayList<OtherSupplierDTO> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<OtherSupplierDTO> otherSupplierDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM other_suppliers ORDER BY CAST(SUBSTRING(Supplier_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            otherSupplierDTOData.add(new OtherSupplierDTO(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        return otherSupplierDTOData;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM other_suppliers ORDER BY CAST(SUBSTRING(Supplier_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public boolean updateSupplier(OtherSupplierDTO otherSupplierDTO, String supID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update other_suppliers set Supplier_ID = ?, Supplier_Type = ?, ID = ?, Name = ?,Contact = ? " +
                        "where Supplier_ID = ?;",
                otherSupplierDTO.getSupID(),
                otherSupplierDTO.getSup_Type(),
                otherSupplierDTO.getID(),
                otherSupplierDTO.getName(),
                otherSupplierDTO.getContact(),
                supID
        );
        return isUpdated;
    }

    public boolean insertNewSupplier(OtherSupplierDTO otherSupplierDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into other_suppliers values (?,?,?,?,?);",
                otherSupplierDTO.getSupID(),
                otherSupplierDTO.getSup_Type(),
                otherSupplierDTO.getID(),
                otherSupplierDTO.getName(),
                otherSupplierDTO.getContact()
        );
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from other_suppliers where Supplier_ID = ?",supID);
        return isDeleted;
    }
}
