package io.ecommerce.DAL;

import io.ecommerce.ConnectionConfigurator;
import io.ecommerce.DTO.Employee;
import io.ecommerce.Gender;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class EmployeeDAL implements ConnectionConfigurator {
    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees ORDER BY employ_id DESC;";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        )
        {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setEmail(resultSet.getString(3));
                employee.setBirthdate(LocalDate.parse(resultSet.getString(4)));
                employee.setGender(Gender.valueOf(resultSet.getString(5).toUpperCase(Locale.ROOT)));

                employees.add(employee);
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeByIdOrName(String key) {
        key += "%";

        Employee employee = null;
        String query = "SELECT * FROM Employees WHERE employee_id LIKE ? OR full_name LIKE ?;";

        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, key);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(resultSet.getString(1));
                    employee.setFullName(resultSet.getString(2));
                    employee.setEmail(resultSet.getString(3));
                    employee.setBirthdate(LocalDate.parse(resultSet.getString(4)));
                    employee.setGender(Gender.valueOf(resultSet.getString(5).toUpperCase(Locale.ROOT)));
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employee;
    }

    public Employee getEmployeeByCredentials(String email, String password) {
        Employee employee = null;
        String query = "SELECT * FROM Employees WHERE email = ? AND password = ?;";

        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmployeeId(resultSet.getString(1));
                    employee.setFullName(resultSet.getString(2));
                    employee.setEmail(resultSet.getString(3));
                    employee.setBirthdate(LocalDate.parse(resultSet.getString(5)));
                    employee.setGender(Gender.valueOf(resultSet.getString(6).toUpperCase(Locale.ROOT)));
                }
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return employee;
    }

    public boolean updateEmployeeById(Employee employee) {
        int rowUpdated = 0;
        String query = """
                UPDATE Employees SET 
                full_name = ?, 
                email = ?, 
                birth_date = ?, 
                gender = ? 
                WHERE employ_id = ?;
                """;

        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, employee.getFullName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setDate(3, Date.valueOf(employee.getBirthdate()));
            preparedStatement.setString(4, employee.getGender().toString());
            preparedStatement.setString(5, employee.getEmployeeId());
            rowUpdated = preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return rowUpdated > 0;
    }

    public boolean deleteEmployeeById(String id) {
        int rowDeleted = 0;
        String query = "DELETE FROM Employees WHERE employ_id = ?;";

        try (Connection connection = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setString(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }

        return rowDeleted > 0;
    }
}