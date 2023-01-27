package lk.ijse.tfms.dao.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.tfms.dao.custom.OtherStockItemDAO;
import lk.ijse.tfms.db.DBConnection;
import lk.ijse.tfms.entity.CustomEntity;
import lk.ijse.tfms.entity.OtherStocks;
import lk.ijse.tfms.entity.OtherSuppliersStocks;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtherStockItemDAOImpl implements OtherStockItemDAO {

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM other_stocks ORDER BY CAST(SUBSTRING(Stock_ID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<CustomEntity> getData() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> otherStockItemData = new ArrayList<>();

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
            otherStockItemData.add(new CustomEntity(
                    rs.getString("date"),
                    rs.getString("Stock_ID"),
                    rs.getString("Supplier_ID"),
                    rs.getString("Name"),
                    rs.getString("Supplier_Type"),
                    rs.getInt("Qty"),
                    rs.getDouble("Price")));
        }
        return otherStockItemData;
    }

    @Override
    public Boolean delete(String stockID) throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean add(CustomEntity entity) throws SQLException, ClassNotFoundException {
        new Alert(Alert.AlertType.INFORMATION, "Not Implemented !");
        return false;
    }

    @Override
    public boolean update(CustomEntity entity) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        Boolean isInsertedToOther_stocks = CrudUtil.execute("update other_suppliers_stocks" +
                        " set Supplier_ID = ?," +
                        "    Stock_ID    = ?," +
                        "    Date        = ?" +
                        " where Stock_ID = ?;",
                entity.getOtherSuppliersStocks_supplier_ID(),
                entity.getOtherSuppliersStocks_stock_ID(),
                entity.getOtherSuppliersStocks_date(),
                entity.getOtherSuppliersStocks_stock_ID());

        Boolean isInsertedToOther_suppliers_stocks = CrudUtil.execute("update other_stocks " +
                        " set Stock_ID   = ?," +
                        "    Stock_Type = ?," +
                        "    Qty        = ?," +
                        "    Price      = ?" +
                        " where Stock_ID = ?;",
                entity.getOtherSuppliersStocks_stock_ID(),
                entity.getOther_stock_Type(),
                entity.getOther_stock_qty(),
                entity.getOther_stock_price(),
                entity.getOtherSuppliersStocks_stock_ID());

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

    @Override
    public Boolean insertInto_OtherStocks(OtherSuppliersStocks entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into other_suppliers_stocks (Supplier_ID, Stock_ID, Date)" +
                "values (?, ?, ?);", entity.getSupplier_ID(), entity.getStock_ID(), entity.getDate());
    }

    @Override
    public Boolean insertInto_OtherSuppliersStocks(OtherStocks entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into other_stocks(Stock_ID, Stock_Type, Qty, Price) " +
                "values(?, ?, ?, ?)", entity.getStock_ID(), entity.getStock_Type(), entity.getQty(), entity.getPrice());
    }

    @Override
    public String getSupplierName(String sup_id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select Name from other_suppliers where Supplier_ID = ?", sup_id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }

    @Override
    public String getSupplierType(String sup_id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("select Supplier_Type from other_suppliers where Supplier_ID = ?", sup_id);
        while (rs.next()) {
            return rs.getString(1);
        }
        return "Not found";
    }
}
