package lk.ijse.tfms.bo.custom.impl;

import lk.ijse.tfms.bo.custom.EmployeeBO;
import lk.ijse.tfms.dao.custom.EmployeeDAO;
import lk.ijse.tfms.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.tfms.dto.EmployeeDTO;
import lk.ijse.tfms.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    public ArrayList<EmployeeDTO> getEmployeeData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();
        ArrayList<Employee> employeeData = employeeDAO.getData();

        for (Employee e : employeeData) {
            employeeDTOS.add(new EmployeeDTO(e.getEmpID(), e.getType(), e.getName(), e.getAddress(), e.getContact(), e.getId()));
        }

        return employeeDTOS;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        return employeeDAO.getCurrentID();
    }

    @Override
    public boolean insertNewEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmpID(), dto.getType(), dto.getName(), dto.getId(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpID(), dto.getType(), dto.getName(), dto.getId(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public Boolean deleteEmployee(String empID) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(empID);
    }
}

