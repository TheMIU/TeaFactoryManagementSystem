package lk.ijse.tfms.dao;

import lk.ijse.tfms.entity.Buyers;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerDAOImpl implements BuyerDAO{

    public ArrayList<Buyers> getData() throws SQLException, ClassNotFoundException {
        ArrayList<Buyers> buyers = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM buyers ORDER BY CAST(SUBSTRING(Buyer_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            buyers.add(new Buyers(rs.getString("Buyer_ID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Contact")));
        }
        return buyers;
    }

    public Boolean delete(String buyerID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from buyers where Buyer_ID = ?",buyerID);
    }

    public  boolean add(Buyers entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into buyers values (?,?,?,?);",
                entity.getBuyer_ID(),
                entity.getName(),
                entity.getAddress(),
                entity.getContact()
        );
    }

    public boolean update(Buyers entity, String buyerID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update buyers set  Name = ?, Address = ?, Contact = ? where Buyer_ID = ?;",
                entity.getName(),
                entity.getAddress(),
                entity.getContact(),
                buyerID
        );
        return isUpdated;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
