package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Continente;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad;

import java.sql.*;
import java.util.ArrayList;

public class UniversidadDao extends BaseDao{

    public ArrayList<Universidad> listarUniversidades(String filter) throws SQLException {
        ArrayList<Universidad> listaUniversidad = new ArrayList<>();
        String sql="";
        if(filter.equalsIgnoreCase("nombre")){
            sql  =  "SELECT u.iduniversidad,u.nombre,u.ranking,count(a.participante_idparticipante),u.foto,p.nombre,c.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                    "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                    "INNER JOIN continente c ON c.idcontinente = p.continente_idcontinente\n" +
                    "GROUP BY iduniversidad\n" +
                    "ORDER BY u.nombre;";
        }else if(filter.equalsIgnoreCase("Pais")){
            sql  =  "SELECT u.iduniversidad,u.nombre,u.ranking,count(a.participante_idparticipante),u.foto,p.nombre,c.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                    "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                    "INNER JOIN continente c ON c.idcontinente = p.continente_idcontinente\n" +
                    "GROUP BY iduniversidad\n" +
                    "ORDER BY u.ranking;";
        }else if(filter.equalsIgnoreCase("Alumnos")){
            sql  =  "SELECT u.iduniversidad,u.nombre,u.ranking,count(a.participante_idparticipante),u.foto,p.nombre,c.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                    "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                    "INNER JOIN continente c ON c.idcontinente = p.continente_idcontinente\n" +
                    "GROUP BY iduniversidad\n" +
                    "ORDER BY count(a.participante_idparticipante);";
        }else{
          sql  =  "SELECT u.iduniversidad,u.nombre,u.ranking,count(a.participante_idparticipante),u.foto,p.nombre,c.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                    "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                    "INNER JOIN continente c ON c.idcontinente = p.continente_idcontinente\n" +
                    "GROUP BY iduniversidad\n" +
                    "ORDER BY ranking;";
        }

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pais pais = new Pais();
                Continente continente = new Continente();
                int idUniversidad = rs.getInt(1);
                String nombre = rs.getString(2);
                int ranking = rs.getInt(3);
                int numero_alumnos = rs.getInt(4);
                String foto = rs.getString(5);
                pais.setNombre(rs.getString(6));
                continente.setNombre(rs.getString(7));
                listaUniversidad.add(new Universidad(idUniversidad, nombre, pais, ranking, numero_alumnos, foto,continente));
            }
        }
            return listaUniversidad;
        }

    public Universidad obtenerUniversidadPorId(int idUniversidad) throws SQLException {
        Universidad universidad = new Universidad();
        Pais pais = new Pais();
        Continente continente = new Continente();
        String sql = "SELECT u.nombre,u.ranking,count(a.participante_idparticipante),u.foto,p.nombre,c.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                "INNER JOIN continente c ON c.idcontinente = p.continente_idcontinente " +
                "GROUP BY iduniversidad " +
                "HAVING iduniversidad = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ) {
            pstmt.setInt(1,idUniversidad);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String nombre = rs.getString(1);
            int ranking = rs.getInt(2);
            int numero_alumnos = rs.getInt(3);
            String foto = rs.getString(4);
            pais.setNombre(rs.getString(5));
            continente.setNombre(rs.getString(6));
            universidad= new Universidad(idUniversidad, nombre, pais, ranking, numero_alumnos, foto,continente);
        }
        return universidad;
    }

    public void editarUniversidad(int id, String nombre,int ranking, String foto , int pais) throws SQLException{
        String sql = "UPDATE universidad SET nombre = ?, ranking = ?, foto = ?, pais_idpais = ? WHERE iduniversidad = ? ;";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nombre);
            pstmt.setInt(2,ranking);
            pstmt.setString(3,foto);
            pstmt.setInt(4,pais);
            pstmt.setInt(5,id);
            pstmt.executeUpdate();
        }
    }
    public void borrarUniversidad(int id) throws SQLException{
        String sql = "DELETE FROM universidad WHERE (iduniversidad = ?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String sql2="DELETE FROM alumno WHERE universidad_iduniversidad = ?";
            try(PreparedStatement pstmt2 = conn.prepareStatement(sql2)){
                pstmt2.setInt(1,id);
                pstmt2.executeUpdate();
            }
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }
    }

    public void crearUniversidad(String nombre, int ranking,String foto,int idPais) throws SQLException{
        String sql = "INSERT INTO universidad (nombre, ranking, foto, pais_idpais) VALUES (?,?,?,?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nombre);
            pstmt.setInt(2,ranking);
            pstmt.setString(3,foto);
            pstmt.setInt(4,idPais);
            pstmt.executeUpdate();
        }
    }
    public boolean validarNombre(String nombre){
        boolean valido = false;
        try{
            int num = Integer.parseInt(String.valueOf(nombre.charAt(0)));
            return false;
        }catch(NumberFormatException e){
            return true;
        }
    }

}
