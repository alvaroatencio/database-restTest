package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseEngine {
    public Connection conexion;
    private databaseConnector dbConnector;


    public Connection getConexion() {
        return dbConnector.iniciar();
    }

    //constructor
    public databaseEngine(){
        //instancia la clase database
        dbConnector = new databaseConnector();
        //inicia la conexion a la db
        conexion= dbConnector.iniciar();
    }

    //procedimiento para sentencias sql que no tienen return

    private String[] array=null;
    public void execute(String consulta){

        try {
            conexion= dbConnector.iniciar();
            PreparedStatement ps = conexion.prepareStatement(consulta);
            for (int i = 0; i < array.length; i++) {
                ps.setObject(i + 1, array[i]);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //funcion para consultas sql con return
    public ResultSet rsreturn(String codigoDB) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = getConexion().prepareStatement(codigoDB);
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}