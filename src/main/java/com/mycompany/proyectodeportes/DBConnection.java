/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class DBConnection {

    private Connection c;

    public DBConnection() {

        try {   // cargar el driver de conexión con la base de datos adecuada
            // y obtener la conexión a través de la IP, nombre de base de datos,
            // usuario y contraseña
            Class.forName("org.mariadb.jdbc.Driver");
            this.c = DriverManager.getConnection(
                    "jdbc:mariadb://192.168.1.50:3306/deportes",
                    "user", "pass"
            );
        } catch (SQLException | ClassNotFoundException ex) {
            this.c = null;
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("conn: " + c);  // para la verificación en consola
    }

    public int insertSport(String deporte) {
        String sqlSentence = "insert into deporte values ('" + deporte + "');";
        System.out.println("Sentence: " + sqlSentence);  // verificación en consola
        Statement st;
        int n = -1;
        try {
            st = this.c.createStatement();
            n = st.executeUpdate(sqlSentence);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int deleteSport(String deporte) {
        String sqlSentence = "delete from deporte where nombre like '" + deporte + "';";
        System.out.println("Sentence: " + sqlSentence);  // verificación en consola
        Statement st;
        int n = -1;
        try {
            st = this.c.createStatement();
            n = st.executeUpdate(sqlSentence);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    ArrayList<Deporte> listSports() {
        ArrayList<Deporte> lista = new ArrayList<Deporte>();
        try {
            Statement s = this.c.createStatement();
            String sql = "select * from deporte";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Deporte d = new Deporte(rs.getString(1));
                lista.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<Deporte> listLike(String searchString) {
        ArrayList<Deporte> lista1 = new ArrayList<Deporte>();
        try {
            //Creamos el Statement
            Statement s = this.c.createStatement();
            //Indicamos lo que tiene que hacer
            String sql = "select * from deporte where nombre like '%" + searchString + "%';";
            //Ejecutamos la query
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Deporte d = new Deporte(rs.getString(1));
            lista1.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista1;
    }
    
    public int insertAficionado(String aficionado){
        int idNombre = 8;
        int idAficionado = 0;
        String sql = "INSERT into deporte_aficionado (idAficionado, idNombre, aficionado) VALUES ('"+idAficionado+"','"+idNombre+"','"+aficionado+"');";
        System.out.println("Sentence" + sql);
        Statement st;
        int n = -1;
        try {
            st = this.c.createStatement();
            n = st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    public int deleteById(int idDeporte){
       int n = -1;
       
       String sqlQuery1 = "DELETE FROM deporte_aficionado where idNombre = ?";
         try(PreparedStatement preparedStatement = this.c.prepareStatement(sqlQuery1)) {
            preparedStatement.setInt(1, idDeporte);
            n = preparedStatement.executeUpdate();
             System.out.println("Borrados" + n+ "registros de deporte_aficionado asociados al id deporte: "+ idDeporte);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       String sqlQuery2 = "DELETE FROM deporte where idNombre = ?";
         try(PreparedStatement preparedStatement = this.c.prepareStatement(sqlQuery2)) {
            preparedStatement.setInt(1, idDeporte);
            n = preparedStatement.executeUpdate();
            System.out.println("Borrados" + n+ "registros de deporteasociados al id deporte: "+ idDeporte);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       return n;
    }
}
