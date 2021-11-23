package pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans;

public class Pais {
    private String nombre;
    private String continente;
    private String tamano;
    private int cantidadPoblacion;

    public Pais(String nombre, String continente , String tamano, int cantidadPoblacion) {
        this.nombre = nombre;
        this.continente = continente;
        this.tamano = tamano;
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public int getCantidadPoblacion() {
        return cantidadPoblacion;
    }

    public void setCantidadPoblacion(int cantidadPoblacion) {
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
