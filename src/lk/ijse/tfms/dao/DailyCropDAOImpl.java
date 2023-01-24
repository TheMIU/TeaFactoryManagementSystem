package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.DailyCropDTO;
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

    public boolean saveDailyCrop(DailyCropDTO dailyCropDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into daily_crop values (?,?,?);",
                dailyCropDTO.getSupID(),
                dailyCropDTO.getDate(),
                dailyCropDTO.getWeight()
        );
    }

    public ArrayList<DailyCropDTO> getData() throws SQLException, ClassNotFoundException {
        ArrayList<DailyCropDTO> cropData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("select * from daily_crop");
        while (rs.next()) {
            cropData.add(new DailyCropDTO(rs.getString("Date"),
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

    public boolean update(DailyCropDTO dc) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update daily_crop set  Date = ?, NetWeight = ? where Suppliers_ID = ? && Date = ? ;",
                dc.getDate(),
                dc.getWeight(),
                dc.getSupID(),
                dc.getDate()
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
