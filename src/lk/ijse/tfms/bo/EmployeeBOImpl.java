package lk.ijse.tfms.bo;

import lk.ijse.tfms.dao.EmployeeDAOImpl;
import lk.ijse.tfms.dto.EmployeeDTO;
import lk.ijse.tfms.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public ArrayList<EmployeeDTO> getEmployeeData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employeeData = employeeDAO.getEmployeeData();

        for(Employee e : employeeData){
            employeeDTOS.add(new EmployeeDTO(e.getEmpID(),e.getType(),e.getName(),e.getAddress(),e.getContact(),e.getId()));
        }

        return employeeDTOS;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentID();
    }

    public boolean insertNewEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.insertNewEmployee(new Employee(dto.getEmpID(), dto.getType(), dto.getName(),dto.getId(), dto.getAddress(), dto.getContact()));
    }

    public boolean updateEmployee(EmployeeDTO dto, String empID) throws SQLException, ClassNotFoundException {
        return employeeDAO.updateEmployee(new Employee(dto.getEmpID(), dto.getType(), dto.getName(),dto.getId(), dto.getAddress(), dto.getContact()), empID);
    }

    public Boolean deleteEmployee(String empID) throws SQLException, ClassNotFoundException {
        return  employeeDAO.deleteEmployee(empID);
    }
}

