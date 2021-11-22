
package Datos;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class d_Usuario {
    
    public int id;
    public String imagen;
    public String nombre;
    public String apellido;
    public String usuario;
    public String password;
    public String email;
    public String tipo;
    public String fecha_sistema;
    public String login_date;
    public String token;
    public int fl_estado;

    public d_Usuario() {
        
    }

    public d_Usuario(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public d_Usuario(int id, String imagen, String nombre, String apellido, String usuario, String password, String email, String tipo, String fecha_sistema, String login_date, String token, int fl_estado) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha_sistema(String fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }

    public int getId() {
        return id;
    }

    public String getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFecha_sistema() {
        return fecha_sistema;
    }

    public String getLogin_date() {
        return login_date;
    }

    public String getToken() {
        return token;
    }

    public int getFl_estado() {
        return fl_estado;
    }
    
    
    
}
