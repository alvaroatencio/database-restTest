package controllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import static daos.sentencias.dbEngine;
import static daos.sentencias.select;

public class porConsola {
    public static porConsola instance;
    public static porConsola getInstance() {
        if (instance == null) {
            instance = new porConsola();
        }
        return instance;
    }

    //Metodo para mostrar tablas por consola.
    public static void mostrar(String database){
        System.out.println("porConsola.mostrar: Mostrando base de datos '"+database+"'");
        //Valor para modificar tabulacion
        int tabulacion = 32;

        try {
            //Resulset es una clase de sql que guarda objetos, tablas. En este caso guardo los datos obtenidos del select
            //select esta declarado en interface entitys.java
            ResultSet rs= dbEngine.rsreturn(select+database);
            //con metadata obtenemos los valores del schema (nombres de las columnas, tipo de dato, etc)
            ResultSetMetaData metaDatos = rs.getMetaData();
            // Se obtiene el numero de columnas.
            int numeroColumnas = metaDatos.getColumnCount();
            //creo un array para guardar los nombres de las columnas
            Object[] etiquetas = new Object[numeroColumnas];
            for (int i = 0; i < numeroColumnas; i++) {
                // Se guardan los nombres de cada columna
                etiquetas[i] = metaDatos.getColumnLabel(i+1)+"|";
                //salida a consola con formato
                System.out.printf("%"+tabulacion+"s",etiquetas[i]);
            }
            System.out.println();

            //recorro el resulset para obtener los datos de las filas
            while (rs.next()) {
                //creo un objeto para almacenar las filas
                Object[] datosFila = new Object[numeroColumnas];

                for (int i = 0; i < numeroColumnas; i++) {
                    //pongan el +1 o explota
                    datosFila[i] = rs.getObject(i + 1);
                    //salida a consola con formato
                    System.out.printf("%"+tabulacion+"s",datosFila[i]+"|");
                }
                //espaciado entre filas
                System.out.println();
            }
            rs.close();
            dbEngine.conexion.close();

        }catch (Exception e){
            System.out.println(e +"\n"+e.getMessage());
        }
    }
}
