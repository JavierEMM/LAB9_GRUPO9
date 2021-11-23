package pe.edu.pucp.iweb.lab9.lab9_grupo9.Beans;

public class Participante {
    private int idParticipante;
    private String nombre;
    private String apellido;
    private int edad;
    private String nacionalidad;
    private String genero;

    public Participante(String nombre, String apellido, int edad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Participante(String nombre, String apellido, int edad, String nacionalidad, String genero, int idParticipante) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.idParticipante = idParticipante;
    }

    public Participante(String nombre, String apellido, int edad, String nacionalidad, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
    }

    public Participante() {
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
