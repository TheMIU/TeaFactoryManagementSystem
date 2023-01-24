package lk.ijse.tfms.dao;

import lk.ijse.tfms.dto.EmployeeDTO;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl {
    public ArrayList<EmployeeDTO> getEmployeeData() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeesData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED)");
        while (rs.next()) {
            employeesData.add(new EmployeeDTO(rs.getString("EmpID"),
                    rs.getString("Type"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Contact"),
                    rs.getString("id")));
        }
        return employeesData;
    }

    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public Boolean deleteEmployee(String empID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from employee where EmpID = ?",empID);
        return isDeleted;
    }

    public boolean updateEmployee(EmployeeDTO employeeDTO, String empID) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update employee set Type = ?, Name = ?, ID = ?,Address = ?, Contact = ? where EmpID = ?;",
                employeeDTO.getType(),
                employeeDTO.getName(),
                employeeDTO.getId(),
                employeeDTO.getAddress(),
                employeeDTO.getContact(),
                empID
        );
        return isUpdated;
    }

    public boolean insertNewEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?,?);",
                employeeDTO.getEmpID(),
                employeeDTO.getType(),
                employeeDTO.getName(),
                employeeDTO.getId(),
                employeeDTO.getAddress(),
                employeeDTO.getContact()
        );
    }
}
