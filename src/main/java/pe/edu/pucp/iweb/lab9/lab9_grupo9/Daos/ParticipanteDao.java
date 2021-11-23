package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;

import java.sql.*;
import java.util.ArrayList;

public class ParticipanteDao extends BaseDao{

    public ArrayList<Participante> listaParticipantes(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ArrayList<Participante> listita = new ArrayList<>();

        String sentenciaSQL = "SELECT participante.nombre,apellidos,edad,genero,p.nombre FROM participante\n"+
                                "INNER JOIN pais p ON (participante.pais_idpais = p.idpais);";

        String url = "jdbc:mysql://localhost:3306/lab9?serverTimezone=America/Lima";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
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
