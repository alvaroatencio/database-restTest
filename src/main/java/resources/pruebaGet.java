package resources;

import jakarta.ws.rs.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import static daos.sentencias.dbEngine;
import static daos.sentencias.select;
import controllers.crud;

@Path("/prueba")
public class pruebaGet {
    @GET
    //@Produces("text/plain")
    @Produces("application/json")
    public String prueba(@QueryParam("database") String tabla) {
        if(tabla==null){
            return "GET sin parametros";
        }else if(tabla.equals("cuentas")||tabla.equals("titulares")) {
            System.out.println("pruebaGet.prueba/guardamos el select de usuario en un ArrayList<Object[]>");
            ArrayList<Object[]> cosas = crud.getInstance().getTabla(tabla);
            return jsonFiles.getInstance().objectToJson(cosas).toString();

        } else return "Solicitud invalida";
    }

}


