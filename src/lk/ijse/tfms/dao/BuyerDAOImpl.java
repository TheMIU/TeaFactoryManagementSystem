package lk.ijse.tfms.dao;

import lk.ijse.tfms.entity.Buyers;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerDAOImpl {

    public ArrayList<Buyers> getBuyerData() throws SQLException, ClassNotFoundException {
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

    public Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from buyers where Buyer_ID = ?",buyerID);
    }

    public  boolean insertNewBuyer(Buyers entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into buyers values (?,?,?,?);",
                entity.getBuyer_ID(),
                entity.getName(),
                entity.getAddress(),
                entity.getContact()
        );
    }

    public boolean updateBuyer(Buyers entity, String buyerID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update buyers set  Name = ?, Address = ?, Contact = ? where Buyer_ID = ?;",
                entity.getName(),
                entity.getAddress(),
                entity.getContact(),
                buyerID
        );
        return isUpdated;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM buyers ORDER BY CAST(SUBSTRING(Buyer_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}
