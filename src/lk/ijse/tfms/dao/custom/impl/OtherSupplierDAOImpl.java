package lk.ijse.tfms.dao.custom.impl;

import lk.ijse.tfms.dao.custom.OtherSupplierDAO;
import lk.ijse.tfms.entity.OtherSuppliers;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherSupplierDAOImpl implements OtherSupplierDAO {
    @Override
    public ArrayList<OtherSuppliers> getData() throws SQLException, ClassNotFoundException {
        ArrayList<OtherSuppliers> otherSuppliersData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM other_suppliers ORDER BY CAST(SUBSTRING(Supplier_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            otherSuppliersData.add(new OtherSuppliers(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)));
        }
        return otherSuppliersData;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM other_suppliers ORDER BY CAST(SUBSTRING(Supplier_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public boolean update(OtherSuppliers entity) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update other_suppliers set Supplier_ID = ?, Supplier_Type = ?, ID = ?, Name = ?,Contact = ? " +
                        "where Supplier_ID = ?;",
                entity.getSupplier_ID(),
                entity.getSupplier_Type(),
                entity.getId(),
                entity.getName(),
                entity.getContact(),
                entity.getSupplier_ID()
        );
        return isUpdated;
    }

    @Override
    public boolean add(OtherSuppliers entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into other_suppliers values (?,?,?,?,?);",
                entity.getSupplier_ID(),
                entity.getSupplier_Type(),
                entity.getName(),
                entity.getId(),
                entity.getContact()
        );
    }

    @Override
    public Boolean delete(String supID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from other_suppliers where Supplier_ID = ?", supID);
        return isDeleted;
    }
}
