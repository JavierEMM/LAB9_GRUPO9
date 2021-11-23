package pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans;

public class Alumno extends  Participante {

    private int codigo;
    private double promedioPonderado;
    private String condicion;

    public Alumno(String nombre, String apellido, int edad,int idParticipante,int codigo,double promedioPonderado,String condicion) {
        super(nombre,apellido,edad,idParticipante);
        this.codigo = codigo;
        this.promedioPonderado = promedioPonderado;
        this.condicion = condicion;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getPromedioPonderado() {
        return promedioPonderado;
    }

    public void setPromedioPonderado(double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }




}
