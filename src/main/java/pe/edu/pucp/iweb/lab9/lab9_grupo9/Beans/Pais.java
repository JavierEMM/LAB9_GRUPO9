package pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans;

public class Pais {
    private int idPais;
    private String nombre;
    private String continente;
    private String tamano;
    private int cantidadPoblacion;

    public Pais() {
    }

    public Pais(int idPais,String nombre, String continente , String tamano, int cantidadPoblacion) {
        this.setIdPais(idPais);
        this.nombre = nombre;
        this.continente = continente;
        this.tamano = tamano;
        this.cantidadPoblacion = cantidadPoblacion;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }
}
