package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ParticipanteDao extends BaseDao{

    public ArrayList<Participante> listaParticipantes(){
        ArrayList<Participante> listita = new ArrayList<>();

        String sentenciaSQL = "SELECT participante.nombre,apellidos,edad,genero,p.nombre FROM participante\n"+
                                "INNER JOIN pais p ON (participante.pais_idpais = p.idpais);";


        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sentenciaSQL)) {

            while (rs.next()) {
                String nombre = rs.getString(1);
                String apellido = rs.getString(2);
                int edad = rs.getInt(3);
                String genero = rs.getString(4);
                String nacionalidad = rs.getString(5);
                Participante participante = new Participante(nombre,apellido,edad,genero,nacionalidad);
                listita.add(participante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listita;
    }



}
