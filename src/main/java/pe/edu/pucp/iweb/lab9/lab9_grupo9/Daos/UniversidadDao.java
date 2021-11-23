package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Universidad;

import java.sql.*;
import java.util.ArrayList;

public class UniversidadDao extends BaseDao{

    public ArrayList<Universidad> listarUniversidades() throws SQLException {
        ArrayList<Universidad> listaUniversidad = new ArrayList<>();
        String sql = "SELECT u.iduniversidad,u.nombre,u.ranking,u.numero_alumnos,u.foto,p.nombre FROM universidad u INNER JOIN pais p ON u.pais_idpais=p.idpais ORDER BY ranking;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pais pais = new Pais();
                int idUniversidad = rs.getInt(1);
                String nombre = rs.getString(2);
                int ranking = rs.getInt(3);
                int numero_alumnos = rs.getInt(4);
                String foto = rs.getString(5);
                pais.setNombre(rs.getString(6));
                listaUniversidad.add(new Universidad(idUniversidad, nombre, pais, ranking, numero_alumnos, foto));
            }
        }
            return listaUniversidad;
        }

    public Universidad obtenerUniversidadPorId(int idUniversidad) throws SQLException {
        Universidad universidad = new Universidad();
        String sql = "SELECT u.nombre,u.ranking,count(*),u.foto,p.nombre FROM alumno a RIGHT JOIN universidad u ON u.iduniversidad= a.universidad_iduniversidad\n" +
                "INNER JOIN pais p ON p.idpais = u.pais_idpais\n" +
                "GROUP BY iduniversidad;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            Pais pais = new Pais();
            String nombre = rs.getString(1);
            int ranking = rs.getInt(2);
            int numero_alumnos = rs.getInt(3);
            String foto = rs.getString(4);
            pais.setNombre(rs.getString(5));
            universidad= new Universidad(idUniversidad, nombre, pais, ranking, numero_alumnos, foto);
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
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
        }
    }
    public void crearUniversidad(String nombre, int ranking, int numero,int idPais) throws SQLException{
        String sql = "INSERT INTO (universidad nombre, ranking, numero_alumnos, pais_idpais) VALUES (?, ?, ?, ?);";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,nombre);
            pstmt.setInt(1,ranking);
            pstmt.setInt(1,numero);
            pstmt.setInt(1,idPais);
            pstmt.executeUpdate();
        }
    }
}
