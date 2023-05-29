package lk.ijse.tfms.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    // facade -	Gathering most common methods into a single interface to improve code re-usability

    public ArrayList<T> getData() throws SQLException, ClassNotFoundException;

    public Boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean add(T entity) throws SQLException, ClassNotFoundException;

    public boolean update(T entity) throws SQLException, ClassNotFoundException;

    public String getCurrentID() throws SQLException, ClassNotFoundException;
}
