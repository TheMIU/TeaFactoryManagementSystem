package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.TeaStockItemDTO;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeaStockItemDAOImpl {

    public static ArrayList<TeaStockItemDTO> getStockItemsData() throws SQLException, ClassNotFoundException {
        ArrayList<TeaStockItemDTO> teaStockItemDTOData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select * from tea_stock");
        while (rs.next()) {
            teaStockItemDTOData.add(new TeaStockItemDTO(rs.getString("Stock_ID"),
                    rs.getString("Type"),
                    rs.getString("Input_Date"),
                    rs.getDouble("One_bag_Weight"),
                    rs.getInt("Qty"),
                    rs.getInt("AvailableQty")));
        }
        return teaStockItemDTOData;
    }

    public static String getCurrentStockID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM tea_stock ORDER BY CAST(SUBSTRING(Stock_ID, 3) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static Boolean deleteStockItem(String stockID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from tea_stock where Stock_ID = ?", stockID);
        return isDeleted;
    }

    public static boolean insertNewStockItem(TeaStockItemDTO teaStockItemDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into tea_stock (Stock_ID, Type, Input_Date, One_bag_Weight, Qty,AvailableQty) values (?,?,?,?,?,?);",
                teaStockItemDTO.getStockID(),
                teaStockItemDTO.getType(),
                teaStockItemDTO.getDate(),
                teaStockItemDTO.getWeight(),
                teaStockItemDTO.getQty(),
                teaStockItemDTO.getQty()
        );
    }

    public static boolean updateStockItem(TeaStockItemDTO teaStockItemDTO) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update tea_stock set Stock_ID = ?, Type = ?, Input_Date = ?, One_bag_Weight = ?,Qty = ? " +
                        "where Stock_ID = ?;",
                teaStockItemDTO.getStockID(),
                teaStockItemDTO.getType(),
                teaStockItemDTO.getDate(),
                teaStockItemDTO.getWeight(),
                teaStockItemDTO.getQty(),
                teaStockItemDTO.getStockID()
        );
        return isUpdated;
    }

    public static void updateAvailableQTY(String stockID, int qtyP, int qtyOP, int qtyFOP, int qtyGFOP, int qtyTGFOP, int qtyFTGFOP, int qtyBOP, int qtyFBOP) throws SQLException, ClassNotFoundException {
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'P';",qtyP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'OP';",qtyOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FOP';",qtyFOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'GFOP';",qtyGFOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'TGFOP';",qtyTGFOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FTGFOP';",qtyFTGFOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'BOP';",qtyBOP,stockID);
        CrudUtil.execute(" update tea_stock set AvailableQty =(AvailableQty - ?) where Stock_ID = ? && Type = 'FBOP';",qtyFBOP,stockID);
    }
}
