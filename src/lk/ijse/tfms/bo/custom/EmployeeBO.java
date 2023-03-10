package lk.ijse.tfms.bo.custom;

import lk.ijse.tfms.bo.SuperBO;
import lk.ijse.tfms.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getEmployeeData() throws SQLException, ClassNotFoundException;
    public String getCurrentID() throws SQLException, ClassNotFoundException;
    public boolean insertNewEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException;
    public Boolean deleteEmployee(String empID) throws SQLException, ClassNotFoundException;
}
