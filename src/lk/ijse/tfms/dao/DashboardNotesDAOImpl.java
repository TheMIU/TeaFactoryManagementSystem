package lk.ijse.tfms.dao;

import lk.ijse.tfms.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardNotesDAOImpl {

    public String getNote(int id) throws SQLException, ClassNotFoundException {
        ResultSet rs =  CrudUtil.execute("select note from notes where id = ?;",id);
        if (rs.next()) {
            return  rs.getString(1);
        }
        return "";
    }

    public boolean saveNote(String task01, String task02, String task03, String task04) throws SQLException, ClassNotFoundException {
        boolean t1 = CrudUtil.execute("update notes set note = ? where id = ?",task01,1);
        boolean t2 = CrudUtil.execute("update notes set note = ? where id = ?",task02,2);
        boolean t3 = CrudUtil.execute("update notes set note = ? where id = ?",task03,3);
        boolean t4 = CrudUtil.execute("update notes set note = ? where id = ?",task04,4);

        if(t1 && t2 && t3 && t4){
            return true;
        }else {
            return false;
        }
    }
}
