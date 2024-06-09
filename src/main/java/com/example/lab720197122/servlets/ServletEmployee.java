package com.example.lab720197122.servlets;

import com.example.lab720197122.beans.Employee;
import com.example.lab720197122.daos.DaoEmployee;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ServletEmployee", value = "/ServletEmployee")
public class ServletEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "listaEmployees" : request.getParameter("action");

        DaoEmployee daoEmployee = new DaoEmployee();

        switch (action){
            case "listaEmployees":
                ArrayList<Employee> listaEmployees = daoEmployee.listarEmployee();
                request.setAttribute("listaEmployees", listaEmployees);
                RequestDispatcher rd = request.getRequestDispatcher("Employee/home.jsp");
                rd.forward(request, response);
                break;
            case "newEmployee":
                request.getRequestDispatcher("Employee/crear_empleado.jsp").forward(request,response);
                break;
            case "editEmployee":
                String id = request.getParameter("id");
                Employee employee = daoEmployee.buscarPorId(id);

                if(employee != null){
                    request.setAttribute("employee",employee);
                    request.getRequestDispatcher("Employee/editar_empleado.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/ServletEmployee");
                }
                break;
            case "delEmployee":
                String idd = request.getParameter("id");
                Employee employeed = daoEmployee.buscarPorId(idd);

                if(employeed != null){
                    try {
                        daoEmployee.borrarEmployee(idd);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/ServletEmployee");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        DaoEmployee daoEmployee = new DaoEmployee();

        String action = request.getParameter("action") == null ? "crearEmployee" : request.getParameter("action");

        switch (action){
            case "crearEmployee"://voy a crear un nuevo trabajo
                String employeeId = request.getParameter("employeeId");
                String full_name = request.getParameter("full_name");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String phone_number = request.getParameter("phone_number");
                String hire_date = request.getParameter("hire_date");
                String job_id = request.getParameter("job_id");
                String salary = request.getParameter("salary");
                String commission_pct = request.getParameter("commission_pct");
                String manager_id = request.getParameter("manager_id");
                String department_id = request.getParameter("department_id");

                boolean todoValido = true;

                if(employeeId == null || employeeId.isEmpty()){
                    todoValido = false;
                }

                if(full_name == null || full_name.isEmpty()){
                    todoValido = false;
                }

                if(email == null || email.isEmpty()){
                    todoValido = false;
                }

                if(job_id == null || job_id.isEmpty()){
                    todoValido = false;
                }

                if(hire_date == null || hire_date.isEmpty()){
                    todoValido = false;
                }

                if(todoValido){

                    Employee employee = daoEmployee.buscarPorId(employeeId);

                    if(employee == null){
                        daoEmployee.crearEmployee(Integer.parseInt(employeeId), full_name, email, password, phone_number, hire_date, job_id, Double.parseDouble(salary), Double.parseDouble(commission_pct), Integer.parseInt(manager_id), Integer.parseInt(department_id));
                        response.sendRedirect(request.getContextPath() + "/ServletEmployee");
                    }else{
                        request.getRequestDispatcher("Employee/crear_empleado.jsp").forward(request,response);
                    }
                }else{
                    request.getRequestDispatcher("Employee/crear_empleado.jsp").forward(request,response);
                }
                break;
            case "actualizarEmployee": //voy a actualizar
                String employeeId2 = request.getParameter("employeeId");
                String full_name2 = request.getParameter("full_name");
                String email2 = request.getParameter("email");
                String password2 = request.getParameter("password");
                String phone_number2 = request.getParameter("phone_number");
                String hire_date2 = request.getParameter("hire_date");
                String job_id2 = request.getParameter("job_id");
                String salary2 = request.getParameter("salary");
                String commission_pct2 = request.getParameter("commission_pct");
                String manager_id2 = request.getParameter("manager_id");
                String department_id2 = request.getParameter("department_id");

                boolean todoValido2 = true;

                if(employeeId2 == null || employeeId2.isEmpty()){
                    todoValido2 = false;
                }

                if(full_name2 == null || full_name2.isEmpty()){
                    todoValido2 = false;
                }

                if(email2 == null || email2.isEmpty()){
                    todoValido2 = false;
                }

                if(job_id2 == null || job_id2.isEmpty()){
                    todoValido2 = false;
                }

                if(hire_date2 == null || hire_date2.isEmpty()){
                    todoValido2 = false;
                }
                if(todoValido2){
                    Employee employee = new Employee();
                    employee.setEmployeeId(Integer.parseInt(employeeId2));
                    employee.setFullName(full_name2);
                    employee.setEmail(email2);
                    employee.setPassword(password2);
                    employee.setPhoneNumber(phone_number2);
                    employee.setHireDate(hire_date2);
                    employee.setJobId(job_id2);
                    employee.setSalary(Double.parseDouble(salary2));
                    employee.setCommissionPct(Double.parseDouble(commission_pct2));
                    employee.setManagerId(Integer.parseInt(manager_id2));
                    employee.setDepartmentId(Integer.parseInt(department_id2));

                    daoEmployee.editarEmployee(employee);
                    response.sendRedirect(request.getContextPath() + "/ServletEmployee");
                }else{
                    request.setAttribute("employee",daoEmployee.buscarPorId(employeeId2));
                    request.getRequestDispatcher("Employee/editar_empleado").forward(request,response);
                }
                break;

        }
    }
}
