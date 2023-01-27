package lk.ijse.tfms.dao.custom.impl;

import lk.ijse.tfms.dao.custom.EmployeeDAO;
import lk.ijse.tfms.entity.Employee;
import lk.ijse.tfms.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getData() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeesData = new ArrayList<>();

        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED)");
        while (rs.next()) {
            employeesData.add(new Employee(rs.getString("EmpID"),
                    rs.getString("Type"),
                    rs.getString("Name"),
                    rs.getString("ID"),
                    rs.getString("Address"),
                    rs.getString("Contact")));
        }
        return employeesData;
    }

    @Override
    public String getCurrentID() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM employee ORDER BY CAST(SUBSTRING(EmpID, 2) AS UNSIGNED) DESC LIMIT 1");
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public Boolean delete(String empID) throws SQLException, ClassNotFoundException {
        Boolean isDeleted = CrudUtil.execute("delete from employee where EmpID = ?", empID);
        return isDeleted;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        Boolean isUpdated = CrudUtil.execute("update employee set Type = ?, Name = ?, ID = ?,Address = ?, Contact = ? where EmpID = ?;",
                entity.getType(),
                entity.getName(),
                entity.getId(),
                entity.getAddress(),
                entity.getContact(),
                entity.getEmpID()
        );
        return isUpdated;
    }

    @Override
    public boolean add(Employee entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("insert into employee values (?,?,?,?,?,?);",
                entity.getEmpID(),
                entity.getType(),
                entity.getName(),
                entity.getId(),
                entity.getAddress(),
                entity.getContact()
        );
    }
}
