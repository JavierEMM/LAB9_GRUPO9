package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UniversidadDao extends BaseDao{

    public ArrayList<Universidad> listarUniversidades(){
        ArrayList<Universidad> listaUniversidad = new ArrayList<>();
        String sql="SELECT u.iduniversidad,u.nombre,u.ranking,u.numero_alumnos,u.foto,p.nombre FROM universidad u INNER JOIN pais p ON u.pais_idpais=p.idpais ORDER BY ranking;";
        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                Pais pais = new Pais();
                int idUniversidad = rs.getInt(1);
                String nombre = rs.getString(2);
                int ranking = rs.getInt(3);
                int numero_alumnos = rs.getInt(4);
                String foto = rs.getString(5);
                pais.setNombre(rs.getString(6));
                listaUniversidad.add(new Universidad(idUniversidad,nombre,pais,ranking,numero_alumnos,foto));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaUniversidad;
    }
}
