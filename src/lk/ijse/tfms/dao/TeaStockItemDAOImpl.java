package lk.ijse.tfms.dao;

import lk.ijse.tfms.entity.TeaStock;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeaStockItemDAOImpl {

    public ArrayList<TeaStock> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaStock> teaStockData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select * from tea_stock");
        while (rs.next()) {
            teaStockData.add(new TeaStock(rs.getString("Stock_ID"),
                    rs.getString("Type"),
                    rs.getString("Input_Date"),
                    rs.getDouble("One_bag_Weight"),
                    rs.getInt("Qty"),
                    rs.getInt("AvailableQty")));
        }
        return teaStockData;
    }

    public String getCurrentStockID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM tea_stock ORDER BY CAST(SUBSTRING(Stock_ID, 3) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from tea_stock where Stock_ID = ?", stockID);
        return isDeleted;
    }

    public boolean insertNewStockItem(TeaStock entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty) values (?,?,?,?,?,?);",
                entity.getStock_ID(),
                entity.getType(),
                entity.getInput_Date(),
                entity.getOne_bag_Weight(),
                entity.getQty(),
                entity.getQty()
        );
    }

    public boolean updateStockItem(TeaStock entity) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update tea_stock set Stock_ID = ?, Type = ?, Input_Date = ?, One_bag_Weight = ?,Qty = ? " +
                        "where Stock_ID = ?;",
                entity.getStock_ID(),
                entity.getType(),
                entity.getInput_Date(),
                entity.getOne_bag_Weight(),
                entity.getQty(),
                entity.getStock_ID()
        );
        return isUpdated;
    }

    public boolean updateAvailableQTY(String stockID, int qtyP, int qtyOP, int qtyFOP, int qtyGFOP, int qtyTGFOP, int qtyFTGFOP, int qtyBOP, int qtyFBOP) throws SQLException, ClassNotFoundException {
        boolean b1 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'P';", qtyP, stockID);
        boolean b2 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'OP';", qtyOP, stockID);
        boolean b3 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FOP';", qtyFOP, stockID);
        boolean b4 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'GFOP';", qtyGFOP, stockID);
        boolean b5 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'TGFOP';", qtyTGFOP, stockID);
        boolean b6 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FTGFOP';", qtyFTGFOP, stockID);
        boolean b7 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'BOP';", qtyBOP, stockID);
        boolean b8 = CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FBOP';", qtyFBOP, stockID);

        if (b1 || b2 || b3 || b4 || b5 || b6 || b7 || b8) {
            return true;
        } else {
            return false;
        }
    }
}
