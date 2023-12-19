/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.proyectodeportes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "HelloWorld", urlPatterns = {"/helloworld"})
public class HelloWorld extends HttpServlet {

    DBConnection db = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String run = request.getParameter("run");
        String deporte = request.getParameter("deporte");
        String aficionado = request.getParameter("aficionado");
        String nombre = request.getParameter("idnombre");
        String task = request.getParameter("task");

        if (run == null) {  // not first run

            switch (task) {
                case "insert":
                    if (deporte.isBlank()) {
                        // do nothing
                    } else {
                        db.insertSport(deporte);
                    }
                    response.sendRedirect("form.jsp");

                    break;
                case "delete":
                    if (deporte.isBlank()) {
                        // do nothing
                    } else {
                        db.deleteSport(deporte);
                    }
//                    showForm(response);
                    response.sendRedirect("form.jsp");
                    break;
                case "list":
                    ArrayList<Deporte> listSports = null;
                    //obtenemos de la base de datos la lista de deportes:
                    listSports = db.listSports();
                    //para poder escribir en HTML la lista hay que hacerla
                    //llegar a la página JSP.
                    //Para ello, hay que guardarla en el objeto Session
                    //y recuperarla de este objeto en la página JSP:
                    HttpSession s = request.getSession();
                    s.setAttribute("listSports", listSports);
                    response.sendRedirect("listAndForm.jsp");
//                    listSportsAndShowForm(listSports, response);
                    break;
                case "select":
                    if (deporte.isBlank()) {
                    } else {
                        ArrayList<Deporte> listSports2 = null;
                        listSports2 = db.listLike(deporte);
                        HttpSession s2 = request.getSession();
                        s2.setAttribute("listSports", listSports2);
                        response.sendRedirect("listAndForm.jsp");
                    }
                    break;
                case "insertAficionado":
                    if (aficionado.isBlank() ) {
                    } else {
                        db.insertAficionado(aficionado);
                    }
                    response.sendRedirect("form.jsp");
                    break;
                case "cascadeDelete":
                    int idNombre = Integer.parseInt(nombre);
                    System.out.println("Entra en el switch");
                    if (idNombre>=0 ) {
                        db.deleteById(idNombre);
                        System.out.println("Esta dentro de la funcion ");
                    } else {
                    }
                    response.sendRedirect("form.jsp");
                    break;
                default:
                    break;
            }
        } else if (run.equalsIgnoreCase("start")) { // first run from this client
            this.db = new DBConnection();
            response.sendRedirect("form.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
