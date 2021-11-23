package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.DTO.ParticipanteDTO;

import java.sql.*;
import java.util.ArrayList;

public class ParticipanteDao{
    String user = "root";
    String pass = "root";
    String url = "jdbc:mysql://localhost:3306/lab9?serverTimezone=America/Lima";

    public ArrayList<Participante> listaParticipantes(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        ArrayList<Participante> listita = new ArrayList<>();

        String sentenciaSQL = "SELECT participante.nombre,apellidos,edad,genero,p.nombre,idparticipante FROM participante\n"+
                                "INNER JOIN pais p ON (participante.pais_idpais = p.idpais);";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sentenciaSQL)) {

            while (rs.next()) {
                String nombre = rs.getString(1);
                String apellido = rs.getString(2);
                int edad = rs.getInt(3);
                String genero = rs.getString(4);
                String nacionalidad = rs.getString(5);
                int idParticipante = rs.getInt(6);
                Participante participante = new Participante(nombre,apellido,edad,genero,nacionalidad,idParticipante);
                listita.add(participante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listita;
    }


    public Participante obtenerParticipantePorId(int idParticipante){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Participante participante = null;
        String sentenciaSQL = "SELECT participante.nombre,apellidos,edad,genero,p.nombre FROM participante\n"+
                "INNER JOIN pais p ON (participante.pais_idpais = p.idpais) WHERE participante.idparticipante = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL);){
            pstmt.setInt(1, idParticipante);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                //SIEMPRE HAY UN PARTICIPANTE
                String nombre = rs.getString(1);
                String apellido = rs.getString(2);
                int edad = rs.getInt(3);
                String genero = rs.getString(4);
                String nacionalidad = rs.getString(5);
                participante = new Participante(nombre,apellido,edad,nacionalidad,genero,idParticipante);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participante;
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

    public boolean validarApellido(String apellido){
        boolean valido = true;

        try{
            int num = Integer.parseInt(String.valueOf(apellido.charAt(0)));
            return false;
        }catch(NumberFormatException e){
            return true;
        }
    }

    public boolean edadAceptable(int edad){
        boolean valido = false;
        if(edad > 18){
            valido = true;
        }
        return valido;
    }

    public void editarParticipante(ParticipanteDTO participante){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "UPDATE participante SET nombre = ?, apellidos = ?,edad = ?,genero = ?,pais_idpais = ? WHERE idparticipante = ?;";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sentenciaSQL)) {
            pstmt.setString(1,participante.getNombre());
            pstmt.setString(2,participante.getApellido());
            pstmt.setInt(3,participante.getEdad());
            pstmt.setString(4,participante.getGenero());
            pstmt.setInt(5,participante.getIdPais());
            pstmt.setInt(6,participante.getIdParticipante());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void crearParticipante(ParticipanteDTO participante){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String sentenciaSQL = "INSERT INTO participante (nombre, apellidos, edad, genero, pais_idpais) VALUES (?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = connection.prepareStatement(sentenciaSQL)) {
            pstmt.setString(1,participante.getNombre());
            pstmt.setString(2,participante.getApellido());
            pstmt.setInt(3,participante.getEdad());
            pstmt.setString(4,participante.getGenero());
            pstmt.setInt(5,participante.getIdPais());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
