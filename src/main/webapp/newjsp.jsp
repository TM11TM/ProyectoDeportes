<%-- 
    Document   : newjsp
    Created on : 18 dic 2023, 10:20:24
    Author     : dawmi
--%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List and Form</title>
</head>
<body>

<%
    Date horaActual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    String horaFormateada = formato.format(horaActual);
    out.println("Hora actual: " + horaFormateada);
%>

<!-- Aquí sigue el resto del contenido de la JSP -->

</body>
</html>


<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List and Form</title>
</head>
<body>

<%
    Date fechaActual = new Date();
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    String fechaFormateada = format.format(fechaActual);
    out.println("Fecha actual: " + fechaFormateada);
%>

<!-- Aquí sigue el resto del contenido de la JSP -->

</body>
</html>




numero de registros

<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List and Form</title>
</head>
<body>



<!-- Aquí sigue el resto del contenido de listAndForm.jsp -->

</body>
</html>