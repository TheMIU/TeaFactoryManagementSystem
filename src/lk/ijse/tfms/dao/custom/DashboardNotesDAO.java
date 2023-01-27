package lk.ijse.tfms.dao.custom;

import lk.ijse.tfms.dao.CrudDAO;
import lk.ijse.tfms.entity.Notes;

import java.sql.SQLException;

public interface DashboardNotesDAO extends CrudDAO<Notes> {
    public String getNote(int id) throws SQLException, ClassNotFoundException;

    public boolean saveNote(String task01, String task02, String task03, String task04) throws SQLException, ClassNotFoundException;
}
