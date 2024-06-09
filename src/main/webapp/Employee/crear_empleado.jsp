<%--
  Created by IntelliJ IDEA.
  User: rlvs_
  Date: 7/06/2024
  Time: 03:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Crear un nuevo Empleado</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Crear un nuevo Empleado</h1>
    <form method="post" action="<%=request.getContextPath()%>/ServletEmployee">
        <div class="mb-3">
            <labe>Employee ID</labe>
            <input type="text" class="form-control" name="employeeId">
        </div>
        <div class="mb-3">
            <label>Nombre y Apellido</label>
            <input type="text" class="form-control" name="full_name">
        </div>
        <div class="mb-3">
            <label>Correo electrónico</label>
            <input type="text" class="form-control" name="email">
        </div>
        <div class="mb-3">
            <label>Contraseña</label>
            <input type="text" class="form-control" name="password">
        </div>
        <div class="mb-3">
            <label>Fecha de ingreso</label>
            <input type="text" class="form-control" name="hire_date">
        </div>
        <div class="mb-3">
            <label>Job ID</label>
            <input type="text" class="form-control" name="job_id">
        </div>
        <div class="mb-3">
            <label>Salario</label>
            <input type="text" class="form-control" name="salary">
        </div>
        <a href="<%=request.getContextPath()%>/ServletEmployee" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>
