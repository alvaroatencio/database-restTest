package resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class jsonFiles {

    public static jsonFiles instance;
    public static jsonFiles getInstance() {
        if (instance == null) {
            instance = new jsonFiles();
        }
        return instance;
    }
    public JSONArray objectToJson(ArrayList<Object[]> tabla) {
        //System.out.println("jsonFiles.objectToJson");
        JSONArray jsonObject = new JSONArray();
        try {

            //creamos array para los metadatos en el index 0 de la tabla
            Object[] etiqueta = tabla.get(0);

            //recorremos las filas de la tabla para cargar los objetos en el json
            for (int i=1;i< tabla.size();i++) {
                JSONObject fila = new JSONObject();

                //recorremos las filas y las guardamos en el json
                for (int j=0;j<tabla.get(i).length;j++) {
                    fila.put(etiqueta[j].toString(),tabla.get(i)[j]);
                }
                jsonObject.put(fila);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    public void mostrarArray(ArrayList<Object[]> tabla) {
        System.out.println("jsonFiles.mostrarArray");
        tabla.forEach(objeto ->
        {
            for (int i = 0; i < objeto.length; i++) {
                System.out.print(objeto[i]+"\t");
            }
            System.out.println();
        });
    }
}
