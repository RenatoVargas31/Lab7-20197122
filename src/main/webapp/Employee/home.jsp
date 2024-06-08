<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab720197122.beans.Employee" %><%--
  Created by IntelliJ IDEA.
  User: rlvs_
  Date: 7/06/2024
  Time: 03:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Employee> listaEmployees = (ArrayList<Employee>) request.getAttribute("listaEmployees");
%>
<html>
<head>
    <title>Trabajos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="clearfix mt-3 mt-2">
        <a href="<%=request.getContextPath()%>/ServletEmployee">
            <h1 class="float-start link-dark">Lista de Trabajos</h1>
        </a>
        <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/ServletEmployee?action=newEmployee">Crear trabajo</a>
    </div>
    <hr/>
    <table class="table table-striped mt-3">
        <tr class="table-primary">
            <th>ID Usuario</th>
            <th>Nombre</th>
            <th>Correo eléctronico</th>
            <th>ID Trabajo/th>
            <th>Salary</th>
            <th></th>
            <th></th>
        </tr>
        <% for (Employee employee : listaEmployees) { %>
        <tr>
            <td><%=employee.getEmployeeId()  %>
            </td>
            <td><%=employee.getFullName()%>
            </td>
            <td><%=employee.getEmail()%>
            </td>
            <td><%=employee.getJobId()%>
            </td>
            <td>$ <%=employee.getSalary()%>
            </td>
            <td><a class="btn btn-success" href="<%=request.getContextPath()%>/ServletEmployee?action=editEmployee&id=<%= employee.getEmployeeId() %>">Editar</a></td>
            <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/ServletEmployee?action=delEmployee&id=<%= employee.getEmployeeId() %>">Borrar</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
