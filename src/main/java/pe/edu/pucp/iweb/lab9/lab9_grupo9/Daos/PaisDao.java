package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;
import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Participante;

import java.sql.*;
import java.util.ArrayList;

public class PaisDao extends BaseDao{


    public ArrayList<Pais> listarPaises(){
        ArrayList<Pais> listaPaises = new ArrayList<>();

        try(Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.idpais ,p.nombre, c.nombre as continente , p.tamanho, p.poblacion FROM lab9.pais p\n" +
                    "inner join\tlab9.continente c on c.idcontinente = p.continente_idcontinente order by p.nombre")){
            while (rs.next()){
                Pais pais = new Pais(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                listaPaises.add(pais);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaPaises;
    }
    public ArrayList<Pais> listarPaisesFiltrados(int idcontinente){
        ArrayList<Pais> listaPaises = new ArrayList<>();
        String sql = "SELECT p.idpais ,p.nombre, c.nombre as continente , p.tamanho, p.poblacion FROM lab9.pais p\n" +
                "inner join\tlab9.continente c on c.idcontinente = p.continente_idcontinente where c.idcontinente = ? order by p.nombre";

        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ){
            pstmt.setInt(1,idcontinente);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Pais pais = new Pais(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
                listaPaises.add(pais);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaPaises;
    }
    public void añadirPais(String nombre, int poblacion, String tamano, int continente_idcontinente){
        String sql = "INSERT INTO pais (nombre, poblacion, tamanho, continente_idcontinente) VALUES (?, ?, ?, ?);";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1,nombre);
            pstmt.setInt(2,poblacion);
            pstmt.setString(3,tamano);
            pstmt.setInt(4,continente_idcontinente);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void editarPais(String nombre, int poblacion, String tamano, int idpais){
        String sql = "UPDATE pais set nombre = ?, poblacion = ?, tamanho = ? where idpais = ?";
        try(Connection conn = this.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1,nombre);
            pstmt.setInt(2,poblacion);
            pstmt.setString(3,tamano);
            pstmt.setInt(4,idpais);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /*public String obtenerNombrePaisPorId(int idPais){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String nombre = null;
        String url = "jdbc:mysql://localhost:3306/lab9?serverTimezone=America/Lima";

        String sentenciaSQL = "SELECT nombre  FROM pais WHERE idpais = ?;";
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sentenciaSQL);){
            pstmt.setInt(1, idPais);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                nombre = rs.getString(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombre;

    }*/




}
