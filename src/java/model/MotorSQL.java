package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase que interactua directamente con la base de datos.
 * @author JUAN JOSE DOLL
 */
public class MotorSQL {
/*Objetos necesarios para hablar con la BD*/
//1º--> Conexión - Connection
//2º--> Hablar en SQL - Statement
//3º--> Recoger datos - Resultset
    private Connection conn;
    private Statement st;
    private ResultSet rs;
// ¿Dónde está la Base de Datos? En el servidor MySQL con el nombre voyageprive
    private static final String URL
            = "jdbc:mysql://localhost/voyageprive";
    private static final String CONTROLADOR
            = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS = "";

// Métodos de lectura y manipulación de 
// la Base de Datos
    public void connect() {
        try {
//¿Qué controlador necesito?
            Class.forName(CONTROLADOR);
// ¿Dónde está la BD, user y pass?
            conn = DriverManager.
                    getConnection(URL, USER, PASS);
// Con el objeto conexión me creo un
// Statement
            st = conn.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
// Consultas DDL - Data Definition Language
    //Esta altera las tablas: Update, Insert, Delete...
    //Si no hace nada, responde: 0
    public int execute(String sql) {
        int resp = 0;
        try {
            resp = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }
// Consultas DML - Data Manipulation Language
    //Esta sirve para hacer consultas
    public ResultSet executeQuery(String sql) {
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }
    
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
        }
    }

}
