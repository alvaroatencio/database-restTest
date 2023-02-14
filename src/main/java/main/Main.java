package main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static daos.sentencias.*;

import controllers.porConsola;
import resources.pruebaGet;

/*
PROBAMOS LA CONEXION A LA DB MIENTRAS SE CORRE EL SERVIDOR LOCAL DE TOMCAT. CON UNA LINEA EXTRA EN LA CLASE DATABASE
LOGRAMOS CORRER CORRECTAMENTE EL DRIVER Y HACER EJECUCIONES SQL. SI TESTEAS AHORA VAS A NOTAR QUE EN LA CONSOLA DE TOMCAT
NOS RETORNA LA TABLA PERO HAY UN ERROR CON EL JSON Y POR ENDE EL RETURN ES INVALIDO Y OBTENEMOS EL ERROR 500.


 */


public class Main {
    public static void main(String[] args) {
        //mostramos datos por consola
        porConsola.mostrar("usuarios");
        porConsola.mostrar("agenda");


        //prueba json
        System.out.println("Main.main/prueba");
        pruebaGet pG= new pruebaGet();
        System.out.println(pG.prueba("usuarios"));

    }


}
