package lk.ijse.tfms.bo.custom;

import java.sql.SQLException;

public interface DashboardNotesBO {
    public String getNote(int id) throws SQLException, ClassNotFoundException;
    public boolean saveNote(String task01, String task02, String task03, String task04) throws SQLException, ClassNotFoundException;
    public String getTotalKg() throws SQLException, ClassNotFoundException;
    public String getBagsCountKg() throws SQLException, ClassNotFoundException;
    public String getProductionKg() throws SQLException, ClassNotFoundException;
}
