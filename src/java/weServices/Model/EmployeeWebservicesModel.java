/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weServices.Model;

import Bean.EmployeeBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lENOVO
 */
public class EmployeeWebservicesModel {

    private Connection connection;

    public void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_practice_db", "root", "root");
        } catch (Exception e) {
            System.out.println("BLEWebServicesModel setConnection() Error: " + e);
        }
    }

    public List<EmployeeBean> getAllemployeesDetails() {
        List<EmployeeBean> employeeList = new ArrayList<>();

        String query = "select * from employee";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                EmployeeBean employee = new EmployeeBean();
                employee.setId(stmt.getInt("id"));
                employee.setName(stmt.getString("name"));
                employee.setEmail(stmt.getString("email"));
                employee.setPassword(stmt.getString("password"));
                employee.setGender(stmt.getString("gender"));
                employee.setDistrict_name(stmt.getString("district_name"));
                employee.setCondition(stmt.getString("terms"));
                employee.setFileName(stmt.getString("fileName"));
                employee.setFilePath(stmt.getString("filePath"));
                employeeList.add(employee);
            }
            connection.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return employeeList;
    }

    public int saveAllEmployeesDetails(EmployeeBean employee) {
        String query = "insert into employee(name,email,password,gender,district_name,terms, fileName, filePath) values(?,?,?,?,?,?,?,?);";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getGender());
            pstmt.setString(5, employee.getDistrict_name());
            pstmt.setString(6, employee.getCondition());
            pstmt.setString(7, employee.getFileName());
            pstmt.setString(8, employee.getFilePath());

            rowsAffected = pstmt.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error while insert record..." + e);
        }

        if (rowsAffected > 0) {
            System.out.println("Data Saved SuccessFully....................");
        } else {
            System.out.println("Data not Saved.......................");
        }

        return rowsAffected;
    }

    public int deleteSingleEmployeeDetails(int empId) {
        int rowsAffected = 0;

        String query = "DELETE FROM employee WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, empId);
            rowsAffected = pstmt.executeUpdate();
            connection.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return rowsAffected;
    }

    public int editAllEmployeesDetails(int empId, EmployeeBean employee) {

        String query = "UPDATE employee SET name = ?, email = ?, password = ?, gender = ?, district_name = ?, terms = ?, fileName=?, filePath=? WHERE id = ?";

        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getGender());
            pstmt.setString(5, employee.getDistrict_name());
            pstmt.setString(6, employee.getCondition());
            pstmt.setString(7, employee.getFileName());
            pstmt.setString(8, employee.getFilePath());
            pstmt.setInt(9, empId);

            rowsAffected = pstmt.executeUpdate();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error while Edit record..." + e);
        }

        if (rowsAffected > 0) {
            System.out.println("Data Edit SuccessFully....................");
        } else {
            System.out.println("Data not Edit.......................");
        }

        return rowsAffected;
    }

}
