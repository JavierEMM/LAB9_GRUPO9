package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
