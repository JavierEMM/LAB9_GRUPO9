package pe.edu.pucp.iweb.lab9.lab9_grupo9.DTO;

public class ParticipanteDTO {
    private int idParticipante;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private int idPais;

    public ParticipanteDTO(int idParticipante, String nombre, String apellido, int edad, String genero, int idPais) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.idPais = idPais;
    }

    public ParticipanteDTO(String nombre, String apellido, int edad, String genero, int idPais) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.idPais = idPais;
    }

    public ParticipanteDTO() {
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
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


    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
