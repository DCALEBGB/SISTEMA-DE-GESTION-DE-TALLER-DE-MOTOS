
package Datos;


/**
 *
 * @author CINTHYA 
 */
public class d_Confi_Proveedor {
    
    private int id;
    private int id_tipo_documento;
    private String numero_documento;
    private int id_ubigeo;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombre;
    private String razon_social;
    private String direccion;
    private String telefono;
    private String correo;
    private int fl_estado;

    public d_Confi_Proveedor() {
    }

    public d_Confi_Proveedor(int id) {
        this.id = id;
    }
    
    public d_Confi_Proveedor(int id, String numero_documento, String nombre, String apellido_paterno, String apellido_materno) {
        this.id = id;
        this.numero_documento = numero_documento;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        
    }

    public d_Confi_Proveedor(int id, int id_tipo_documento, String numero_documento, int id_ubigeo, String apellido_paterno, String apellido_materno, String nombre, String razon_social, String direccion, String telefono, String correo, int fl_estado) {
        this.id = id;
        this.id_tipo_documento = id_tipo_documento;
        this.numero_documento = numero_documento;
        this.id_ubigeo = id_ubigeo;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.razon_social = razon_social;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fl_estado = fl_estado;
    }

    public int getId() {
        return id;
    }

    public int getId_tipo_documento() {
        return id_tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public int getId_ubigeo() {
        return id_ubigeo;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public int getFl_estado() {
        return fl_estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_tipo_documento(int id_tipo_documento) {
        this.id_tipo_documento = id_tipo_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public void setId_ubigeo(int id_ubigeo) {
        this.id_ubigeo = id_ubigeo;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }
    
    @Override
    public String toString(){
        
        return numero_documento + " | " + nombre + " " + apellido_paterno + " " + apellido_materno;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if (obj != null) {
            
            return this.id == ((d_Confi_Proveedor) obj).id;
            
        }
        
        return false;  
        
    }
    
    
    
    
    
}
