
package Datos;

/**
 *
 * @author PC
 */
public class d_Confi_Usuario {
    
    private int id;
    private byte [] imagen;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String email;
    private String tipo;
    private String fecha_sistema;
    private String login_date;
    private String token;
    private int fl_estado;

    public d_Confi_Usuario() {
    }

    public d_Confi_Usuario(int id) {
        this.id = id;
    }

    public d_Confi_Usuario(int id, byte[] imagen, String nombre, String apellido, String usuario, String password, String email, String tipo, String fecha_sistema, String login_date, String token, int fl_estado) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
        this.fecha_sistema = fecha_sistema;
        this.login_date = login_date;
        this.token = token;
        this.fl_estado = fl_estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getFl_estado() {
        return fl_estado;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }
    
    
    
}
