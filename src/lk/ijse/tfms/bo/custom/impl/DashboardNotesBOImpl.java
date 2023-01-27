package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.DashboardNotesBO;
import lk.ijse.tfms.dao.FactoryDAO;
import lk.ijse.tfms.dao.custom.DailyCropDAO;
import lk.ijse.tfms.dao.custom.impl.DailyCropDAOImpl;
import lk.ijse.tfms.dao.custom.DashboardNotesDAO;
import lk.ijse.tfms.dao.custom.impl.DashboardNotesDAOImpl;
import lk.ijse.tfms.db.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DashboardNotesBOImpl implements DashboardNotesBO {
    DashboardNotesDAO dashboardNotesDAO = (DashboardNotesDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.DASHBOARD_NOTES);
    DailyCropDAO dailyCropDAO = (DailyCropDAO) FactoryDAO.getFactoryDAO().getDAO(FactoryDAO.Types.DAILY_CROP);

    @Override
    public String getNote(int id) throws SQLException, ClassNotFoundException {
        return dashboardNotesDAO.getNote(id);
    }

    @Override
    public boolean saveNote(String task01, String task02, String task03, String task04) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        boolean isSaved = dashboardNotesDAO.saveNote(task01, task02, task03, task04);
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

    @Override
    public String getTotalKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getTotalKg();
    }

    @Override
    public String getBagsCountKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getBagsCountKg();
    }

    @Override
    public String getProductionKg() throws SQLException, ClassNotFoundException {
        return dailyCropDAO.getProductionKg();
    }
}
