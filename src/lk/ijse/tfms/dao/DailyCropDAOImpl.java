package lk.ijse.tfms.dao;

import lk.ijse.tfms.entity.DailyCrop;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyCropDAOImpl {
    public String getSupplierName(String supID) {
        try {
            ResultSet resultSet = CrudUtil.execute("select name from tea_suppliers where Suppliers_ID = ?;",supID);
            while (resultSet.next()){
                return resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "no";
    }

    public boolean saveDailyCrop(DailyCrop entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into daily_crop values (?,?,?);",
                entity.getSuppliers_ID(),
                entity.getDate(),
                entity.getNetWeight()
        );
    }

    public ArrayList<DailyCrop> getData() throws SQLException, ClassNotFoundException {
        ArrayList<DailyCrop> cropData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select * from daily_crop");
        while (rs.next()) {
            cropData.add(new DailyCrop(rs.getString("Date"),
                    rs.getString("Suppliers_ID"),
                    rs.getDouble("NetWeight")
                    ));
        }
        return cropData;
    }

    public Boolean deleteSelected(String supID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from daily_crop where Suppliers_ID = ?", supID);
        return isDeleted;
    }

    public boolean update(DailyCrop entity) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update daily_crop set  Date = ?, NetWeight = ? where Suppliers_ID = ? && Date = ? ;",
                entity.getDate(),
                entity.getNetWeight(),
                entity.getSuppliers_ID(),
                entity.getDate()
        );
        return isUpdated;
    }

    public String getTotalKg(LocalDate now) throws SQLException, ClassNotFoundException {
        String date = now.toString();
        String kg = null;
        ResultSet rs =  CrudUtil.execute("select sum(NetWeight) from daily_crop where Date = ?",date);
        while (rs.next()) {
          kg =  rs.getString("sum(NetWeight)");
        }
        if(kg != null){
            return kg;
        }else  {
            return "0";
        }
    }

    public String getTotalKg() throws SQLException, ClassNotFoundException {
        String kg = null;
        ResultSet rs =  CrudUtil.execute("select sum(NetWeight) from daily_crop");
        while (rs.next()) {
            kg =  rs.getString("sum(NetWeight)");
        }
        if(kg != null){
            return kg;
        }else  {
            return "0";
        }
    }

    public String getProductionKg() throws SQLException, ClassNotFoundException {
        String kg = null;
        ResultSet rs =  CrudUtil.execute("select sum(One_bag_Weight*AvailableQty) from tea_stock;");
        while (rs.next()) {
            kg =  rs.getString("sum(One_bag_Weight*AvailableQty)");
        }
        if(kg != null){
            return kg;
        }else  {
            return "0";
        }
    }

    public String getBagsCountKg() throws SQLException, ClassNotFoundException {
        String kg = null;
        ResultSet rs =  CrudUtil.execute("select sum(AvailableQty) from tea_stock;");
        while (rs.next()) {
            kg =  rs.getString("sum(AvailableQty)");
        }
        if(kg != null){
            return kg;
        }else  {
            return "0";
        }
    }
}
