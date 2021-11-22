
package Datos;

/**
 *
 * @author CINTHYA PAOLA GUILLERMO CASTRO
 */
public class d_Confi_Vehiculo_Marca {
    
    private int id;
    private String nombre;
    private int fl_estado;

    public d_Confi_Vehiculo_Marca() {
    }

    public d_Confi_Vehiculo_Marca(int id) {
        this.id = id;
    }

    public d_Confi_Vehiculo_Marca(int id, String nombre, int fl_estado) {
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

    
}
