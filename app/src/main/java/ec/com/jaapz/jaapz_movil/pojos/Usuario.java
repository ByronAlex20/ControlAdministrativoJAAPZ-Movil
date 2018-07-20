package ec.com.jaapz.jaapz_movil.pojos;

public class Usuario {
    private Integer idUsuario;
    private String apellidos;
    private String cargo;
    private String cedula;
    private String clave;
    private String direccion;
    private String estado;
    private byte[] foto;
    private String nombres;
    private String telefono;
    private String usuario;

    public Usuario(Integer idUsuario, String apellidos, String cargo, String cedula, String clave, String direccion, String estado, byte[] foto, String nombres, String telefono, String usuario) {
        this.idUsuario = idUsuario;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.cedula = cedula;
        this.clave = clave;
        this.direccion = direccion;
        this.estado = estado;
        this.foto = foto;
        this.nombres = nombres;
        this.telefono = telefono;
        this.usuario = usuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
