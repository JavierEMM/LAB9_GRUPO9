package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaisDao extends BaseDao{
    public ArrayList<Pais> listarPaises(){
        ArrayList<Pais> listaPaises = new ArrayList<>();

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.nombre, c.nombre as continente , p.tamanho, p.poblacion FROM lab9.pais p\n" +
                    "inner join\tlab9.continente c on c.idcontinente = p.continente_idcontinente")){
            while (rs.next()){
                Pais pais = new Pais(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4));
                listaPaises.add(pais);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaPaises;
    }
}
