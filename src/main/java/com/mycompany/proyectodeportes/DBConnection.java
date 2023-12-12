/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectodeportes;

import java.sql.Connection;
import java.sql.DriverManager;
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
        ArrayList<Deporte> lista =new ArrayList<Deporte>();
        try {
            Statement s = this.c.createStatement();
            String sql = "select * from deporte";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                Deporte d= new Deporte(rs.getString(1));
                lista.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
