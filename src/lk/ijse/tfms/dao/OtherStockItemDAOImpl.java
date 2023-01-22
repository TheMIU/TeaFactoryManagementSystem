package lk.ijse.tfms.dao;

import javafx.scene.control.Alert;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.dto.OtherStockItemDTO;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherStockItemDAOImpl {

    public static String getCurrentStockID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM other_stocks ORDER BY CAST(SUBSTRING(Stock_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static ArrayList<OtherStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<OtherStockItemDTO> otherStockItemDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select other_suppliers_stocks.date,\n" +
                "       other_suppliers_stocks.Stock_ID,\n" +
                "       other_suppliers_stocks.Supplier_ID,\n" +
                "       Name,\n" +
                "       Supplier_Type,\n" +
                "       Qty,\n" +
                "       Price\n" +
                "from other_suppliers_stocks\n" +
                "         join other_suppliers os on other_suppliers_stocks.Supplier_ID = os.Supplier_ID\n" +
                "         join Other_Stocks O on O.Stock_ID = other_suppliers_stocks.Stock_ID " +
                " ORDER BY CAST(SUBSTRING(other_suppliers_stocks.Stock_ID, 2) AS UNSIGNED);");
        while (rs.next()) {
            otherStockItemDTOData.add(new OtherStockItemDTO(rs.getString("date"),
                    rs.getString("Stock_ID"),
                    rs.getString("Supplier_ID"),
                    rs.getString("Name"),
                    rs.getString("Supplier_Type"),
                    rs.getInt("Qty"),
                    rs.getDouble("Price")));
        }
        return otherStockItemDTOData;
    }

    public static Boolean deleteStockTransactions(String stockID) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        Boolean isDeletedFromOther_suppliers_stocks = CrudUtil.execute("delete from other_suppliers_stocks where Stock_ID = ?", stockID);
        Boolean isDeletedFromOther_stocks = CrudUtil.execute("delete from other_stocks where Stock_ID = ?", stockID);

        if (isDeletedFromOther_stocks && isDeletedFromOther_suppliers_stocks) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "rollback !").show();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    public static String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select Name from other_suppliers where Supplier_ID = ?", sup_id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    public static String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select Supplier_Type from other_suppliers where Supplier_ID = ?", sup_id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    public static boolean insertNewStockItemTransaction(OtherStockItemDTO otherStockItemDTO) throws SQLException, ClassNotFoundException {
        String date = otherStockItemDTO.getDate();
        String stockID = otherStockItemDTO.getStockID();
        String supID = otherStockItemDTO.getSupplierID();
        String type = otherStockItemDTO.getType();
        int qty = otherStockItemDTO.getQty();
        double price = otherStockItemDTO.getPrice();

        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToOther_suppliers_stocks = CrudUtil.execute("insert into other_stocks(Stock_ID, Stock_Type, Qty, Price) " +
                "values(?, ?, ?, ?)", stockID, type, qty, price);
        Boolean isInsertedToOther_stocks = CrudUtil.execute("insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)" +
                "values (?, ?, ?);", supID, stockID, date);

        if (isInsertedToOther_suppliers_stocks && isInsertedToOther_stocks) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "rollback !").show();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    public static boolean updateStockItem(OtherStockItemDTO otherStockItemDTO) throws SQLException, ClassNotFoundException {
        String date = otherStockItemDTO.getDate();
        String stockID = otherStockItemDTO.getStockID();
        String supID = otherStockItemDTO.getSupplierID();
        String type = otherStockItemDTO.getType();
        int qty = otherStockItemDTO.getQty();
        double price = otherStockItemDTO.getPrice();

        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToOther_stocks = CrudUtil.execute("update other_suppliers_stocks" +
                " set Supplier_ID = ?," +
                "    Stock_ID    = ?," +
                "    Date        = ?" +
                " where Stock_ID = ?;", supID, stockID, date,stockID);

        Boolean isInsertedToOther_suppliers_stocks = CrudUtil.execute("update other_stocks " +
                " set Stock_ID   = ?," +
                "    Stock_Type = ?," +
                "    Qty        = ?," +
                "    Price      = ?" +
                " where Stock_ID = ?;", stockID,type, qty, price,stockID);

        if (isInsertedToOther_suppliers_stocks && isInsertedToOther_stocks) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            new Alert(Alert.AlertType.ERROR, "rollback !").show();
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
}
