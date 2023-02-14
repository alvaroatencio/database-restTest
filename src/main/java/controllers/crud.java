package controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import static daos.sentencias.*;

public class crud {

    public static crud instance;
    public static crud getInstance() {
        if (instance == null) {
            instance = new crud();
        }
        return instance;
    }

    public ArrayList<Object[]> getTabla(String tabla) {
        System.out.println("crud.getTabla");
        ArrayList<Object[]> datos = new ArrayList<>();
        try {
            System.out.println();

            //Resulset es una clase de sql que guarda objetos, tablas. En este caso guardo los datos obtenidos del select
            //select esta declarado en interface entitys.java
            ResultSet rs = dbEngine.rsreturn(select + tabla);
            //con metadata obtenemos los valores del schema (nombres de las columnas, tipo de dato, etc)
            ResultSetMetaData metaDatos = rs.getMetaData();
            // Se obtiene el numero de columnas.
            int numeroColumnas = metaDatos.getColumnCount();
            //creo un array para guardar los nombres de las columnas
            Object[] etiquetas = new Object[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++) {
                // Se guardan los nombres de cada columna
                etiquetas[i] = metaDatos.getColumnLabel(i + 1);
            }

            datos.add(etiquetas);
            //recorro el resulset para obtener los datos de las filas
            while (rs.next()) {
                //creo un objeto para almacenar las filas
                Object[] datosFila = new Object[numeroColumnas];
                for (int i = 0; i < numeroColumnas; i++) {
                    //pongan el +1 o explota
                    datosFila[i] = rs.getObject(i + 1);
                }
                //guardo la fila
                datos.add(datosFila);
            }
            rs.close();
            dbEngine.conexion.close();

        } catch (Exception e) {
            System.out.println("crudGet.hola: "+e + "\n" + e.getMessage());
        }
        return datos;
    }

    public static void crearUsuario(String user,String email, String password){
        System.out.println("crud.crearUsuario");
        // CREAR USUARIO
        try {
            //insertUsuarios esta declarado en la interface entitys.java
            PreparedStatement pstmt = dbEngine.getConexion().prepareStatement(insertUsuarios);
            //REEMPLAZAR LOS SETS CON LOS VALORES A GUARDAR
            pstmt.setString(1, user);
            pstmt.setString(2, email);
            pstmt.setString(3, password);

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("crud.crearUsuario: "+ex+"\n"+ex.getMessage());
        }
    }

    //desarrollar el resto de metodos
    // moverlos a la clase-entidad correspondiente?
    // es necesario hacer herencia de los metodos CRUD?
}
