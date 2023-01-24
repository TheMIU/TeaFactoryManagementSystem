package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.EmployeeDAOImpl;
import lk.ijse.tfms.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public ArrayList<EmployeeDTO> getEmployeeData() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeData();
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentID();
    }

    public boolean insertNewEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.insertNewEmployee(new EmployeeDTO(dto.getEmpID(), dto.getType(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getId()));
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO, String empID) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateEmployee(employeeDTO, empID);
    }

    public Boolean deleteEmployee(String empID) throws SQLException, ClassNotFoundException {
        return  employeeDAO.deleteEmployee(empID);
    }
}

