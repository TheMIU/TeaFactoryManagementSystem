package lk.ijse.tfms.dao;
import lk.ijse.tfms.entity.TeaSuppliers;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeaSupplierDAOImpl {

    public boolean insertNewSupplier(TeaSuppliers teaSuppliers) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into tea_suppliers values (?,?,?,?,?);",
                teaSuppliers.getSuppliers_ID(),
                teaSuppliers.getName(),
                teaSuppliers.getId(),
                teaSuppliers.getAddress(),
                teaSuppliers.getMobile_No()
        );
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM tea_suppliers ORDER BY CAST(SUBSTRING(Suppliers_ID, 3) AS UNSIGNED) DESC LIMIT 1");

        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public boolean updateSupplier(TeaSuppliers entity, String supID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update tea_suppliers set Name = ?, ID = ?, Address = ?,Mobile_No = ? " +
                        "where Suppliers_ID = ?;",
                entity.getName(),
                entity.getId(),
                entity.getAddress(),
                entity.getMobile_No(),
                supID
        );
        return isUpdated;
    }

    public ArrayList<TeaSuppliers> getSupplierData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaSuppliers> teaSuppliersData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM tea_suppliers ORDER BY CAST(SUBSTRING(Suppliers_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            teaSuppliersData.add(new TeaSuppliers(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        return teaSuppliersData;
    }

    public Boolean deleteSupplier(String supID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from tea_suppliers where Suppliers_ID = ?",supID);
        return isDeleted;
    }
}
