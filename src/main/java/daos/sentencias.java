package daos;
import controllers.databaseEngine;
public interface sentencias {

    databaseEngine dbEngine =new databaseEngine();

    //SENTENCIAS SQL CORRESPONDIENTES PARA LA DB, LOS "?" SE SETEAN LUEGO, PERO DEBEN CORRESPONDER CON LA CANTIDAD DE DATOS A INGRESAR
    //LOS "?" SON UN COMODIN, SE REEMPLAZAN LUEGO CON .setString({VALOR})

    //INSERT usuarios
    //                                                   3 DATOS                  3VALORES
    String insertUsuarios = "INSERT INTO usuarios (usuario, Mail, Password) VALUES (?, ?, ?)";
    //UPDATE usuarios
    String updateUsuarios = "UPDATE usuarios SET Usuario = ?, Mail = ?, Password = ? where id = ?";
    //DELETE usuarios
    String deleteUsuarios = "DELETE FROM usuarios WHERE id= ?";
    //SELECT ALL
    String select = "select * from ";
    //SELECT BY ID FROM usuarios
    String selectUsuariosWhereId = "select * from usuarios where id = ?";
}
