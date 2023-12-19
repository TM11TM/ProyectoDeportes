<%-- 
    Document   : listAndForm
    Created on : 12 dic 2023, 19:13:29
    Author     : Carlos
--%>

<%@page import="com.mycompany.proyectodeportes.Deporte"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SportThingsManagement</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 20px;
            }

            h1 {
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            
            table, th, td {
                border: 1px solid #ddd;
            }
            
            th, td {
                padding: 15px;
                text-align: left;
            }
            
            form {
                margin-top: 20px;
            }
            
            input[type="text"], input[type="submit"], input[type="hidden"] {
                padding: 10px;
                margin: 5px;
                border-radius: 5px;
            }
            
            input[type="submit"] {
                background-color: #4caf50;
                color: white;
                cursor: pointer;
            }
        </style>   <!--CSS-->
    </head>
    <h1>SportThingsManagement</h1>
    <%
        ArrayList<Deporte> l = (ArrayList<Deporte>) session.getAttribute("listSports");
        if (l != null) {
            out.println("<table>");
            for (Object sp : l) {
                out.println("<tr>");
                Deporte s = (Deporte) sp;
                out.println("<td>Sport:</td><td> " + s.getName() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        } else {
    %>
    <h2>No data</h2>
    <%
        }
    %>
    <form action="helloworld">
        Deporte:<input type="text" name="deporte">
        <input type="hidden" name="task" value="insert" >
        <input type="submit" label="Insert">
    </form>
    <form action="helloworld">
        Deporte:<input type="text" name="deporte">
        <input type="hidden" name="task" value="delete" >
        <input type="submit" label="Delete">
    </form>
    <form action="helloworld">
        <input type="hidden" name="task" value="list" ">
        <input type="submit" value="List sports">
    </form>
</html>
