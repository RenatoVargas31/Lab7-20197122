package com.example.lab720197122.daos;

import com.example.lab720197122.beans.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {
    public ArrayList<Employee> listarEmployee(){

        ArrayList<Employee> listaEmployee = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "SELECT e.employee_id, CONCAT(e.first_name,' ',e.last_name) AS full_name, e.email, e.password, e.phone_number, e.hire_date, e.job_id, e.salary, e.commission_pct, e.manager_id, e.department_id, e.enabled FROM employees e";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();

                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFullName(rs.getString("full_name"));
                employee.setEmail(rs.getString("email"));
                employee.setPassword(rs.getString("password"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setHireDate(rs.getString("hire_date"));
                employee.setJobId(rs.getString("job_id"));
                employee.setSalary(rs.getDouble("salary"));
                employee.setCommissionPct(rs.getDouble("commission_pct"));
                employee.setManagerId(rs.getInt("manager_id"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employee.setEnabled(rs.getInt("enabled"));

                listaEmployee.add(employee);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEmployee;
    }

    public Employee buscarPorId(String id){

        Employee employee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees where employee_id = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    employee = new Employee();

                    employee.setEmployeeId(rs.getInt(1));
                    String fullname = rs.getString(2) + " " + rs.getString(3);
                    employee.setFullName(fullname);
                    employee.setEmail(rs.getString(4));
                    employee.setPassword(rs.getString(5));
                    employee.setPhoneNumber(rs.getString(6));
                    employee.setHireDate(rs.getString(7));
                    employee.setJobId(rs.getString(8));
                    employee.setSalary(rs.getDouble(9));
                    employee.setCommissionPct(rs.getDouble(10));
                    employee.setManagerId(rs.getInt(11));
                    employee.setDepartmentId(rs.getInt(12));
                    employee.setEnabled(rs.getInt(13));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    public void crearEmployee(int employee_id, String full_name, String email, String password, String phone_number, String hire_date, String job_id, double salary, double commission_pct, int manager_id, int department_id){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password_db = "root";

        String sql = "insert into jobs (employee_id, first_name, last_name, email, password, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id, enabled) values (?,?,?,?,?,?,?,?,?,?,?,1);";

        try(Connection connection = DriverManager.getConnection(url,username,password_db);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,employee_id);
            ///////////////////////////////////////////////////
            String name_full = full_name;
            String first_name = name_full.split(" ")[0];
            String last_name = name_full.split(" ")[1];
            //////////////////////////////////////////////////
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, phone_number);
            pstmt.setString(7, hire_date);
            pstmt.setString(8, job_id);
            pstmt.setDouble(9, salary);
            pstmt.setDouble(10, commission_pct);
            pstmt.setInt(11, manager_id);
            pstmt.setInt(12, department_id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarEmployee(Employee employee){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "update employees set first_name = ?, last_name = ?, email = ?, password = ?, phone_number=?, hire_date = ?, job_id = ?, salary = ?, commission_pct = ?, manager_id = ?, department_id = ?, enabled = ? where job_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            String name_full = employee.getFullName();
            String first_name = name_full.split(" ")[0];
            String last_name = name_full.split(" ")[1];

            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPassword());
            pstmt.setString(5, employee.getPhoneNumber());
            pstmt.setString(6, employee.getHireDate());
            pstmt.setString(7, employee.getJobId());
            pstmt.setDouble(8, employee.getSalary());
            pstmt.setDouble(9, employee.getCommissionPct());
            pstmt.setInt(10, employee.getManagerId());
            pstmt.setInt(11, employee.getDepartmentId());
            pstmt.setInt(12, employee.getEnabled());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarEmployee(String id) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "UPDATE employees SET enabled = 0 WHERE employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,id);
            pstmt.executeUpdate();
        }
    }

}
