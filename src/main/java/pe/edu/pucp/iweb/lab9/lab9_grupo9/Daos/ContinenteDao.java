package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Continente;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinenteDao extends BaseDao{
    public ArrayList<Continente> listarContinente(){
        ArrayList<Continente> listaContinentes = new ArrayList<>();

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from continente")){
            while (rs.next()){
                Continente continente = new Continente(rs.getInt(1),rs.getString(2));
                listaContinentes.add(continente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaContinentes;
    }
}
