package Model;

import Bean.EmployeeBean;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class EmployeeModel {

    private Connection connection;
    private String driverClass;
    private String connectionString;
    private String db_username;
    private String db_password;

    public void setConnection() {
        try {
            Class.forName(driverClass);
            // connection = DriverManager.getConnection(connectionString+"?useUnicode=true&characterEncoding=UTF-8&character_set_results=utf8", db_username, db_password);
            connection = (Connection) DriverManager.getConnection(connectionString, db_username, db_password);
        } catch (Exception e) {
            System.out.println("CommandModel setConnection() Error: " + e);
        }
    }

    public int saveEmployeeData(EmployeeBean employee) throws UnknownHostException {

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

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDb_username() {
        return db_username;
    }

    public void setDb_username(String db_username) {
        this.db_username = db_username;
    }

    public String getDb_password() {
        return db_password;
    }

    public void setDb_password(String db_password) {
        this.db_password = db_password;
    }

////////////////////////Show EmployeeData////////////////////////
    public List<EmployeeBean> showEmployeeData() {

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

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return employeeList;
    }

    public int deleteEmployeeData(int employeeId) {
        int rowsAffected = 0;

        String query = "DELETE FROM employee WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, employeeId);
            rowsAffected = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return rowsAffected;
    }

    public EmployeeBean getsingleEmployee(int empId) {
        EmployeeBean employee = new EmployeeBean();

        String query = "SELECT * FROM employee WHERE id = ?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, empId);
            ResultSet stmt = pstmt.executeQuery();

            while (stmt.next()) {
                employee.setId(stmt.getInt("id"));
                employee.setName(stmt.getString("name"));
                employee.setEmail(stmt.getString("email"));
                employee.setPassword(stmt.getString("password"));
                employee.setGender(stmt.getString("gender"));
                employee.setDistrict_name(stmt.getString("district_name"));
                employee.setCondition(stmt.getString("terms"));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return employee;
    }

    public int editEmployeeDetails(EmployeeBean employee, int empId) {
//        String query = "insert into employee(name,email,password,gender,district_name,terms) values(?,?,?,?,?,?);";

        String query = "UPDATE employee SET name = ?, email = ?, password = ?, gender = ?, district_name = ?, terms = ? WHERE id = ?";

        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getPassword());
            pstmt.setString(4, employee.getGender());
            pstmt.setString(5, employee.getDistrict_name());
            pstmt.setString(6, employee.getCondition());
            pstmt.setInt(7, empId);

            rowsAffected = pstmt.executeUpdate();

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
}
