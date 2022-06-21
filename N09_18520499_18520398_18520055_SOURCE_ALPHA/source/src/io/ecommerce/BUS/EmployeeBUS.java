package io.ecommerce.BUS;

import io.ecommerce.DAL.EmployeeDAL;
import io.ecommerce.DTO.Employee;

import java.util.ArrayList;

public class EmployeeBUS {
    private EmployeeDAL _employeeDAL = new EmployeeDAL();

    public ArrayList<Employee> getAllEmployees() {
        return _employeeDAL.getAllEmployees();
    }

    public Employee getEmployeeByIdOrName(String key) {
        return _employeeDAL.getEmployeeByIdOrName(key);
    }

    public Employee getEmployeeByCredentials(String email, String password) {
        return _employeeDAL.getEmployeeByCredentials(email, password);
    }

    public boolean updateEmployeeById(Employee employee) {
        return _employeeDAL.updateEmployeeById(employee);
    }

    public boolean deleteEmployeeById(String id) {
        return _employeeDAL.deleteEmployeeById(id);
    }
}