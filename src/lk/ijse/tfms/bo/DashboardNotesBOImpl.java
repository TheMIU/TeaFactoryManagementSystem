package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.DailyCropDAOImpl;
import lk.ijse.tfms.dao.DashboardNotesDAOImpl;
import lk.ijse.tfms.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DashboardNotesBOImpl {
    DashboardNotesDAOImpl dashboardNotesDAO = new DashboardNotesDAOImpl();
    DailyCropDAOImpl dailyCropDAO = new DailyCropDAOImpl();

    public String getNote(int id) throws SQLException, ClassNotFoundException {
        return dashboardNotesDAO.getNote(id);
    }

    public boolean saveNote(String task01, String task02, String task03, String task04) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isSaved = dashboardNotesDAO.saveNote(task01,task02,task03,task04);
/*
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save notes ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {}*/

        if (isSaved) {
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    public String getTotalKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getTotalKg();
    }

    public String getBagsCountKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getBagsCountKg();
    }

    public String getProductionKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getProductionKg();
    }
}
