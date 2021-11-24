package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.DTO.ParticipanteDTO;

import java.sql.*;
import java.util.ArrayList;

public class AlumnoDao extends  BaseDao {
    public ArrayList<Alumno> listaAlumnos (int idUniversidad){
        ArrayList<Alumno> listita = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "SELECT participante.nombre,participante.apellidos,participante.edad,alumno.codigo,alumno.promedio_ponderado,alumno.condicion,participante.idparticipante FROM participante\n"+
                "INNER JOIN alumno ON (participante.idparticipante = alumno.participante_idparticipante)\n"+
                "INNER JOIN universidad ON (alumno.universidad_iduniversidad = universidad.iduniversidad)\n"+
                "WHERE universidad.iduniversidad = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {
            pstmt.setInt(1,idUniversidad);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    String nombre = rs.getString(1);
                    String apellidos = rs.getString(2);
                    int edad = rs.getInt(3);
                    int codigo = rs.getInt(4);
                    String promedioStr = rs.getString(5);
                    double promedio = Double.parseDouble(promedioStr);
                    String condicion = rs.getString(6);
                    int idParticipante = rs.getInt(7);
                    Alumno alumno = new Alumno(nombre,apellidos,edad,idParticipante,codigo,promedio,condicion);
                    listita.add(alumno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return listita;
    }

    public void borrarAlumno(String idParticipante){
        String sql = "DELETE FROM alumno WHERE participante_idparticipante = ?";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1,idParticipante);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editarCondicion(String idParticipante){
        String sql = "UPDATE alumno set condicion = ? WHERE participante_idparticipante = ?";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1,"Eliminado");
            pstmt.setString(2,idParticipante);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Participante> listaParticipantesCrear (int idUniversidad){
        ArrayList<Participante> listita = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "SELECT participante.idparticipante,participante.nombre FROM participante\n"+
                                "LEFT JOIN alumno ON (participante.idparticipante = alumno.participante_idparticipante)\n"+
                                "LEFT JOIN universidad ON (alumno.universidad_iduniversidad = universidad.iduniversidad)\n"+
                                "WHERE codigo IS NULL AND participante.pais_idpais = ?;";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {
            pstmt.setInt(1,idUniversidad);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Participante participante = new Participante();
                participante.setIdParticipante(rs.getInt(1));
                participante.setNombre(rs.getString(2));
                listita.add(participante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listita;
    }

    public  void insertAlumno(int idParticipante, int codigoParticipante, double promedioParticipante,int idUniversidad) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "INSERT INTO alumno (codigo,promedio_ponderado,condicion,participante_idparticipante,universidad_iduniversidad)\n"+
                                "VALUES (?,?,?,?,?);";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {

            pstmt.setInt(1,codigoParticipante);
            pstmt.setDouble(2,promedioParticipante);
            pstmt.setString(3,"Normal");
            pstmt.setInt(4,idParticipante);
            pstmt.setInt(5,idUniversidad);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public Alumno obtenerAlumnoPorId(int idParticipante){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Alumno alumno = null;
        String sentenciaSQL =   "SELECT p.nombre,p.apellidos,p.edad,a.codigo,a.promedio_ponderado,a.condicion FROM alumno a\n"+
                                "INNER JOIN participante p ON (a.participante_idparticipante = p.idparticipante)\n"+
                                "WHERE a.participante_idparticipante = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL);){
            pstmt.setInt(1, idParticipante);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //SIEMPRE HAY UN PARTICIPANTE
                String nombre = rs.getString(1);
                String apellido = rs.getString(2);
                int edad = rs.getInt(3);
                int codigo = rs.getInt(4);
                double promedio = rs.getDouble(5);
                String condicion = rs.getString(6);
                alumno = new Alumno(nombre,apellido,edad,idParticipante,codigo,promedio,condicion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }


    public  void updateAlumno(Alumno alumno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "UPDATE participante \n" +
                "INNER JOIN alumno a ON (participante.idparticipante = a.participante_idparticipante)\n" +
                "SET nombre = ?,apellidos = ? , edad = ? , a.codigo = ?,a.promedio_ponderado = ?,a.condicion = ? WHERE a.participante_idparticipante = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL)) {

            pstmt.setString(1,alumno.getNombre());
            System.out.println(alumno.getNombre());
            pstmt.setString(2,alumno.getApellido());
            pstmt.setInt(3,alumno.getEdad());
            pstmt.setInt(4,alumno.getCodigo());
            pstmt.setDouble(5,alumno.getPromedioPonderado());
            pstmt.setString(6,alumno.getCondicion());
            pstmt.setInt(7,alumno.getIdParticipante());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
