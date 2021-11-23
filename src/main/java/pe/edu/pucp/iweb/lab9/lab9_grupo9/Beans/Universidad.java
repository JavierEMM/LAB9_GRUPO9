package pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans;

public class Universidad {
    private int idUniversidad;
    private String nombre;
    private Pais pais;
    private Continente continente;
    private int ranking;
    private int numeroAlumnos;

    public Universidad() {
    }
    public Universidad(int idUniversidad,String nombre, Pais pais, int ranking, int numeroAlumnos, String foto, Continente continente) {
        this.idUniversidad=idUniversidad;
        this.nombre = nombre;
        this.pais = pais;
        this.ranking = ranking;
        this.numeroAlumnos = numeroAlumnos;
        this.foto = foto;
        this.continente=continente;
    }

    private String foto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getNumeroAlumnos() {
        return numeroAlumnos;
    }

    public void setNumeroAlumnos(int numeroAlumnos) {
        this.numeroAlumnos = numeroAlumnos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public int getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Continente getContinente() {
        return continente;
    }

    public void setContinente(Continente continente) {
        this.continente = continente;
    }
}
