package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.BuyerDTO;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BuyerDAOImpl {

    public static ArrayList<BuyerDTO> getBuyerData() throws SQLException, ClassNotFoundException {
        ArrayList<BuyerDTO> buyerDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM buyers ORDER BY CAST(SUBSTRING(Buyer_ID, 2) AS UNSIGNED)");
        while (rs.next()) {
            buyerDTOData.add(new BuyerDTO(rs.getString("Buyer_ID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Contact")));
        }
        return buyerDTOData;
    }

    public static Boolean deleteBuyer(String buyerID) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("delete from buyers where Buyer_ID = ?",buyerID);
    }

    public static boolean insertNewBuyer(BuyerDTO buyerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into buyers values (?,?,?,?);",
                buyerDTO.getBuyer_ID(),
                buyerDTO.getName(),
                buyerDTO.getAddress(),
                buyerDTO.getContact()
        );
    }

    public static boolean updateBuyer(BuyerDTO buyerDTO, String buyerID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update buyers set  Name = ?, Address = ?, Contact = ? where Buyer_ID = ?;",
                buyerDTO.getName(),
                buyerDTO.getAddress(),
                buyerDTO.getContact(),
                buyerID
        );
        return isUpdated;
    }

    public static String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM buyers ORDER BY CAST(SUBSTRING(Buyer_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
}
