
package Datos;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class d_Almac_Tipo_Articulo {
    
    private int id;
    private String nombre;
    private int fl_estado;

    public d_Almac_Tipo_Articulo() {
    }

    public d_Almac_Tipo_Articulo(int id) {
        this.id = id;
    }

    public d_Almac_Tipo_Articulo(int id, String nombre, int fl_estado) {
        this.id = id;
        this.nombre = nombre;
        this.fl_estado = fl_estado;
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

    public int getFl_estado() {
        return fl_estado;
    }

    public void setFl_estado(int fl_estado) {
        this.fl_estado = fl_estado;
    }
    
    @Override
    public String toString(){
        
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj){
        
        if (obj != null) {
            
            return this.id == ((d_Almac_Tipo_Articulo) obj).id;
            
        }
        
        return false;  
        
    }

    
}
