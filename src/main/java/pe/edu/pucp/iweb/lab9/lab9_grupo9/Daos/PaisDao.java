package pe.edu.pucp.iweb.lab9.lab9_grupo9.Daos;

import pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans.Pais;

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
}
