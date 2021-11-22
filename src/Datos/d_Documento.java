
package Datos;

/**
 *
 * @author PC
 */
public class d_Documento {
    
    private int id;
    private String nombre;
    private String codigo_sunat;
    private String tipo;

    public d_Documento() {
        
    }
    
    public d_Documento(int id) {
        
        this.id = id;
        
    }

    public d_Documento(int id, String nombre, String codigo_sunat, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo_sunat = codigo_sunat;
        this.tipo = tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo_sunat(String codigo_sunat) {
        this.codigo_sunat = codigo_sunat;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo_sunat() {
        return codigo_sunat;
    }

    public String getTipo() {
        return tipo;
    }
    
    @Override
    public String toString(){
        
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if (obj != null) {
            
            return this.id == ((d_Documento) obj).id;
            
        }
        
        return false;  
        
    }
    
    
    
}
