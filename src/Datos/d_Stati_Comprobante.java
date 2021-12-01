
package Datos;

/**
 *
 * @author PC
 */
public class d_Stati_Comprobante {
    
    private int id;
    private String nombre;    
    private String tipo;
    private String codigo_sunat;

    public d_Stati_Comprobante() {
    }

    public d_Stati_Comprobante(int id) {
        this.id = id;
    }

    public d_Stati_Comprobante(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    

    public d_Stati_Comprobante(int id, String nombre, String tipo, String codigo_sunat) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_sunat = codigo_sunat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo_sunat() {
        return codigo_sunat;
    }

    public void setCodigo_sunat(String codigo_sunat) {
        this.codigo_sunat = codigo_sunat;
    }
    
    @Override
    public String toString(){
        
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if (obj != null) {
            
            return this.id == ((d_Stati_Comprobante) obj).id;
            
        }
        
        return false;  
        
    }
    
    
    
}
