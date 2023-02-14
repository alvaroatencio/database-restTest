package controllers;
import java.sql.*;

public class databaseConnector {
    Connection cn=null;

    public Connection iniciar(){
        //si no hay una conexion creada inicia una
        if(!comprobar()){
            try {
                //Class.forName( "com.mysql.cj.jdbc.Driver" ); //innecesario en las ultimas versiones del jdbc

                //necesario para hostear la api
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
                //declaracion de la conexion                  localhost:{puerto}/{nombredeladb},{user},{password}
                cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/listed","root","");
                System.out.println("database.iniciar: cn iniciada");
            } catch (SQLException e) {
                System.out.print(e+"\n"+e.getMessage());
            }
        }
        //devuelve la conexion
        return cn;
    }
    public boolean comprobar(){
        try {
            if(cn==null||cn.isClosed()){
                return false;
            }else{
                System.out.println("database.comprobar: cn existente");
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cerrar(){
        try {
            cn.close();
        } catch (SQLException e) {
            System.out.print(e+"\n"+e.getMessage());
        }
    }
}